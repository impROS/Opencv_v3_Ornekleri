/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg09_threshold;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Main {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        for (int i = 0; i < 50; i++) {
            try {
                threshold(i);
            } catch (Exception e) {
            }

        }
    }

    public static void threshold(int i) {

        Mat kaynakGoruntu = new Mat();
        kaynakGoruntu = Imgcodecs.imread("C:\\OpenCvDeneme\\3.jpg");
        Mat hedefGoruntu = new Mat();
        int thresh = 150;
        int maxDeger = 255;
        Imgproc.threshold(kaynakGoruntu, hedefGoruntu, thresh, maxDeger, i);
        Imgcodecs.imwrite("C:\\OpenCvDeneme\\threshold\\" + i + "_out.jpg", hedefGoruntu);
        System.out.println("Thresholding uygulandı.");
    }
}
