package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    Address add1;

    @BeforeEach
    void setUp() {
        add1 = new Address("rua estranha", "1234-567", 1561.28651, 15651.513, 75);
    }

    @Test
    void getStreet() {
        assertEquals("rua estranha", add1.getStreet());
        assertNotEquals("", add1.getStreet());
    }

    @Test
    void getCoordinateY() {
        assertEquals(15651.513, add1.getCoordinateY());
        assertNotEquals(0, add1.getCoordinateY());
    }

    @Test
    void testToString() {
        assertEquals("Address{street='rua estranha', coordinateX=1561.28651, coordinateY=15651.513, postalCode=PostalCode{city=1234, local=567}}", add1.toString());
        assertNotEquals("", add1.toString());
    }
}