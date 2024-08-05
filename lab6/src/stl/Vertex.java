package stl;
import java.text.DecimalFormat;

/**
 * This class uses a formula to create a unit normal for a plane in 3D space
 * @author Neil Kingdom
 * @version 1.0
 * @since 2020-2-25
 */
public class Vertex {

    private double x;
    private double y;
    private double z;
    private static DecimalFormat df;

    /**
     * @param x The x coordinate for the vertex object
     * @param y The y coordinate for the vertex object
     * @param z The z coordinate for the vertex object
     */
    public Vertex(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        df = new DecimalFormat("#.##");
    }

    @Override
    public String toString() {
        return (df.format(x) + " " + df.format(y) + " " + df.format(z));
    }

    /**
     * Calculates the unit normal by using the dot product of 2 vertices on the plane
     * @param v1 The first vertex for the facet object
     * @param v2 The second vertex for the facet object
     * @param v3 The third vertex for the facet object
     * @return Returns a new vertex with an x,y,z coordinate
     */
    public static Vertex calcNormal(Vertex v1, Vertex v2, Vertex v3) {

        //vector P coordinates
        double a1 = v2.x - v1.x;
        double b1 = v2.y - v1.y;
        double c1 = v2.z - v1.z;
        //vector Q coordinates
        double a2 = v3.x - v1.x;
        double b2 = v3.y - v1.y;
        double c2 = v3.z - v1.z;
        //vector normal transforms
        double i = b1*c2 - b2*c1;
        double j = a2*c1 - a1*c2;
        double k = a1*b2 - a2*b1;
        //convert normal to unit normal
        double magnitude = Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2) + Math.pow(k, 2));
        return new Vertex(i/magnitude, j/magnitude, k/magnitude);
    }
}
