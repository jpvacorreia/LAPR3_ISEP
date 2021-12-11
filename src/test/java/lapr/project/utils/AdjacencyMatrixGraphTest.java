
package lapr.project.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit tests class
 * for AdjacencyMatrixGraph
 *
 * @author DEI_ESINF
 * 
 */
public class AdjacencyMatrixGraphTest {

	AdjacencyMatrixGraph adg;
	Vertex v1;
	Vertex v2;
	Vertex v3;
	Vertex v4;
	Vertex v5;
	Edge e1;
	Edge e2;
	Edge e3;
	
    @BeforeEach
	void setUp(){
    	adg = new AdjacencyMatrixGraph(10);
    	v1 = new Vertex();
    	v1.setCoordinates(Pair.createPair(41.21, -8.21));
    	v2 = new Vertex();
		v2.setCoordinates(Pair.createPair(41.31, -8.31));
    	v3 = new Vertex();
		v3.setCoordinates(Pair.createPair(41.41, -8.41));
		v4 = new Vertex();
		v4.setCoordinates(Pair.createPair(41.51, -8.51));
		v5 = new Vertex();
		v5.setCoordinates(Pair.createPair(41.61, -8.61));
		e1 = new Edge("e1",1,v1,v2);
		e2 = new Edge("e2",2,v2,v4);
		e3 = new Edge("e3",3,v3,v5);
		adg.insertVertex(v1);
		adg.insertVertex(v2);
		adg.insertVertex(v3);
		adg.insertVertex(v4);
		adg.insertVertex(v5);
		adg.insertEdge(v1,v2,e1);
		adg.insertEdge(v2,v4,e2);
		adg.insertEdge(v3,v5,e3);
	}

    @Test
    public void testNumVertices() {
		assertTrue((adg.numVertices()==5));
    }

    @Test
    public void testNumEdges() {
		assertTrue((adg.numEdges()==3));
    }

    @Test
    public void testVertices() {
		Iterator<Vertex> itVert = adg.vertices().iterator();
		assertTrue(itVert.hasNext());
		itVert = adg.vertices().iterator();
		assertEquals(v1, itVert.next());
		assertEquals(v2, itVert.next());
		adg.removeVertex(v1);
		itVert = adg.vertices().iterator();
		assertEquals(v2, itVert.next());
		adg.removeVertex(v2);
    }


	@Test
	void getXY() {
    	assertEquals(e1,adg.getXY(adg.toIndex(v1),adg.toIndex(v2)));
	}

	@Test
	void toIndex() {
    	assertEquals(0,adg.toIndex(v1));
		assertEquals(1,adg.toIndex(v2));
		Vertex vA = new Vertex();
		vA.setCoordinates(Pair.createPair(41.88, -8.88));
		assertEquals(-1,adg.toIndex(vA));
	}

	@Test
	void getNumVertices() {
    	assertEquals(5,adg.getNumVertices());
	}

	@Test
	void checkVertex() {
    	assertTrue(adg.checkVertex(v1));
		Vertex vA = new Vertex();
		vA.setCoordinates(Pair.createPair(41.88, -8.88));
		assertFalse(adg.checkVertex(vA));
	}

	@Test
	void edges() {
		Iterator<Edge> itVert = adg.edges().iterator();
		assertTrue(itVert.hasNext());
		itVert = adg.edges().iterator();
		assertEquals(e1, itVert.next());
		assertEquals(e2, itVert.next());
		assertEquals(e3, itVert.next());
		assertEquals(3,adg.edges().spliterator().estimateSize());
		adg.removeEdge(v1,v2);
		itVert = adg.edges().iterator();
		assertEquals(e2, itVert.next());
		adg.removeEdge(v2,v4);
		assertEquals(e3, itVert.next());
		assertEquals(1,adg.edges().spliterator().estimateSize());
	}

	@Test
	void getEdge() {
		Vertex vA = new Vertex();
		vA.setCoordinates(Pair.createPair(41.88, -8.88));
    	assertEquals(e1,adg.getEdge(v1,v2));
    	assertEquals(e2,adg.getEdge(v2,v4));
		assertNull(adg.getEdge(v3, v4));
		assertNull(adg.getEdge(vA,v1));
		assertNull(adg.getEdge(v2,vA));
	}

	@Test
	void insertVertex() {
		Vertex vA = new Vertex();
		vA.setCoordinates(Pair.createPair(41.88, -8.88));
    	assertTrue(adg.insertVertex(vA));
    	assertEquals(6,adg.vertices().spliterator().estimateSize());
    	assertFalse(adg.insertVertex(vA));
	}

	@Test
	void insertEdge() {
		Vertex vA = new Vertex();
		vA.setCoordinates(Pair.createPair(41.88, -8.88));
    	Edge e4 = new Edge("e4",4,v2,v3);
    	assertTrue(adg.insertEdge(v2,v3,e4));
    	assertEquals(4,adg.edges().spliterator().estimateSize());
		assertFalse(adg.insertEdge(vA,v3,e4));
		assertFalse(adg.insertEdge(vA,vA,new Edge("e",1,vA,vA)));
		assertEquals(4,adg.edges().spliterator().estimateSize());
		assertFalse(adg.insertEdge(v3,vA,e4));
		assertFalse(adg.insertEdge(v3,v3,e4));
	}
	
	@Test
	void removeVertex() {
		assertEquals(5,adg.numVertices());
		assertEquals(3,adg.numEdges());

    	assertTrue(adg.removeVertex(v4));

		assertEquals(4,adg.numVertices());
		assertEquals(2,adg.numEdges());

		Iterator<Vertex> itVert = adg.vertices().iterator();

		assertEquals(v1,itVert.next());
		assertEquals(v2,itVert.next());
		assertEquals(v3,itVert.next());
		assertEquals(v5,itVert.next());

		Vertex vA = new Vertex();
		vA.setCoordinates(Pair.createPair(41.88, -8.88));
    	assertEquals(4,adg.getNumVertices());
    	assertFalse(adg.removeVertex(v4));
    	assertFalse(adg.removeVertex(vA));

	}

	@Test
	void removeEdge() {
		Vertex vA = new Vertex();
		vA.setCoordinates(Pair.createPair(41.88, -8.88));
    	assertEquals(e1,adg.removeEdge(v1,v2));
    	assertEquals(2,adg.numEdges());
		assertNull(adg.removeEdge(v1,v2));
		assertNull(adg.removeEdge(vA,v2));
		assertNull(adg.removeEdge(v2,vA));

	}

}