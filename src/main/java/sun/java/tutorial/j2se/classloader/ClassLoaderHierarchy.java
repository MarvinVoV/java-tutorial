package sun.java.tutorial.j2se.classloader;

import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * Created by sunyamorn on 3/3/16.
 */
public class ClassLoaderHierarchy {
    public static void main(String[] args) {
        ClassLoaderHierarchy classLoaderHierarchy = new ClassLoaderHierarchy();
        ClassLoader classLoader = classLoaderHierarchy.getClass().getClassLoader();
        while (classLoader != null) {
            String loadPath = Arrays.toString(((URLClassLoader) classLoader).getURLs()).replace(",", "\n");
            System.out.println(classLoader);
            System.out.println("\t" + loadPath);
            classLoader = classLoader.getParent();
        }
    }
}
