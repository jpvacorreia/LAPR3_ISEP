package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourierTest {

    Courier cour;
    Courier cour2;

    @BeforeEach
    void TestInstance() {
        cour = new Courier("Courier1", 1234567898, 123456789, 78.7, 78, 1, "courier1@email.com");
        cour2 = new Courier(1,"Courier1", 1234567898, 123456789, 78.7, 78, 1, "courier1@email.com");

    }


    @Test
    void getName() {
        String expected = "Courier1";
        String actual = cour.getName();
        assertEquals(expected, actual);
    }
    
    @Test
    void getNiss() {
        double expected = 1234567898;
        double actual = cour.getNiss();
        assertEquals(expected, actual);
    }
    
    @Test
    void getNif() {
        int expected = 123456789;
        int actual = cour.getNif();
        assertEquals(expected, actual);
    }

    @Test
    void getWeight() {
        double expected = 78.7;
        double actual = cour.getWeight();
        assertEquals(expected, actual);
    }
    
    @Test
    void getScooterId() {
        int expected = 78;
        int actual = cour.getScooterId();
        assertEquals(expected, actual);
    }
    
    @Test
    void getPharmacyId() {
        int expected = 1;
        int actual = cour.getPharmacyId();
        assertEquals(expected, actual);
    }
    
    @Test
    void getEmail() {
        String expected = "courier1@email.com";
        String actual = cour.getEmail();
        assertEquals(expected, actual);
    }

    @Test
    void getId() {
        assertEquals(1, cour2.getId());
    }

}