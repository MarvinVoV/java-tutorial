package com.sun.base.image;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

/**
 * Created by louis on 2014/11/28.
 */
public class EnhanceContrast {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat source = Highgui.imread("D:\\testpic\\gray3.jpg", Highgui.CV_LOAD_IMAGE_GRAYSCALE);
        Mat destination = new Mat(source.rows(),source.cols(),source.type());
        Imgproc.equalizeHist(source, destination);
        Highgui.imwrite("D:\\testpic\\contrast.jpg", destination);
    }
}
