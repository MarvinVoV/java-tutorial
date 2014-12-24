package com.sun.base.classloader;

/**
 * Created by louis on 2014/12/24.
 */
public class ClassLoaderHierarchy {
    public static void main(String[] args) {
        ClassLoaderHierarchy loaderHierarchy=new ClassLoaderHierarchy();
        ClassLoader classLoader=loaderHierarchy.getClass().getClassLoader();
        System.out.println(classLoader);
        while((classLoader=classLoader.getParent())!=null){
            System.out.println(classLoader);
        }
    }
}
