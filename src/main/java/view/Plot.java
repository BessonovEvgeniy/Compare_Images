package view;

import model.DifferenceImageBlock;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Plot extends JPanel{

    private BufferedImage image;
    private Graphics2D graphics2D;

    public Plot(BufferedImage img) {
        image = img;
        graphics2D = (Graphics2D) image.getGraphics();
    }

    public void drawRect(List<DifferenceImageBlock> blocks) {

        Color oldColor = graphics2D.getColor();

        for (DifferenceImageBlock block : blocks) {

            int x1 = block.getX1();
            int x2 = block.getX2();
            int y1 = block.getY1();
            int y2 = block.getY2();

            int width = x2 - x1 + 1;
            int height = y2 - y1 + 1;
            graphics2D.setColor(Color.RED);
            graphics2D.drawRect(x1, y1, width, height);
        }
        graphics2D.setColor(oldColor);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if(image != null){
            graphics.drawImage(image, 0, 0, this);
        }
    }
}
