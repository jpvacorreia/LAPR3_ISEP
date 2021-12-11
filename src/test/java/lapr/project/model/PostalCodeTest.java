package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostalCodeTest {
    PostalCode pc;

    @BeforeEach
    void setUp() {
        pc = new PostalCode("1234-567");
    }

    @Test
    void getCity() {
        assertEquals(1234, pc.getCity());
    }

    @Test
    void getLocal() {
        assertEquals(567, pc.getLocal());
    }

    @Test
    void testToString() {
        String expected = "PostalCode{city=1234, local=567}";
        assertEquals(expected, pc.toString());
    }
}