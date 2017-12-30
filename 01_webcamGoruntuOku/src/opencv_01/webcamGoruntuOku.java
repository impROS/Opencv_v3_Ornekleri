package opencv_01;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

public class webcamGoruntuOku {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat img = new Mat();

        VideoCapture videoKaydet = new VideoCapture();
        videoKaydet.open(0);
        if (videoKaydet.isOpened()) {
            videoKaydet.read(img);
            videoKaydet.release();
        }
        Imgcodecs.imwrite("C:\\opencv\\build\\java\\deneme.jpg", img);
    }

}
