package service.Impl;

import exceptions.InvalidImageException;
import model.ImageBlock;
import service.ImageService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImageServiceImpl implements ImageService {

    public boolean isValidImageSize(BufferedImage image1, BufferedImage image2) throws InvalidImageException {

        boolean isNoImages = (image1 == null || image2 == null);

        if (isNoImages) {
            throw new InvalidImageException("Exception. No imeges fouund.");
        }

        int width1 = image1.getWidth();
        int height1 = image1.getHeight();

        int width2 = image2.getWidth();
        int height2 = image2.getHeight();

        boolean isDifSize = (width1 != width2 || height1 != height2);

        if (isDifSize) {
            throw new InvalidImageException("Exception. Images should be the same size.");
        }
        return true;
    }

    public void registerDifferenceInImage(BufferedImage image,
                                          List<Integer> registeredDifByX,
                                          List<Integer> registeredDifByY) {

        if (registeredDifByX.size() != registeredDifByY.size()) {
            System.out.println("Something wrong. Difference data information are currupted.");
        }
        for (int index = 0; index < registeredDifByX.size(); index++) {
            int x = registeredDifByX.get(index);
            int y = registeredDifByY.get(index);
            image.setRGB(x, y, Color.CYAN.getRGB());
        }
    }


    public List<ImageBlock> generateImageBlocks(List<Integer> blockHeightBoundaries, List<Integer> blockWidthBoundaries) {
        List<ImageBlock> imageBlocks = new ArrayList<>();

        int blocksByX = blockWidthBoundaries.size() - 1;
        int blocksByY = blockHeightBoundaries.size() - 1;

        int lastX = blockWidthBoundaries.get(blocksByX);
        int lastY = blockHeightBoundaries.get(blocksByY);

        for (int x = 0; x < blocksByX; x++) {
            int x1 = blockWidthBoundaries.get(x);
            int x2 = blockWidthBoundaries.get(x + 1) == lastX ? lastX : blockWidthBoundaries.get(x + 1) - 1;

            for (int y = 0; y < blocksByY; y++) {
                int y1 = blockHeightBoundaries.get(y);
                int y2 = blockHeightBoundaries.get(y + 1) == lastY ? lastY : blockHeightBoundaries.get(y + 1) - 1;

                ImageBlock block = new ImageBlock(x1, x2, y1, y2);
                imageBlocks.add(block);
            }
        }

        return Collections.unmodifiableList(imageBlocks);
    }

    public List<Integer> calculateBlockBoundaries(int size, int blocks) {
        List<Integer> boundaries = new ArrayList<>();

        int blockSizeInPx = Math.floorDiv(size, blocks);

        for (int i = 1; i <= size - blockSizeInPx; i+=blockSizeInPx) {
            boundaries.add(i);
        }
        boundaries.add(size);
        return Collections.unmodifiableList(boundaries);
    }

    public BufferedImage deepCopy(BufferedImage image) {

        ColorModel colorModel = image.getColorModel();

        WritableRaster raster = image.copyData(null);

        return new BufferedImage(colorModel, raster, colorModel.isAlphaPremultiplied(), null);
    }

    public void saveResualtImage(BufferedImage resaultImage, String path) throws IOException {
        File file = createFileIfNotExists(path);
        saveResualtImage(resaultImage, file);
    }

    public void saveResualtImage(BufferedImage resaultImage, File file) throws IOException {
        createFileIfNotExists(file);
        ImageIO.write(resaultImage, "png", file);
    }

    private void createFileIfNotExists(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    private File createFileIfNotExists (String imgPath) throws IOException {
        File file = new File(imgPath);
        createFileIfNotExists(file);
        return file;
    }
}
