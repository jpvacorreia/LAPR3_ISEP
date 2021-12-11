package lapr.project.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VertexTest {

    Vertex v;
    Vertex v2;
    Edge e;
    Vertex v3;
    Edge e2;
    Vertex v4;

    @BeforeEach
    void setUp(){
        Pair<Double,Double> pair = new Pair<>(1.0,2.0);
        v = new Vertex(1,pair,3.0);
        Pair<Double,Double> pair2 = new Pair<>(3.0,4.0);
        v2 = new Vertex(2,pair2,5.0);
        e = new Edge("1",1.0,v,v2);
        v3 = new Vertex(3,pair2,5.0);
        e2 = new Edge("1",1.0,v,v3);
        v.addAdjVert(v3,e2);
        v4 = new Vertex(v);
    }

    @Test
    void getCoordinates() {
        Pair<Double,Double> pair = new Pair<>(1.0,2.0);
        assertEquals(pair.toString(),v.getCoordinates().toString());
    }

    @Test
    void getAltitude() {
        assertEquals(3.0,v.getAltitude());
        assertEquals(v4.getCoordinates(),v.getCoordinates());
    }

    @Test
    void setCoordinates() {
        Pair<Double,Double> pair = new Pair<>(2.0,1.0);
        v.setCoordinates(pair);
        assertEquals(pair,v.getCoordinates());
    }

    @Test
    void setAltitude() {
        v.setAltitude(4.0);
        assertEquals(4.0,v.getAltitude());
    }

    @Test
    void getKey() {
        assertEquals(1,v.getKey());
    }

    @Test
    void setKey() {
        v.setKey(2);
        assertEquals(2,v.getKey());
    }

    @Test
    void addAdjVert() {
        v.addAdjVert(v2,e);
        assertEquals(v2,v.getAdjVert(e));
    }

    @Test
    void getAdjVert() {
        v.addAdjVert(v2,e);
        assertEquals(v2,v.getAdjVert(e));
    }

    @Test
    void remAdjVert() {
        v.addAdjVert(v2,e);
        v.remAdjVert(v2);
        assertEquals(null,v.getAdjVert(e));
    }

    @Test
    void getEdge() {
        assertEquals(e2,v.getEdge(v3));
    }

    @Test
    void numAdjVerts() {
        assertEquals(1,v.numAdjVerts());
    }

    @Test
    void getAllAdjVerts() {
        assertEquals(1,v.getAllAdjVerts().spliterator().estimateSize());
    }

    @Test
    void getAllOutEdges() {
        assertEquals(1,v.getAllOutEdges().spliterator().estimateSize());
    }

    @Test
    void testClone() {
        assertEquals(v.getKey(),v.clone().getKey());
        assertEquals(v.getCoordinates(),v.clone().getCoordinates());
        assertEquals(v.getAllAdjVerts().spliterator().estimateSize(),v.clone().getAllAdjVerts().spliterator().estimateSize());
        assertEquals(v.getAllOutEdges().spliterator().estimateSize(),v.clone().getAllOutEdges().spliterator().estimateSize());
        assertEquals(v.getAltitude(),v.clone().getAltitude());
    }

    @Test
    void testToString() {
        assertEquals("Vertex{" +
                "key=" + 1 +
                ", coordinates=" + v.getCoordinates() +
                ", altitude=" + v.getAltitude() +
                '}',v.toString());
    }
}