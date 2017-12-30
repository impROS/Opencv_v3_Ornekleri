package videodosyasiokumaornegi;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

//OpenCV 3.1
public class VideoDosyasiOkumaOrnegi {

    public static void main(String[] args) {
        String strPath = "C:\\OpenCvDeneme\\Otoyol_Araba_Tanima_Projesi\\";
        String okunacakVideo = strPath + "\\oto1.mp4";

        String kaydedilecekYer = strPath + "\\Oto1_Goruntuler";
        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            Mat frame = new Mat();
            VideoCapture videoCapture = new VideoCapture();
            // Video dosyasını aç
            videoCapture.open(okunacakVideo);
            int i = 5;
            int videoIndex = 0;
            // ağlantı açıldı ise

            if (videoCapture.isOpened()) {

                while (videoCapture.read(frame)) {
                    if (i % 30 == 0) {
                        Imgcodecs.imwrite(kaydedilecekYer + "\\" + videoIndex + ".jpg", frame);
                        videoIndex++;
                    }
                    i++;
                }

            }
        } catch (Exception e) {
            System.err.println("Hata : " + e.getMessage());
        }

    }

}
