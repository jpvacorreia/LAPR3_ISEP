package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSlotDronesTest {

    ParkingSlotDrones psd;

    @BeforeEach
    void setUp(){
        psd = new ParkingSlotDrones(1,2,true);
    }

    @Test
    void getIdParkingSlot() {
        assertEquals(1,psd.getIdParkingSlot());
    }

    @Test
    void getHasChargingCapabilities() {
        assertTrue(psd.getHasChargingCapabilities());
    }

    @Test
    void getStateOfUse() {
        assertEquals(2,psd.getStateOfUse());
    }

    @Test
    void setIdParkingSlot() {
        psd.setIdParkingSlot(2);
        assertEquals(2,psd.getIdParkingSlot());
        assertNotEquals(1,psd.getIdParkingSlot());
    }

    @Test
    void setHasChargingCapabilities() {
        psd.setHasChargingCapabilities(false);
        assertFalse(psd.getHasChargingCapabilities());
        assertNotEquals(true,psd.getHasChargingCapabilities());
    }

    @Test
    void setStateOfUse() {
        psd.setStateOfUse(1);
        assertEquals(1,psd.getStateOfUse());
        assertNotEquals(2,psd.getStateOfUse());
    }
}