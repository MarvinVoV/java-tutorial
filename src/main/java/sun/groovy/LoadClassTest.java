package sun.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.junit.Test;

import java.io.File;

/**
 * Created by marvin on 2017/09/06.
 */

public class LoadClassTest {

    @Test
    public void testLoadClass() throws Exception{
        GroovyClassLoader gcl = new GroovyClassLoader();
        Class clazz = gcl.parseClass(new File("/Users/marvin/temp/Hello.groovy"));
        GroovyObject groovyObject = (GroovyObject) clazz.newInstance();
        groovyObject.invokeMethod("setName", "hello");
        String name = (String) groovyObject.invokeMethod("getName", null);
        System.out.println(name);
    }

    @Test
    public void testLoadInnerClass () throws Exception {
        GroovyClassLoader gcl = new GroovyClassLoader();
        Class clazz = gcl.parseClass(new File("/Users/marvin/temp/MyCallback.groovy"));
        TestCallback callback = (TestCallback)clazz.newInstance();
        System.out.println(callback.callback());
    }

}
