package lapr.project.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {

    Edge e;
    Vertex v;
    Vertex v2;
    Edge e2;
    Edge e3;

    @BeforeEach
    void setUp(){
        Pair<Double,Double> pair = new Pair<>(1.0,2.0);
        v = new Vertex(1,pair,3.0);
        Pair<Double,Double> pair2 = new Pair<>(3.0,4.0);
        v2 = new Vertex(2,pair2,5.0);
        e = new Edge("1",1.0,v,v2);
        e2 = new Edge("2",2.0,v,v2,true, 0);
        e3 = new Edge("3",3.0,v,v2,false, 0);
    }

    @Test
    void getStreetName() {
        assertEquals("1",e.getStreetName());
    }

    @Test
    void setStreetName() {
        e.setStreetName("2");
        assertEquals("2",e.getStreetName());
    }

    @Test
    void getWeight() {
        assertEquals(1.0,e.getWeight());
    }

    @Test
    void setWeight() {
        e.setWeight(2.0);
        assertEquals(2.0,e.getWeight());
    }

    @Test
    void getVOrig() {
        assertEquals(v.toString(),e.getVOrig().toString());
    }

    @Test
    void setVOrig() {
        e.setVOrig(v2);
        assertEquals(v2.toString(),e.getVOrig().toString());
    }

    @Test
    void getVDest() {
        assertEquals(v2.toString(),e.getVDest().toString());
    }

    @Test
    void setVDest() {
        e.setVDest(v);
        assertEquals(v.toString(),e.getVDest().toString());
    }

    @Test
    void testClone() {
        assertEquals(e.getStreetName(),e.clone().getStreetName());
    }

    @Test
    void testToString() {
        assertEquals("Edge{" +
                "streetName='" + "1" + '\'' +
                ", weight=" + e.getWeight() +
                ", vOrig=" + e.getVOrig() +
                ", vDest=" + e.getVDest() +
                '}',e.toString());
    }

    @Test
    void setEnergyCost() {
        e2.setEnergyCost(1);
        assertEquals(3.741941,e2.getEnergyCost(),0.1);
        e3.setEnergyCost(1);
        assertEquals(36.475376,e3.getEnergyCost(),0.1);
    }

    @Test
    void getEnergyCost() {
        assertEquals(0.0,e2.getEnergyCost());
        e2.setEnergyCost(1);
        assertEquals(3.741941,e2.getEnergyCost(),0.1);
    }

    @Test
    void getWindDirection(){
        assertEquals(0, e2.getWindDirection());
        e2.setWindDirection(180);
        assertEquals(180, e2.getWindDirection());
    }

    @Test
    void setWindDirection(){
        e2.setWindDirection(2);
        assertEquals(2, e2.getWindDirection());
        e3.setWindDirection(4);
        assertEquals(4, e3.getWindDirection());
    }
}