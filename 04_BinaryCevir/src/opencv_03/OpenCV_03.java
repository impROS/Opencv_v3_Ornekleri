package opencv_03;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class OpenCV_03 {

    public static void main(String[] args) {
        int toplam = 0;
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat im1 = new Mat();
        im1 = Imgcodecs.imread("C:\\OpenCvDeneme\\Ders_03\\3.jpg");
        int kanalSayisi = im1.channels();
        System.out.println("Kanal Sayisi : " + kanalSayisi);
//f(x)=4x+3;
        for (int i = 0; i < im1.rows(); i++) {
            for (int j = 0; j < im1.cols(); j++) {
                double[] arrRenkUzayi = im1.get(i, j);
//                arrRenkUzayi[0] = arrRenkUzayi[0] + 50;
                toplam = (int) (arrRenkUzayi[0] + arrRenkUzayi[1] + arrRenkUzayi[2]) / 3;
                if (toplam < 123) {
                    arrRenkUzayi[0] = 0;
                    arrRenkUzayi[1] = 0;
                    arrRenkUzayi[2] = 0;
                    im1.put(i, j, arrRenkUzayi);
                } else {
                    arrRenkUzayi[0] = 255;
                    arrRenkUzayi[1] = 255;
                    arrRenkUzayi[2] = 255;
                    im1.put(i, j, arrRenkUzayi);
                }

            }
        }
        Imgcodecs.imwrite("C:\\OpenCvDeneme\\Ders_03\\3_output3.jpg", im1);
    }

}
