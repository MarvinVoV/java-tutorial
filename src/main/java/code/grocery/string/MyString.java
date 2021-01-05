/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package code.grocery.string;

/**
 * @author hufeng
 * @version : MyString.java, v 0.1 2021年01月05日 11:26 上午 hufeng Exp $
 */
public class MyString {
    public static void main(String[] args) {
        String data = "20210105";
        boolean result = data.matches("\\d{8}");
        System.out.println(result);

    }
}