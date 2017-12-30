package maxpoolingdeneme;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class MaxPoolingDeneme {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat img1 = new Mat();
        Mat imgMaxPool = new Mat();
        img1 = Imgcodecs.imread("C:\\OpenCvDeneme\\Ders_03\\3.jpg");
        int im1Rows = img1.rows();
        int im1Cols = img1.cols();

        imgMaxPool.create((im1Rows / 4), (im1Rows / 4), CvType.CV_8U);

        System.out.println("Sutun : " + img1.cols());
        System.out.println("Satir: " + img1.rows());

        int satir = 0;
        int sutun = 0;
        System.out.println("im1Rows : " + im1Rows);
        System.out.println("im1Cols : " + im1Cols);
//<editor-fold defaultstate="collapsed" desc="Gerekirse Resmi Tekrar Boyutlandir">
        Size imgSize = new Size();
        imgSize = img1.size();
        boolean isResize = false;
        double height, width;
        height = imgSize.height;
        width = imgSize.width;
        System.out.println("Height  : " + height);
        System.out.println("Width : " + width);

        if (height % 4 != 0) {
            isResize = true;
            height = (height % 4) + height;
        }

        if (width % 4 != 0) {
            isResize = true;
            width = (width % 4) + width;
        }

        System.out.println(" Yeni Height  : " + height);
        System.out.println("Yeni Width : " + width);

        if (isResize) {
            Imgproc.resize(img1, img1, new Size(height, width));
        }
//</editor-fold>

       
        for (int i = 0; i < im1Rows; i += 4) {
            for (int j = 0; j < im1Cols; j += 4) {

                double[] pixel1 = img1.get(i + 1, j);
                double[] pixel2 = img1.get(i + 1, j + 1);
                double[] pixel3 = img1.get(i, j + 1);
                double[] pixel4 = img1.get(i, j);
                double[] arrNewRGB = new double[3];
                //ortalamayi aldik
                double ort1, ort2, ort3, ort4;
                ort1 = (pixel1[0] + pixel1[1] + pixel1[2]) / 3.0;
                ort2 = (pixel2[0] + pixel2[1] + pixel2[2]) / 3.0;
                ort3 = (pixel3[0] + pixel3[1] + pixel3[2]) / 3.0;
                ort4 = (pixel4[0] + pixel4[1] + pixel4[2]) / 3.0;

                //ortalamasi en buyuk olani al
                double max1 = Math.max(ort1, ort2);
                double max2 = Math.max(ort3, ort4);
                double max3 = Math.max(max1, max2);

                //en buyuk ortalamaya sahip pixeli yazdir
                if (ort1 == max3) {
                    imgMaxPool.put(satir++, sutun++, pixel1);
                    //System.out.println("1 : ");
                } else if (ort2 == max3) {
                    imgMaxPool.put(satir++, sutun++, pixel2);
                   // System.out.println("2 : ");
                } else if (ort3 == max3) {
                    imgMaxPool.put(satir++, sutun++, pixel3);
                    //System.out.println("3 : ");
                } else if (ort4 == max3) {
                    imgMaxPool.put(satir++, sutun++, pixel4);
                  //  System.out.println("4 : ");
                } else {

                }

            }
        }
//        Imgproc.resize(img1, img1, new Size(160,160));
        Imgcodecs.imwrite("C:\\OpenCvDeneme\\Ders_03\\maxPool.jpg", imgMaxPool);
    }

}
