package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSlotsScootersTest {

    ParkingSlotsScooters pss;
    @BeforeEach
    void setUp(){
        pss = new ParkingSlotsScooters(1,1,true);
    }

    @Test
    void getIdParkingSlot() {
        assertEquals(1,pss.getIdParkingSlot());
    }

    @Test
    void getHasChargingCapabilities() {
        assertTrue(pss.getHasChargingCapabilities());
    }

    @Test
    void getStateOfUse() {
        assertEquals(1,pss.getStateOfUse());
    }

    @Test
    void setIdParkingSlot() {
        pss.setIdParkingSlot(2);
        assertEquals(2,pss.getIdParkingSlot());
        assertNotEquals(1,pss.getIdParkingSlot());
    }

    @Test
    void setHasChargingCapabilities() {
        pss.setHasChargingCapabilities(false);
        assertFalse(pss.getHasChargingCapabilities());
        assertNotEquals(true,pss.getHasChargingCapabilities());
    }

    @Test
    void setStateOfUse() {
        pss.setStateOfUse(2);
        assertEquals(2,pss.getStateOfUse());
        assertNotEquals(1,pss.getStateOfUse());
    }
}