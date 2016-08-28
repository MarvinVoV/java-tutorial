package sun.instrument.practise.proxy;

import javax.xml.ws.Service;
import java.lang.reflect.Proxy;

/**
 * Created by yamorn on 8/27/16.
 */
public class ServiceMain {
    public static void main(String[] args) {
        // Create the target instance
        ServiceOne serviceOne = new ServiceOneBean();

        // Create the proxy
        ServiceOne proxy = (ServiceOne) Proxy.newProxyInstance(ServiceOne.class.getClassLoader(),
                serviceOne.getClass().getInterfaces(), new LogExecutionTimeProxy(serviceOne));

        String result = proxy.sayHello();
        System.out.println("result = " + result);
    }
}
