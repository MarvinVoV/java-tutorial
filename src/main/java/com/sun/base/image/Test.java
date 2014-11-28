package com.sun.base.image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by louis on 2014/11/28.
 */
public class Test extends JPanel {
    @Override
    public void paint(Graphics g) {
        Image img=createImageWithText();
        g.drawImage(img,20,20,this);
    }
    private Image createImageWithText(){
        BufferedImage bufferedImage=new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);
        Graphics g=bufferedImage.getGraphics();
        g.drawString("hello world", 20, 20);
        g.drawString("hello world", 20, 40);
        return bufferedImage;
    }

    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.getContentPane().add(new Test());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(200,200);
        frame.setVisible(true);

    }
}
