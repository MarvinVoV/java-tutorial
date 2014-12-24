package com.sun.base.classloader;

import java.net.URL;

/**
 * Created by louis on 2014/12/24.
 */
public class Example {
    public static void main(String[] args) {
        // default bootstrap class loader
        URL[] urls=sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url.toExternalForm());
        }

        // the same as follow
        System.out.println(System.getProperty("sun.boot.class.path")); // bootstrap class loader load path
        System.out.println(System.getProperty("java.ext.dirs"));       // ExtClassLoader load path
        System.out.println(System.getProperty("java.class.path"));      //AppClassLoader load path
    }
}
