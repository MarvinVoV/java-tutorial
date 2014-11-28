package com.sun.base.io;

import java.io.*;

/**
 * Created by louis on 2014/11/28.
 */
public class IOTest {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream(new File("D:/smb.conf"));
        int len;
        byte[] buffer = new byte[1024];
        while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
            System.out.println(new String(buffer,0,buffer.length));
        }
        inputStream.close();

    }
}
