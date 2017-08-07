package model;

import service.ImageBlockService;
import service.Impl.ImageBlockServiceImpl;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class ImageBlock extends AbstractImageBlock implements Runnable {

    private double diffLimitInPercentage = 10.0;

    private BufferedImage image1, image2;

    private LinkedList<Integer> registeredDiffByX = new LinkedList<>();

    private LinkedList<Integer> registeredDiffByY = new LinkedList<>();

    private ImageBlockService imageBlockService = new ImageBlockServiceImpl();

    public ImageBlock(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public ImageBlock(BufferedImage image1, BufferedImage image2, int x1, int x2, int y1, int y2) {
        this.image1 = image1;
        this.image2 = image2;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public void setImageToCompare(BufferedImage image1, BufferedImage image2) {
        this.image1 = image1;
        this.image2 = image2;
    }

    @Override
    public void run() {
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                int pixel1 = image1.getRGB(x, y);
                int pixel2 = image2.getRGB(x, y);
                double diffInPercentage = imageBlockService.comparePixels(pixel1, pixel2);
                if (diffInPercentage >= diffLimitInPercentage) {
                    registeredDiffByX.add(x);
                    registeredDiffByY.add(y);
                }
            }
        }
    }

    public double getDiffLimitInPercentage() {
        return diffLimitInPercentage;
    }

    public void setDiffLimitInPercentage(double diffLimitInPercentage) {
        this.diffLimitInPercentage = diffLimitInPercentage;
    }

    public LinkedList<Integer> getRegisteredDiffByX() {
        return registeredDiffByX;
    }

    public LinkedList<Integer> getRegisteredDiffByY() {
        return registeredDiffByY;
    }
}
