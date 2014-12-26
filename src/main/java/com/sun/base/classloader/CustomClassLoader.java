package com.sun.base.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by louis on 2014/12/26.
 */
public class CustomClassLoader extends ClassLoader {
    //read file from disk
    private byte[] getBytes(String filename) throws IOException {
        File file = new File(filename);
        long len = file.length();
        byte[] raw = new byte[(int) len];
        FileInputStream fin = new FileInputStream(file);
        int r = fin.read(raw);
        if (r != len) {
            throw new IOException("Can't read all file content");
        }
        fin.close();
        return raw;
    }

    //Spawn a process to compile the java source code file
    //specified in the 'javaFile' parameter,Return a true if
    //the compilation worked,false otherwise
    private boolean compile(String javaFile) throws IOException {
        System.out.println("Compiling " + javaFile + " ...");
        //Start up the compiler
        Process p = Runtime.getRuntime().exec("javac " + javaFile);
        //Wait for it to finish running
        try {
            p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int ret = p.exitValue();
        return ret == 0;
    }
    //The heart of the ClassLoader -- automatically compile
    //source as necessary when looking for class files


    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        //Our goal is to get a Class object
        Class clazz = null;
        //First,see if we've already dealt with this one
        clazz = findLoadedClass(name);

        //Create a pathname from the class name
        String fileSub = name.replace('.', '/');
        //Build objects pointing to the source code (.java) and object code (.class)
        String javaFilename = fileSub + ".java";
        String classFilename = fileSub + ".class";

        File javaFile = new File(javaFilename);
        File classFile = new File(classFilename);

        //First, see if we want to try compiling,We do if (a) there
        //is source code,and either (b0) there is no object code,
        //or (b1) there is object code,but it's older than the source
        if (javaFile.exists() && (!classFile.exists() || javaFile.lastModified() > classFile.lastModified())) {
            try {
                //Try to compile it. If this doesn't work,then
                //we must declare failure,(It's not good enough to use
                //and already-existing,but out-of-date,classFile)
                if (!compile(javaFilename) || !classFile.exists()) {
                    throw new ClassNotFoundException("Compile failed: " + javaFilename);
                }
            } catch (IOException e) {
                //Another place where we might come to if we fail
                // to compile
                throw new ClassNotFoundException(e.toString());
            }
        }
        //Let's try to load up the raw bytes,assuming they were
        //property compiled, or didn't need to be compiled
        try {
            byte[] raw = getBytes(classFilename);
            //try to turn them in a class
            clazz = defineClass(name, raw, 0, raw.length);
        } catch (IOException e) {
            // This is not a failure!  If we reach here, it might
            // mean that we are dealing with a class in a library,
            // such as java.lang.Object
        }
        //System.out.println( "defineClass: "+clazz );
        // Maybe the class is in a library -- try loading
        // the normal way
        if (clazz == null) {
            clazz = findSystemClass(name);
        }
        //System.out.println( "findSystemClass: "+clazz );
        // Resolve the class, if any, but only if the "resolve"
        // flag is set to true
        if (resolve && clazz != null)
            resolveClass(clazz);

        // If we still don't have a class, it's an error
        if (clazz == null)
            throw new ClassNotFoundException(name);

        // Otherwise, return the class
        return clazz;
    }
    //http://www.ibm.com/developerworks/java/tutorials/j-classloader/j-classloader.html
}
