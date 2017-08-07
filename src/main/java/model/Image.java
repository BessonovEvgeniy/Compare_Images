package model;

import exceptions.InvalidImageException;
import service.DifferenceImageBlockService;
import service.ImageService;
import service.Impl.DifferenceImageBlockServiceImpl;
import service.Impl.ImageServiceImpl;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Image implements Runnable{

    private BufferedImage image1;

    private BufferedImage image2;

    private BufferedImage resaultImage;

    private Integer height;

    private Integer width;

    private int blocksPerSide = 2;

    private List<Integer> blockHeightBoundaries;
    private List<Integer> blockWidthtBoundaries;

    private LinkedList<Integer> registeredDifByX = new LinkedList<>();
    private LinkedList<Integer> registeredDifByY = new LinkedList<>();

    private List<ImageBlock> imageBlocks = new ArrayList<>();

    public List<DifferenceImageBlock> getDifferenceImageBlocks() {
        return differenceImageBlocks;
    }

    private List<DifferenceImageBlock> differenceImageBlocks = new ArrayList<>();

    private ImageService imageService = new ImageServiceImpl();

    public Image(BufferedImage image1, BufferedImage image2) throws InvalidImageException {
        this.image1 = image1;
        this.image2 = image2;
        init();
    }

    public Image(File image1, File image2) throws InvalidImageException, IOException {
        this.image1 = ImageIO.read(image1);
        this.image2 = ImageIO.read(image2);
        init();
    }

    private void init() throws InvalidImageException {

        if (imageService.isValidImageSize(image1, image2)) {
            this.width = image1.getWidth();
            this.height = image2.getHeight();

            blockHeightBoundaries = imageService.calculateBlockBoundaries(height, blocksPerSide);
            blockWidthtBoundaries = imageService.calculateBlockBoundaries(width, blocksPerSide);
            imageBlocks = imageService.generateImageBlocks(blockHeightBoundaries, blockWidthtBoundaries);
            setImagesToImagesBlocksCompare();
            resaultImage = imageService.deepCopy(image1);
        }
    }

    private void setImagesToImagesBlocksCompare() {
        if (imageBlocks != null) {
            for (ImageBlock imageBlock : imageBlocks) {
                imageBlock.setImageToCompare(image1, image2);
            }
        }
    }

    public void run(){
        //TODO Replace BY THREAD FACTORY
        for (ImageBlock imageBlock : imageBlocks) {

            //TODO implement threads usage instead thread stub
            imageBlock.run();

            registeredDifByX.addAll(imageBlock.getRegisteredDiffByX());
            registeredDifByY.addAll(imageBlock.getRegisteredDiffByY());
        }

        DifferenceImageBlockService differenceImageBlock = new DifferenceImageBlockServiceImpl(width, height, 10);

        List<DifferenceImageBlock> diffBlocks = differenceImageBlock.findBlocksOfDifferences(registeredDifByX, registeredDifByY);

        differenceImageBlocks.addAll(diffBlocks);

        imageService.registerDifferenceInImage(resaultImage, registeredDifByX, registeredDifByY);
    }

    public BufferedImage getResualtImage() throws IOException {
        return resaultImage;
    }


}
