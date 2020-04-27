package ifs;

/**
 * This class contains the matrix values for creating the Barnsley Leaf fractal
 * @author Neil Kingdom
 * @version 1.0
 * @since 2020-2-25
 */
public class BarnsleyIfs implements AffineTransform{

    private double scale = 100;
    private int height = 503;
    private int width = 1019;
    private int xOffset = 228;
    private int yOffset = 10;
    private double[][] affine = new double[][]{{0.00, 0.00, 0.00, 0.16, 0.00, 0.00, 0.01},
                                               {0.85, 0.04, -0.04, 0.85, 0.00, 1.60, 0.85},
                                               {0.20, -0.26, 0.23, 0.22, 0.00, 1.60, 0.07},
                                               {-0.15, 0.28, 0.26, 0.24, 0.00, 0.44, 0.07}};

    @Override
    public String getName() { return "Barnsley";}

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
