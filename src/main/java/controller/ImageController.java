package controller;


import model.Image;
import org.springframework.stereotype.Controller;
import view.Plot;

import javax.swing.*;
import java.io.File;

@Controller
public class ImageController {

    public static void main(String[] args) {
        String img1;
        String img2;

        img1 = "/home/ionex/image1.png";
        img2 = "/home/ionex/image2.png";

//        img1 = "/home/ionex/smallImage.png";
//        img2 = "/home/ionex/smallWImage.png";

        File sourceImageFile1 = new File(img1);
        File sourceImageFile2 = new File(img2);
        try {
            if (sourceImageFile1.exists() && sourceImageFile2.exists()) {
//              Executor executor = Executors.newCachedThreadPool();
                Image img = new Image(sourceImageFile1, sourceImageFile2);

                //TODO change to threads
                img.run();

                JFrame window = new JFrame("Plot");
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                Plot plot = new Plot(img.getResualtImage());
                window.add(plot);
                window.setVisible(true);
                window.setSize(985, 701);
                plot.drawRect(img.getDifferenceImageBlocks());

                plot.repaint();

//                img.saveResualtImage("");
            } else {
                System.out.println("Images are different. Please crop images to the same Dimensions");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
