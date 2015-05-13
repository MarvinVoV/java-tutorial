package com.sun.base.jvm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by louis on 2015/5/12.
 */
public class Boo {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader(new File("D:\\data\\20150224-测试数据\\20150511\\(加入高度）绿地东上海星港景苑天线经纬度统计.csv"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
