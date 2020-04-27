package ifs;

/**
 * This class iterates through a 2D array and stores the matrix values from an Ifs
 * image to be printed on a swing component
 * @author Neil Kingdom
 * @version 1.0
 * @since 2020-2-25
 */
public class IfsCreator implements AffineTransform {
	
	@Override
	public String getName() {
		return null;
	}
	@Override
	public double[][] getAffine() {
		return null;
	}
	@Override
	public double getScale() {
		return 0;
	}
	@Override
	public int getHeight() {
		return 0;
	}
	@Override
	public int getWidth() {
		return 0;
	}
	@Override
	public int getXOffset() {
		return 0;
	}
	@Override
	public int getYOffset() {
		return 0;
	}

    /**
     * This method randomly chooses a row from the matrix and applies its values to the 2D array
     * @param affineIfs A matrix containing Ifs coordinate values
     * @return The new 2D array
     */
    public int[][] generateIfs(AffineTransform affineIfs) {

        int[][] newArray = new int[affineIfs.getWidth()][affineIfs.getHeight()];
        double x = 0, y = 0;

        for (int i = 0; i < affineIfs.getHeight(); i++) {
            for (int j = 0; j < affineIfs.getWidth(); j++) {

                double PROB = 0; //current probability in the matrix
                double random = Math.random(); //determines the row picked on each iteration
                int l = affineIfs.getAffine()[0].length-1; //the last double (the probability) in the matrix
                int k = 0;

                for(; PROB < random; k++) {
                    PROB += affineIfs.getAffine()[k][l]; 
                }
                double[] row = affineIfs.getAffine()[k-1];

                double A = row[0];
                double B = row[1];
                double C = row[2];
                double D = row[3];
                double E = row[4];
                double F = row[5];

                double u = (A*x) + (B*y) + E;
                double v = (C*x) + (D*y) + F;
                x = u;
                y = v;

                int xDisp = (int) (x*affineIfs.getScale() + affineIfs.getXOffset());
                int yDisp = (int) (y*affineIfs.getScale() + affineIfs.getYOffset());

                newArray[yDisp][xDisp]++;
            }
        }
        return newArray;
    }

    /**
     * This method returns an array of Ifs objects
     * @return An array of Ifs objects
     */
    public AffineTransform[] getTransforms() {
        return new AffineTransform[]{new BarnsleyIfs(), new MapleIfs(), new SierpinskiIfs()};
    }
    
    public AffineTransform getOther(String fileName) {
        return new FileIfs(fileName);
    }
}