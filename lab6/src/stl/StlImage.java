package stl;

/**
 * This class creates a lithophane from a 2D array of integers
 * @author Neil Kingdom
 * @version 1.0
 * @since 2020-2-25
 */
public class StlImage extends Solid{

    private double minThick; //base thickness
    private double thickScale; //scale for z axis between minThick and maxValue
    private double scale;
    private double maxValue; //maximum value of the ifs matrix

    /**
     * StlImage constructor with initial min and max thicknesses of the lithophane
     * @param min The minimum lithophane base thickness
     * @param max The tallest height that the lithophane is capable of being
     */
    public StlImage(double min, double max) {
        this.minThick = min;
        this.thickScale = max;
        this.scale = 0.1; //scale hard-coded to 0.1
    }

    /**
     * Creates the lithophane
     * @param matrix The 2D matrix representing the Ifs
     * @return Returns a new solid representing the lithophane
     */
    public Solid createSolid(int[][] matrix) {

    	int width = matrix[0].length;
    	int length = matrix.length;
        Solid solid = new Solid();

        //finding maxValue of the 2D matrix for z axis referencing
        double max = 0;
        for (int y = 0; y < length; y++)
            for (int x = 0; x < width; x++)
                if (matrix[y][x] > max) max = matrix[y][x];
        maxValue = max;
        
        //creating the base for the Ifs Stl to rest on
        //side 1
        Vertex[] argsSide1 = {
                createIfsVertex(0, 0, 0),
                createIfsVertex(width, 0, 0),
                createIfsVertex(width, 0, minThick),
                createIfsVertex(0, 0, minThick)
        };
        solid.addFacet(argsSide1);
        //side 2
        Vertex[] argsSide2 = {
                createIfsVertex(0, length, 0),
                createIfsVertex(0, 0, 0),
                createIfsVertex(0, 0, minThick),
                createIfsVertex(0, length, minThick)
        };
        solid.addFacet(argsSide2);
        //side 3
        Vertex[] argsSide3 = {
                createIfsVertex(width, 0, 0),
                createIfsVertex(width, length, 0),
                createIfsVertex(width, length, minThick),
                createIfsVertex(width, 0, minThick)
        };
        solid.addFacet(argsSide3);
        //side 4
        Vertex[] argsSide4 = {
                createIfsVertex(width, length, 0),
                createIfsVertex(0, length, 0),				
                createIfsVertex(0, length, minThick),
                createIfsVertex(width, length, minThick)
        };
        solid.addFacet(argsSide4);
        //bottom
        Vertex[] argsBottom = {
                createIfsVertex(0, 0, 0),
                createIfsVertex(0, length, 0),
                createIfsVertex(width, length, 0),
                createIfsVertex(width, 0, 0)
        };
        solid.addFacet(argsBottom);
        //top
        for(int y = 0; y < length-1; y++) {
            for (int x = 0; x < width-1; x++) {

                Vertex[] argsTop = {
                        createIfsVertex(x, y, matrix[y][x]+minThick),
                        createIfsVertex(x+1, y, matrix[y][x+1]+minThick),
                        createIfsVertex(x+1, y+1, matrix[y+1][x+1]+minThick),
                        createIfsVertex(x, y+1, matrix[y+1][x]+minThick)
                }; 
                solid.addFacet(argsTop);
            }
        }
        return solid;
    }

    /**
     * Updates vertexes with the appropriate scale and thickness
     * @param x
     * @param y
     * @param z
     * @return Returns the transformed vertex
     */
    private Vertex createIfsVertex(double x, double y, double z) {

    	double thickness = minThick + thickScale * Math.sqrt(z/maxValue);
        return new Vertex(x * scale, y * scale, thickness);
    }
}
