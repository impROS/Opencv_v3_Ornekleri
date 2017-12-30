package opencv_erozyon;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;

import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class OpenCv_Erozyon {

    public static void main(String[] args) {
        genişletme();
    }

    public static void genişletme() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat src_img = new Mat();
        Mat dst_img = new Mat();
        src_img = Imgcodecs.imread("C:\\OpenCvDeneme\\trafik\\502.jpg");

        Imgproc.dilate(src_img, dst_img, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(15, 15)));
        Imgcodecs.imwrite("C:\\OpenCvDeneme\\trafik\\502_output23.jpg", dst_img);
        Imgproc.morphologyEx(src_img, dst_img, Imgproc.MORPH_OPEN, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(25, 25)));
           Imgcodecs.imwrite("C:\\OpenCvDeneme\\trafik\\502_output_open.jpg", dst_img);
           
           Imgproc.morphologyEx(src_img, dst_img, Imgproc.MORPH_CLOSE, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(25,25)));
             Imgcodecs.imwrite("C:\\OpenCvDeneme\\trafik\\502_output_closing.jpg", dst_img);
    }

    public static void erozyon() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src_img = new Mat();
        src_img = Imgcodecs.imread("C:\\OpenCvDeneme\\50.jpg");

        Mat dst_img = new Mat();
        Imgproc.erode(src_img, dst_img, Imgproc.getStructuringElement(Imgproc.MORPH_ERODE, new Size(5, 5)));
        Imgcodecs.imwrite("C:\\OpenCvDeneme\\502.jpg", dst_img);
    }
}
