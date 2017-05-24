/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package sun.utils;


import org.apache.commons.codec.binary.Base64;
import org.junit.Test;


/**
 * Created by hufeng on 16/11/2.
 */
public class MyTest {

    @Test
    public void testStr() throws Exception{
        // [b64]6JGj55uI[/b64]
//        byte[] source = Base64.encodeBase64("三毛".getBytes());
//        byte[] target = Base64.decodeBase64(source);
        byte[] target = Base64.decodeBase64("[b64]5YCp6aKW[/b64]");
        String str = new String(target);
        System.out.println(str.length());
        System.out.println(str);

        String t = "[b64]5YCp6aKW[/b64]";
        t = t.replaceAll("\\[b64\\]", "").replaceAll("\\[/b64\\]","");
        String v= new String(Base64.decodeBase64(t.getBytes()));
        System.out.println("[b64]" + new String(Base64.encodeBase64(v.getBytes())) + "[/b64]");
    }



}
