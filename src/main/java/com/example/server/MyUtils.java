package com.example.server;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

import static org.opencv.imgcodecs.Imgcodecs.imread;

public class MyUtils {
    public static double CmpPic(String src, String des) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("\n==========直方图比较==========");
        //自定义阈值
        //相关性阈值，应大于多少，越接近1表示越像，最大为1
        double HISTCMP_CORREL_THRESHOLD = 0.9;//0.7
        //卡方阈值，应小于多少，越接近0表示越像
        double HISTCMP_CHISQR_THRESHOLD = 0.5;//2
        //交叉阈值，应大于多少，数值越大表示越像
        double HISTCMP_INTERSECT_THRESHOLD = 2;//1.2
        //巴氏距离阈值，应小于多少，越接近0表示越像
        double HISTCMP_BHATTACHARYYA_THRESHOLD = 0.3;//0.3

        try {

            long startTime = System.currentTimeMillis();

            Mat mat_src = imread(src);
            Mat mat_des = imread(des);

            if (mat_src.empty() || mat_des.empty()) {
                throw new Exception("no file.");
            }

            org.opencv.core.Mat hsv_src = new org.opencv.core.Mat();
            org.opencv.core.Mat hsv_des = new org.opencv.core.Mat();

            // 转换成HSV
            Imgproc.cvtColor(mat_src, hsv_src, Imgproc.COLOR_BGR2HSV);
            Imgproc.cvtColor(mat_des, hsv_des, Imgproc.COLOR_BGR2HSV);

            List<Mat> listImg1 = new ArrayList<>();
            List<org.opencv.core.Mat> listImg2 = new ArrayList<>();
            listImg1.add(hsv_src);
            listImg2.add(hsv_des);

            MatOfFloat ranges = new MatOfFloat(0, 255);
            MatOfInt histSize = new MatOfInt(50);
            MatOfInt channels = new MatOfInt(0);

            org.opencv.core.Mat histImg1 = new org.opencv.core.Mat();
            org.opencv.core.Mat histImg2 = new org.opencv.core.Mat();

            //org.bytedeco.javacpp中的方法不太了解参数，所以直接上org.opencv中的方法，所以需要加载一下dll，System.load("D:\\soft\\openCV3\\opencv\\build\\java\\x64\\opencv_java345.dll");
            //opencv_imgproc.calcHist(images, nimages, channels, mask, hist, dims, histSize, ranges, uniform, accumulate);
            Imgproc.calcHist(listImg1, channels, new org.opencv.core.Mat(), histImg1, histSize, ranges);
            Imgproc.calcHist(listImg2, channels, new org.opencv.core.Mat(), histImg2, histSize, ranges);

            org.opencv.core.Core.normalize(histImg1, histImg1, 0d, 1d, Core.NORM_MINMAX, -1,
                    new org.opencv.core.Mat());
            org.opencv.core.Core.normalize(histImg2, histImg2, 0d, 1d, Core.NORM_MINMAX, -1,
                    new org.opencv.core.Mat());

            double result0, result1, result2, result3;
            result0 = Imgproc.compareHist(histImg1, histImg2, Imgproc.HISTCMP_CORREL);
            result1 = Imgproc.compareHist(histImg1, histImg2, Imgproc.HISTCMP_CHISQR);
            result2 = Imgproc.compareHist(histImg1, histImg2, Imgproc.HISTCMP_INTERSECT);
            result3 = Imgproc.compareHist(histImg1, histImg2, Imgproc.HISTCMP_BHATTACHARYYA);

            System.out.println("相关性（度量越高，匹配越准确 [基准：" + HISTCMP_CORREL_THRESHOLD + "]）,当前值:" + result0);
            System.out.println("卡方（度量越低，匹配越准确 [基准：" + HISTCMP_CHISQR_THRESHOLD + "]）,当前值:" + result1);
            System.out.println("交叉核（度量越高，匹配越准确 [基准：" + HISTCMP_INTERSECT_THRESHOLD + "]）,当前值:" + result2);
            System.out.println("巴氏距离（度量越低，匹配越准确 [基准：" + HISTCMP_BHATTACHARYYA_THRESHOLD + "]）,当前值:" + result3);

            String where = "";
            //一共四种方式，有三个满足阈值就算匹配成功
            int count = 0;
            if (result0 > HISTCMP_CORREL_THRESHOLD) {
                count++;
                where += 1;
            }
            if (result1 < HISTCMP_CHISQR_THRESHOLD) {
                count++;
                where += 2;
            }
            if (result2 > HISTCMP_INTERSECT_THRESHOLD) {
                count++;
                where += 3;
            }
            if (result3 < HISTCMP_BHATTACHARYYA_THRESHOLD) {
                count++;
                where += 4;
            }
            int retVal = 0;
            if (count >= 3) {
                //这是相似的图像
                retVal = 1;
            }
            System.out.println(where);
            long estimatedTime = System.currentTimeMillis() - startTime;

            System.out.println("花费时间= " + estimatedTime + "ms");

            return retVal;
        } catch (Exception e) {
            System.out.println("例外:" + e);
        }
        return 0;
    }

}
