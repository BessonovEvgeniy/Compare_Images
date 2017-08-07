package service;

import exceptions.InvalidImageException;
import model.ImageBlock;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ImageService {

    void registerDifferenceInImage(BufferedImage image,
                                   List<Integer> registeredDifByX,
                                   List<Integer> registeredDifByY);

    boolean isValidImageSize(BufferedImage image1, BufferedImage image2) throws InvalidImageException;

    List<Integer> calculateBlockBoundaries(int size, int blocks);

    List<ImageBlock> generateImageBlocks(List<Integer> blockHeightBoundaries, List<Integer> blockWidthBoundaries);

    BufferedImage deepCopy(BufferedImage image);

    void saveResualtImage(BufferedImage resaultImage, String path) throws IOException;

    void saveResualtImage(BufferedImage resaultImage, File file) throws IOException;
}
