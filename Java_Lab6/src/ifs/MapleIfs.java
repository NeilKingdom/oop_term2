package ifs;

/**
 * This class contains the values for creating the Maple fractal
 * @author Neil Kingdom
 * @version 1.0
 * @since 2020-2-25
 */
public class MapleIfs implements AffineTransform {

    private double scale = 100;
    private int height = 702;
    private int width = 885;
    private int xOffset = 340;
    private int yOffset = 445;
    private double[][] affine = new double[][]{{0.51, 0, 0.01, 0.14, 1.31, 0.08, 0.10},
            								  {0.5, -0.45, 0.52, 0.43, 0.75, -1.49, 0.35},
            								  {0.47, 0.47, -0.49, 0.45, 0.74, 1.62, 0.35},
            								  {0.51, 0, 0, 0.49, -1.62, -0.02, 0.20}};

    @Override
    public String getName() { return "Maple";}

    @Override
    public double[][] getAffine() { return affine;}

    @Override
    public double getScale() {
        return scale;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getXOffset() {
        return xOffset;
    }

    @Override
    public int getYOffset() {
        return yOffset;
    }
}