package com.sun.base.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by louis on 2014/11/28.
 */
public class GrayScale {
    public static void gray(File input, File output, AlgorithmType type) throws IOException {
        BufferedImage image = ImageIO.read(input);
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color c = new Color(image.getRGB(j, i));
                int sum = 0;
                switch (type) {
                    case MaxValue:
                        sum = Math.max(c.getRed(), Math.max(c.getGreen(), c.getBlue()));
                        break;
                    case AverageValue:
                        sum = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                        break;
                    case WeightValue:
                        int red = (int) (c.getRed() * 0.299);
                        int green = (int) (c.getGreen() * 0.587);
                        int blue = (int) (c.getBlue() * 0.114);
                        sum = (red + green + blue);
                        break;
                }
                Color newColor = new Color(sum, sum, sum);
                image.setRGB(j, i, newColor.getRGB());
            }
        }
        ImageIO.write(image, "jpg", output);
    }

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\testpic\\2.jpg");
        int i=1;
        for (AlgorithmType type : AlgorithmType.values()) {
            File output = new File("D:\\testpic\\gray"+(i++)+".jpg");
            gray(file, output, type);
        }
    }
    public enum AlgorithmType {
        MaxValue,
        AverageValue,
        WeightValue
    }
}
