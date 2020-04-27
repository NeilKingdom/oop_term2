package ifs;

/**
 * This class acts as an Interface for Ifs fractals
 * @author Neil Kingdom
 * @version 1.1
 * @since 2020-2-25
 */
public interface AffineTransform {

    String getName();
    double[][] getAffine();
    double getScale();
    int getHeight();
    int getWidth();
    int getXOffset();
    int getYOffset();
}