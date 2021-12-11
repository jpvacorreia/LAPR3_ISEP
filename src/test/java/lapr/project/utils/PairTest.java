package lapr.project.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PairTest {

    Pair<Integer,Integer> p;

    @BeforeEach
    void setUp(){
        p = new Pair<Integer,Integer>(1,1);
    }

    @Test
    void createPair() {
        Pair<Integer,Integer> pair = Pair.createPair(1,2);
        assertEquals(1,pair.getElement0());
        assertEquals(2,pair.getElement1());
    }

    @Test
    void getElement0() {
        assertEquals(1,p.getElement0());
    }

    @Test
    void getElement1() {
        assertEquals(1,p.getElement1());
    }

    @Test
    void testToString() {
        assertEquals("Pair{" +
                "element0=" + 1 +
                ", element1=" + 1 +
                '}',p.toString());
    }
}