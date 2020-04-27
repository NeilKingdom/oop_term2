package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ifs.IfsCreator;
import stl.Facet;
import stl.Solid;
import stl.Vertex;
import java.text.DecimalFormat;

class SolidTest {
	
	static Vertex V1;
    static Vertex V2;
    static Vertex V3;
    static Vertex V4;
    static Vertex V5;
    static Vertex V6;
    static Vertex V7;
    static Vertex V8;
    static Vertex V9;
    
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    	
    	V1 = new Vertex(0,0,0);
        V2 = new Vertex(1,0,0);
        V3 = new Vertex(0,1,0);
        V4 = new Vertex(1,1,0);
        V5 = new Vertex(0,0,1);
        V6 = new Vertex(1,0,1);
        V7 = new Vertex(0,1,1);
        V8 = new Vertex(1,1,1);
        V9 = new Vertex(0,0,0);
    }
   
	@Test
	void testInvalid() { 
		
		//check to ensure that a solid with less than 3 vertices return false
		assertFalse(new Solid().addFacet());
		assertFalse(new Solid().addFacet(V1));
		assertFalse(new Solid().addFacet(V1, V2));
		//check to ensure that a solid with less than 3 vertices does not create any facets
		Solid solid1 = new Solid();
		Solid solid2 = new Solid();
		Solid solid3 = new Solid();
		solid1.addFacet();
		solid2.addFacet(V1);
		solid3.addFacet(V1, V2);
		assertEquals(solid1.toString(), new Solid().toString());
		assertEquals(solid2.toString(), new Solid().toString());
		assertEquals(solid3.toString(), new Solid().toString());
	}
	
	@Test
	void test3() { 
		
		//check to ensure a solid with three vertices returns true
		assertTrue(new Solid().addFacet(V1, V2, V3));
		//check to ensure a solid with three vertices creates 
		//the same tri as a singular facet would
		Facet facet = new Facet(V1, V2, V3);
		Solid solid1 = new Solid();
		Solid solid2 = new Solid();
		solid1.addFacet(V1, V2, V3);
		solid2.addFacet(facet);
		assertEquals(solid1.toString(), solid2.toString());
	}
	
	@Test
	void test4() { 

		//check to ensure that a solid with four vertices returns true
		assertTrue(new Solid().addFacet(V1, V2, V3, V4));
		//check to ensure that a solid with four vertices creates the same two 
		//tris as calling addFacet twice would
		Facet facet1 = new Facet(V1, V2, V3);
		Facet facet2 = new Facet(V1, V3, V4);
		Solid solid1 = new Solid();
		Solid solid2 = new Solid();
		solid1.addFacet(V1, V2, V3, V4);
		solid2.addFacet(facet1);
		solid2.addFacet(facet2);
		assertEquals(solid1.toString(), solid2.toString());
	}
	
	@Test
	void test9() { 
		
		//check to ensure that a solid with nine vertices returns true
		assertTrue(new Solid().addFacet(V1, V2, V3, V1, V2, V3, V1, V2, V3));
		//check to ensure that a solid with nine vertices creates the
		//same three tris as calling addFacet seven times would
		Facet facet1 = new Facet(V1, V2, V3);
		Facet facet2 = new Facet(V1, V3, V4);
		Facet facet3 = new Facet(V1, V4, V5);
		Facet facet4 = new Facet(V1, V5, V6);
		Facet facet5 = new Facet(V1, V6, V7);
		Facet facet6 = new Facet(V1, V7, V8);
		Facet facet7 = new Facet(V1, V8, V9);
		Solid solid1 = new Solid();
		Solid solid2 = new Solid();
		solid1.addFacet(V1, V2, V3, V4, V5, V6, V7, V8, V9);
		solid2.addFacet(facet1);
		solid2.addFacet(facet2);
		solid2.addFacet(facet3);
		solid2.addFacet(facet4);
		solid2.addFacet(facet5);
		solid2.addFacet(facet6);
		solid2.addFacet(facet7);
		assertEquals(solid1.toString(), solid2.toString());
	}
	
	@Test
	void bonusTest() {
		
		//check to ensure that each ifs' probability matrix adds to 1
		//Need to use DecimalFormat because 
		DecimalFormat df = new DecimalFormat("#.##");
		IfsCreator create = new IfsCreator();
		int transformsLength = create.getTransforms().length;
		double sum = 0;
		//for each object in ifsCreate.getTransforms(), add current ifs' final matrix value to sum
		for(int j = 0; j < transformsLength; j++) {
			for(int i = 0; i < create.getTransforms()[j].getAffine().length; i++) {
				sum += create.getTransforms()[j].getAffine()[i][create.getTransforms()[j].getAffine()[0].length-1];
			}
		}
		//test that the amount of transforms tested will equal all 
		//of their probability matrix' values combined
		assertEquals(df.format(transformsLength), df.format(sum));
	}
	
	public static void main(String[] args) {
		
		SolidTest st = new SolidTest();
		st.testInvalid();
		st.test3();
		st.test4();
		st.test9();
	}
}
