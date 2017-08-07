package model;

public class DifferenceImageBlock extends AbstractImageBlock {

    protected int x;
    protected int y;

    public DifferenceImageBlock(Integer width, Integer height) {
        this.x = width;
        this.y = height;
    }

    public DifferenceImageBlock(Integer x1, Integer x2, Integer y1, Integer y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
}
