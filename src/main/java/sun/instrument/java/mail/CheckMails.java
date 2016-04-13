package sun.instrument.java.mail;

import javax.mail.*;
import java.security.Security;
import java.util.Properties;

/**
 * Created by yamorn on 2016/4/13.
 */
public class CheckMails {
    public static void main(String[] args) throws Exception{

//        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        String host = "client.deppon.com";
        final String user = "sunyameng@deppon.com";
        final String password = "";
        String port = "995";
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "pop3");
        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", port);
        properties.put("mail.pop3.auth", "true");//需要邮件服务器认证


        Session emailSession = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        //create the POP3 store object and connect with the pop server  ssl 995 !ssl 110
        Store store = emailSession.getStore("pop3");
        store.connect(host, user, password);
        //create the folder object and open it
        Folder emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_ONLY);

        // retrieve the messages from the folder in an array and print it
        Message[] messages = emailFolder.getMessages();
        System.out.println("messages.length---" + messages.length);

        for (int i = 0, n = messages.length; i < n; i++) {
            Message message = messages[i];
            System.out.println("---------------------------------");
            System.out.println("Email Number " + (i + 1));
            System.out.println("Subject: " + message.getSubject());
            System.out.println("From: " + message.getFrom()[0]);
            System.out.println("Text: " + message.getContent().toString());

        }

        //close the store and folder objects
        emailFolder.close(false);
        store.close();


    }
}
