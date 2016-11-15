/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package sun.tutorial.exception;

/**
 * Created by hufeng on 16/11/15.
 *
 * If an exception is thrown in the try block, whether this code will continue to be executed
 */
public class ExceptionScene1 {
    public static void main(String[] args) {
        try{
            System.out.println("try block");
            System.out.println(3/0);
        }catch (Exception e){
            throw e;
        }finally {
            System.out.println("executed");  // It will be executed
        }
    }
}
