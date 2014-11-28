package com.sun.base.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by louis on 2014/11/28.
 */
public class Pixel {
    public static void outputPix(File file) throws IOException {
        BufferedImage image= ImageIO.read(file);
        int count=0;
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                count++;
                Color c = new Color(image.getRGB(j, i));
                System.out.printf("NO:%d ,Red:%d,Green:%d,Blue:%d\n", count, c.getRed(), c.getGreen(), c.getBlue());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\testpic\\output\\201306135p13492911E\\F\\14.jpg");
        outputPix(file);
    }
}
