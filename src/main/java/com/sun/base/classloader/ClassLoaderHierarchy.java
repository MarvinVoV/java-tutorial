package com.sun.base.classloader;

import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by louis on 2014/12/24.
 */
public class ClassLoaderHierarchy {
    public static void main(String[] args) {
        ClassLoaderHierarchy loaderHierarchy=new ClassLoaderHierarchy();
        ClassLoader classLoader=loaderHierarchy.getClass().getClassLoader();
        while(classLoader!=null){
            System.out.println(classLoader);
            System.out.println(Arrays.toString(((URLClassLoader)(classLoader)).getURLs()).replaceAll(",","\n"));
            classLoader=classLoader.getParent();
        }

        System.out.println("==================");
        //Print environment
        Map<String, String> env = System.getenv();
        for (Map.Entry<String, String> entry : env.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}
