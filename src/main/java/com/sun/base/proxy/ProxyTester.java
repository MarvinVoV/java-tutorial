package com.sun.base.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by louis on 2015/1/19.
 */
public class ProxyTester {
    public interface Hello{
        void sayHello();
    }

    public interface Hi{
        void sayHi();
    }

    public class HelloImpl implements Hello,Hi{
        @Override
        public void sayHello() {
            System.out.println("Hello world");
        }

        @Override
        public void sayHi() {
            System.out.println("Hi world");
        }
    }


    public class FakeHello implements InvocationHandler{
        private Object agent;

        public FakeHello(Object agent) {
            this.agent = agent;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before");
            Object result = method.invoke(agent, args);
            System.out.println("after");
            return result;
        }
    }

    public void test(){
        HelloImpl helloImpl=new HelloImpl();
        FakeHello fakeHello = new FakeHello(helloImpl);

        Object proxy=Proxy.newProxyInstance(Hello.class.getClassLoader(), new Class[]{Hello.class,Hi.class}, fakeHello);
        Hello hello=(Hello)proxy;
        hello.sayHello();

        Hi hi=(Hi)proxy;
        hi.sayHi();

    }

    public static void main(String[] args) {
        ProxyTester proxyTester=new ProxyTester();
        proxyTester.test();
    }

}
