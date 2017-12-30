package opencv_02;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class OpenCV_02 {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat img1 = new Mat();
        img1 = Imgcodecs.imread("C:\\OpenCvDeneme\\threshold\\2_out.jpg");
     

        int satir = img1.rows();
        int sutun = img1.cols();

        for (int i = 0; i < satir; i++) {
            for (int j = 0; j < sutun; j++) {
                double[] tmpRenkUzayi = img1.get(i, j);

                for (double d : tmpRenkUzayi) {
                    System.out.print(d + " ");
                }
                System.out.println("");
            }
        }

    }

}
