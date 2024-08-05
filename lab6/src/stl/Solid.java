package stl;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class adds facets to an array in Stl format
 * @author Neil Kingdom
 * @version 1.0
 * @since 2020-2-25
 */
public class Solid {
	
	private String name;
    private List<Facet> facetList = new ArrayList<>();
    
    /**
     * @param name Name of the solid
     */
    public Solid(String name) {
        this.name = name;
    }
    
    public Solid() {
    }
    
    /**
     * Outputs facets to array list
     * @param facet
     * @return A boolean
     */
    public boolean addFacet(Facet facet) {
        return facetList.add(facet);
    }
    
    /**
     * Creates a variable amount of facets given 3 or more vertices
     * @param vertices A variable argument of vertex objects
     * @return A boolean
     */
    public boolean addFacet(Vertex... vertices) {

    	int vertLength = vertices.length;
 
    	if(vertLength < 3) {
    		return false;
    	}
    	
        else {
        	for(int i = 2; i < vertLength; i++) 
        		addFacet(new Facet(vertices[0], vertices[i-1], vertices[i]));
        	return true;
        }	
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Facet f : facetList) 
            s.append(f);
        return ("solid " + name + s + "\nendsolid " + name);
    }
    
    /**
     * Method to output a solid into a .stl file
     * @param printwriter
     */
    public void print(PrintWriter printwriter) {
        printwriter.print(this);
        printwriter.close();
    }
}

