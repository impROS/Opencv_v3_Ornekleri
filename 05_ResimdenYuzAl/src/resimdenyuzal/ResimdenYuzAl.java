package resimdenyuzal;

import java.io.File;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

public class ResimdenYuzAl {

    static int indexFoto = 0;

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        File tmpFile = new File("C:\\OpenCvDeneme\\araba");
        File[] arrFile = tmpFile.listFiles();

        for (int i = 0; i < arrFile.length; i++) {
            if (arrFile[i].isFile()) {
                System.out.println(arrFile[i].getAbsolutePath());

                yuzAl(arrFile[i].getAbsolutePath());
            }

            // arrFile[i].renameTo(new File(file.getPath() + "\\" + i + ".jpg"));
        }

    }

    public static void yuzAl(String strFile) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MatOfRect faces = new MatOfRect();
                    System.out.println("");
                    CascadeClassifier faceDetect = new CascadeClassifier("C:\\Users\\impROS\\Downloads\\Compressed\\Face-detect-opencv3-master\\Face-detect-opencv3-master\\myhaarcar.xml");
                    Mat img1 = Imgcodecs.imread((strFile));

                    faceDetect.detectMultiScale(img1, faces);
                    for (Rect rect : faces.toArray()) {
                        Kirp(img1, new Rect(new Point(rect.x, rect.y), new Point(
                                rect.x + rect.width, rect.y
                                + rect.height)));
                    }
                } catch (Exception e) {
                    System.err.println("Hata : " + e.getMessage());
                }

            }
        });
        t.start();
    }

    public static void Kirp(Mat image, Rect yuz) {

        Mat yeniGoruntu = new Mat(image, yuz);
        Imgcodecs.imwrite("C:\\OpenCvDeneme\\araba\\output\\" + indexFoto++ + ".jpg", yeniGoruntu);
        System.out.println("Kayit Edildi");

    }
}
