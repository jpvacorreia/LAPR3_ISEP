package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DroneTest {
    Drone drone;

    @BeforeEach
    void setUp() {
        drone = new Drone(202121, 3500, 48.9, 75, 0.65, 2, 1);
        drone.setLocked(false);
    }

    @Test
    void isLocked() {
        assertFalse(drone.isLocked());
    }

    @Test
    void setLocked() {
        drone.setLocked(true);
        assertTrue(drone.isLocked());
    }

    @Test
    void getID() {
        assertEquals(202121, drone.getID());
    }

    @Test
    void getMaxBatteryCapacity() {
        assertEquals(3500, drone.getMaxBatteryCapacity());
    }

    @Test
    void getStateOfCharge() {
        assertEquals(48.9, drone.getStateOfCharge());
    }

    @Test
    void getPowerOutput() {
        assertEquals(75, drone.getPowerOutput());
    }

    @Test
    void getWeight() {
        assertEquals(0.65, drone.getWeight());
    }

    @Test
    void getMaxPayload() {
        assertEquals(2, drone.getMaxPayload());
    }

    @Test
    void getPharmacyID() {
        assertEquals(1, drone.getPharmacyID());
    }

}