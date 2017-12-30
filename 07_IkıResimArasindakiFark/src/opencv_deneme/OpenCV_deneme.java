package opencv_deneme;

import java.util.Arrays;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_COLOR;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import org.opencv.imgproc.Imgproc;

public class OpenCV_deneme {

    public static void main(String[] args) {
        double[] arrBeyaz = new double[3];
        arrBeyaz[0] = 255;
        arrBeyaz[1] = 255;
        arrBeyaz[2] = 255;

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat im1 = Imgcodecs.imread("C:\\OpenCvDeneme\\arkaplan_temizle\\1.jpg");
        Mat im2 = Imgcodecs.imread("C:\\OpenCvDeneme\\arkaplan_temizle\\2.jpg");
        Mat im3 = Imgcodecs.imread("C:\\OpenCvDeneme\\arkaplan_temizle\\2.jpg");

        Imgproc.cvtColor(im1, im1, Imgproc.COLOR_RGB2GRAY);
        Imgproc.cvtColor(im2, im2, Imgproc.COLOR_RGB2GRAY);

        for (int i = 0; i < im1.rows(); i++) {
            for (int j = 0; j < im1.cols(); j++) {
                double[] arr1 = im1.get(i, j);
                double[] arr2 = im2.get(i, j);
                if (compImg(arr1, arr2)) {
                    im3.put(i, j, arrBeyaz);
                }
            }
        }
        //Imgproc.blur(im2, im2, new Size(7, 7));
        Imgcodecs.imwrite("C:\\OpenCvDeneme\\arkaplan_temizle\\fark.jpg", im3);
    }

    public static boolean compImg(double[] arr1, double[] arr2) {
        int threshold = 5;
        for (int i = 0; i < arr1.length; i++) {
            if ((arr1[i] > arr2[i] + threshold) || (arr1[i] < arr2[i] - threshold)) {
                return false;
            }
        }
        return true;
    }
//<editor-fold defaultstate="collapsed" desc="Eski">

    public static void a() {

        Mat src = imread("C:\\OpenCvDeneme\\3.jpg", CV_LOAD_IMAGE_COLOR); //load  image
        System.out.println(src.channels());
        for (int i = 0; i < 100; i++) {
            src = imread("C:\\OpenCvDeneme\\out_" + i + ".jpg", CV_LOAD_IMAGE_COLOR); //load  image
            System.out.println(src.channels());;
        }
        int satir = src.rows();
        int sutun = src.cols();

        for (int i = 0; i < satir; i++) {
            for (int j = 0; j < sutun; j++) {
                double[] tmpRenkUzayi = src.get(i, j);
                tmpRenkUzayi[0] = tmpRenkUzayi[0] + 50;
                src.put(i, j, tmpRenkUzayi);
                for (double d : tmpRenkUzayi) {
                    System.out.print(d + " ");
                }
                System.out.println("");
            }
        }
        Imgcodecs.imwrite("C:\\OpenCvDeneme\\Ders_03\\out.jpg", src);
    }
//</editor-fold>

}
