package com.sun.base.security.sasl.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.security.auth.callback.*;
import javax.security.sasl.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by louis on 2015/1/20.
 */
@RunWith(JUnit4.class)
public class SaslMd5DigestExampleTest {

    @Test
    public void testSaslDigestMd5() throws SaslException {
        String[] mechanism = {"DIGEST-MD5"};
        Map<String, String> props = new HashMap<>();
        props.put(Sasl.QOP, "auth-conf");

        final SaslClient client = Sasl.createSaslClient(mechanism, "myAuthorizationId", "myProtocol", "myRealm",
                props, new CallbackHandler() {
                    @Override
                    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
                        for (Callback callback : callbacks) {
                            //realm of the server is defaulted to my realm
                            if (callback instanceof RealmCallback) {
                                ((RealmCallback) callback).setText("myRealm");
                            } else if (callback instanceof NameCallback) {
                                ((NameCallback) callback).setName("bob");
                            } else if (callback instanceof PasswordCallback) {
                                ((PasswordCallback) callback).setPassword("bob's pass".toCharArray());
                            }
                        }
                    }
                });

        final SaslServer server = Sasl.createSaslServer(mechanism[0], "myProtocol", "myRealm", props, new CallbackHandler() {
            @Override
            public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
                String user = null;
                PasswordCallback passwordCb = null;
                for (Callback callback : callbacks) {
                    // Set required username and password, framework will verify they match
                    if (callback instanceof RealmCallback) {
                        assertThat(((RealmCallback) callback).getDefaultText(),
                                is(equalTo("myRealm")));
                    } else if (callback instanceof NameCallback) {
                        user = ((NameCallback) callback).getDefaultName();
                    } else if (callback instanceof PasswordCallback) {
                        passwordCb = (PasswordCallback) callback;
                    }
                    if (callback instanceof AuthorizeCallback) {
                        // always allow valid user
                        final AuthorizeCallback cb = (AuthorizeCallback) callback;
                        assertThat(cb.getAuthorizationID(), equalTo("myAuthorizationId"));
                        cb.setAuthorized(true);
                    }
                }

                Map<String, char[]> userDb = new HashMap<>();
                userDb.put("bob", "bob's pass".toCharArray());
                userDb.put("admin", "secret pass".toCharArray());

                if (userDb.containsKey(user) && passwordCb != null) {
                    passwordCb.setPassword(userDb.get(user));
                }
            }
        });

        byte[] response = new byte[]{};
        assertThat(client.hasInitialResponse(), is(false));

        // in principle there should be a loop here, but we know
        // that this MD5-digest implementation is done after 3 rounds
        response = server.evaluateResponse(response);
        response = client.evaluateChallenge(response);
        response = server.evaluateResponse(response);
        assertThat(server.isComplete(), is(true));
        assertThat(client.evaluateChallenge(response), is(nullValue()));
        assertThat(client.isComplete(), is(true));

        byte[] content = "hello".getBytes();
        final byte[] wrapped = server.wrap(content, 0, content.length);
        assertThat(wrapped, is(not(equalTo(content))));
        assertThat(client.unwrap(wrapped, 0, wrapped.length), is(equalTo(content)));

    }
}
