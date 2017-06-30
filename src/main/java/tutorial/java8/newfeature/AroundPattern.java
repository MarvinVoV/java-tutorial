/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package tutorial.java8.newfeature;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author hufeng
 * @version $Id: AroundPattern, v0.1 2017年06月29日 10:01 PM hufeng Exp $
 */
public class AroundPattern {

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("/etc/hosts"))) {
            return p.process(br);
        }
    }

    public static void main(String[] args) throws IOException{
        String oneLine = processFile((BufferedReader br) -> br.readLine());
        String twoLine = processFile((BufferedReader br) -> br.readLine() + br.readLine());

        System.out.println(oneLine);
        System.out.println(twoLine);
    }
}

