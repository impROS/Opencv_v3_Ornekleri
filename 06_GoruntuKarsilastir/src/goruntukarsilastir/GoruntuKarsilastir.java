package goruntukarsilastir;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class GoruntuKarsilastir {

    public static void main(String[] args) throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String compImg = "suleyman";
        int enCokEslesen = 0;
        String enCokEslesenFotoAdi = "";
        double ortalamaBenzerlik = 0;
        int keyMatch = 0;
       File klasor = new File("C:\\OpenCvDeneme\\yuz_ornekleri_db\\Suleyman");
        //File klasor = new File("C:\\OpenCvDeneme\\goruntu_karsilastir\\asift");
        File[] arrKlasor = klasor.listFiles();
        for (File file : arrKlasor) {
            if (file.isDirectory()) {
                File tmpFile = new File(file.getAbsolutePath());
                File[] arrFile = tmpFile.listFiles();

                for (int i = 0; i < arrFile.length; i++) {
                    //   System.out.println(arrFile[i].getAbsolutePath());
                    // arrFile[i].renameTo(new File(file.getPath() + "\\" + i + ".jpg"));
                    //    grilestir(arrFile[i].getAbsolutePath());

                    try {
                        keyMatch = 0;
                        Mat img1 = Imgcodecs.imread(("C:\\OpenCvDeneme\\" + compImg + ".jpg"));
                        Mat img2 = Imgcodecs.imread(arrFile[i].getAbsolutePath());

                        // Mat img2 = Imgcodecs.imread(("C:\\OpenCvDeneme\\goruntu_karsilastir\\3.jpg"));
                        Mat img3 = new Mat();

                        int im1Rows = img1.rows();
                        int im1Cols = img1.cols();

                        Imgproc.resize(img2, img2, new Size(im1Rows, im1Cols));
                        //System.out.println("img1: " + img1.toString() + "Rows : " + im1Rows + " ,  Cols : " + im1Cols);

                        int im2Rows = img2.rows();
                        int im2Cols = img2.cols();
                        //  System.out.println("img2: " + img1.toString() + "Rows : " + im2Rows + " ,  Cols : " + im2Cols);
                        img3.create(im1Rows + im2Rows, im1Cols, 22);

                        img3 = goruntuYaz(img1, img3, 0);
                        img3 = goruntuYaz(img2, img3, im1Rows);
                        for (int j = 0; j < im1Rows; j++) {
                            for (int k = 0; k < im1Rows; k++) {
                                boolean[] arrPixelBenzerlik = new boolean[2];
                                int tmpJ=j;
                                for (int l = 0; l < 2; l++) {
                                    double[] arrTmp1;
                                    double[] arrTmp2;
                                    if (j + l < im1Rows) {
                                        arrTmp1 = img1.get(j + l, k);
                                        arrTmp2 = img2.get(j + l, k);
                                    } else {
                                        arrTmp1 = img1.get(tmpJ--, k);
                                        arrTmp2 = img2.get(tmpJ--, k);
                                    }
                                    if ((arrTmp1[0] != 255.0 && arrTmp1[1] != 255.0 && arrTmp1[2] != 255.0)
                                            && (arrTmp1[0] != 0 && arrTmp1[1] != 0 && arrTmp1[2] != 0)) {
                                        if (arrTmp1[0] == arrTmp2[0] && arrTmp1[1] == arrTmp2[1] && arrTmp1[2] == arrTmp2[2]) {
                                            arrPixelBenzerlik[l] = true;
                                        } else {
                                            arrPixelBenzerlik[l] = false;
                                        }
                                    }

                                }
                                boolean isSame = diziTrueMu(arrPixelBenzerlik);
                                if (isSame) {
                                    if (keyMatch % 1 == 0) {
                                        Imgproc.circle(img3, new Point(j, k), 2, new Scalar(0, 0, 255), 2);
                                        Imgproc.circle(img3, new Point(j, k + im1Cols), 2, new Scalar(0, 0, 255), 2);
                                        Imgproc.line(img3, new Point(j, k), new Point(j, k + im1Cols), new Scalar(255, 255, 255));
                                        Imgproc.line(img3, new Point(j + 1, k), new Point(j + 1, k + im1Cols), new Scalar(255, 255, 255));
                                    }
                                    keyMatch++;
                                }

                            }
                        }
                        if (enCokEslesen < keyMatch) {
                            //  if (!compImg.equalsIgnoreCase(i + "")) {
                            enCokEslesen = keyMatch;
                            enCokEslesenFotoAdi = arrFile[i].getAbsolutePath();
                            // }

                        }

                       // Imgcodecs.imwrite("C:\\OpenCvDeneme\\goruntu_karsilastir\\asift\\" + i+ "_comp_output_" + keyMatch + ".jpg", img3);
                    } catch (Exception e) {
                        //System.out.println("Hata : " + e.getMessage() + e.getStackTrace()[0]);
                        System.out.println("Hata : " + e.getMessage());
                    }
                    ortalamaBenzerlik = +keyMatch;
                }
                ortalamaBenzerlik = ortalamaBenzerlik / arrFile.length;
                System.out.println(file.getName() + " Ortalama Benzerlik " + ortalamaBenzerlik);
            }

        }

        if (enCokEslesen > 100) {
            System.out.println(
                    "Eslesme Bulundu : " + enCokEslesenFotoAdi + "_Oran_" + enCokEslesen);
        } else {

            System.out.println("Saglikli Bir Eslesme Bulunamadi");;
            System.out.println(
                    "En Cok Esleyen : " + enCokEslesenFotoAdi + "_Oran_" + enCokEslesen);
        }

    }

    public static boolean diziTrueMu(boolean[] arrTmp) {
        for (boolean d : arrTmp) {
            if (d == false) {
                return false;
            }
        }
        return true;
    }

    public static String griResimOku(String strPath) throws IOException {
        try {

            File input = new File(strPath);
            BufferedImage image = ImageIO.read(input);

            byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
            Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
            mat.put(0, 0, data);

            Mat mat1 = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC1);
            Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);

            byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int) (mat1.elemSize())];
            mat1.get(0, 0, data1);
            BufferedImage image1 = new BufferedImage(mat1.cols(), mat1.rows(), BufferedImage.TYPE_BYTE_GRAY);
            image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);

            File ouptut = new File(strPath);
            ImageIO.write(image1, "jpg", ouptut);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return strPath;
    }

    public static Mat goruntuYaz(Mat kaynakImg, Mat hedefImg, int bas) {
        // System.out.println("HedefImg row : " + hedefImg.rows());
        for (int i = bas; i < bas + kaynakImg.rows(); i++) {
            for (int j = 0; j < kaynakImg.cols(); j++) {
                hedefImg.put(i, j, kaynakImg.get(i - bas, j));
            }
        }
        return hedefImg;
    }

}
