package stl;

/**
 * This class transforms vertices into facets
 * @author Neil Kingdom
 * @version 1.0
 * @since 2020-2-25
 */
public class Facet extends Solid {

    private Vertex normal;
    private Vertex v1;
    private Vertex v2;
    private Vertex v3;

    /**
     * @param v1
     * @param v2
     * @param v3
     */
    public Facet(Vertex v1, Vertex v2, Vertex v3) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        normal = Vertex.calcNormal(v1, v2, v3);
    }

    @Override
    public String toString() {
        return "\nfacet normal " + normal.toString() + "\n outer loop\n  vertex " + v1.toString() + "\n  vertex "
                + v2.toString() + "\n  vertex " + v3.toString() + "\n endloop\nendfacet";
    }
}
