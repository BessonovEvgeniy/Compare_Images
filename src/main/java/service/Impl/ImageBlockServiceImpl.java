package service.Impl;

import service.ImageBlockService;

public class ImageBlockServiceImpl implements ImageBlockService {

    public Double comparePixels(int pixel1, int pixel2) {

        double diff = 0;
        int red1 = (pixel1 >> 16) & 0xff;
        int green1 = (pixel1 >> 8) & 0xff;
        int blue1 = (pixel1) & 0xff;
        int red2 = (pixel2 >> 16) & 0xff;
        int green2 = (pixel2 >> 8) & 0xff;
        int blue2 = (pixel2) & 0xff;

        diff += Math.abs(red1 - red2);
        diff += Math.abs(green1 - green2);
        diff += Math.abs(blue1 - blue2);

        double avgDiff = diff / 3;

        double percentage = (avgDiff / 255) * 100;

        return percentage;
    }

    public void printPixelARGB(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        System.out.println("Alpha: " + alpha + ", Red: " + red + ", Green:" + green + ", Blue: " + blue);
    }
}
