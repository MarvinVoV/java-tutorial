package sun.instrument.practise.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by yamorn on 8/27/16.
 */
public class LogExecutionTimeProxy implements InvocationHandler{

    // The target instance
    private Object proxied;

    public LogExecutionTimeProxy(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();

        // Invoke the method on the target instance
        Object result = method.invoke(proxied, args);

        // Print the execute time
        System.out.println("Executed method " + method.getName() + " in " + (System.nanoTime() - startTime) + " nanoseconds");

        return result;
    }
}
