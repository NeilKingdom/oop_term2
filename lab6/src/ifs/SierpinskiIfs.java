package ifs;

/**
 * This class contains the values for creating the Sierpinski Gasket fractal
 * @author Neil Kingdom
 * @version 1.0
 * @since 2020-2-25
 */
public class SierpinskiIfs implements AffineTransform {

    private double scale = -500;
    private int height = 449;
    private int width = 519;
    private int xOffset = 439;
    private int yOffset = 509;
    private double[][] affine = new double[][]{{0.5, 0, 0, 0.5, 0, 0, 0.33},
                                                {0.5, 0, 0, 0.5, 0, 0.5, 0.33},
                                                {0.5, 0, 0, 0.5, 0.43, 0.25, 0.34}};

    @Override
    public String getName() { return "Sierpinski";}

    @Override
    public double[][] getAffine() { return affine;}

    @Override
    public double getScale() { return scale;}

    @Override
    public int getHeight() { return height;}

    @Override
    public int getWidth() { return width;}

    @Override
    public int getXOffset() { return xOffset;}

    @Override
    public int getYOffset() { return yOffset;}
}