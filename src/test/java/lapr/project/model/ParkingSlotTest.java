package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSlotTest {
    ParkingSlot ps;
    @BeforeEach
    void setUp(){
        ps = new ParkingSlot(2,3);
    }

    @Test
    void getId() {
        assertEquals(2,ps.getId());
    }

    @Test
    void getPharmacyId() {
        assertEquals(3,ps.getPharmacyId());
    }
}