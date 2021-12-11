package lapr.project.model;

import lapr.project.controller.RegisterScooterController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class ScooterTest {

    private Scooter scoot;

    @BeforeAll
    public void setUp() {
        scoot = new Scooter(20211, 10000, 65.4, 250, 13, 5, 1);
    }


    @Test
    void getID() {
        int expected = 20211;
        int actual = scoot.getId();
        assertEquals(expected, actual);
    }

    @Test
    void getMaxBatteryCapacity() {
        double expected = 10000;
        double actual = scoot.getMaxBatteryCapacity();
        assertEquals(expected, actual);
    }

    @Test
    void getStateOfCharge() {
        double expected = 65.4;
        double actual = scoot.getStateOfCharge();
        assertEquals(expected, actual);
    }

    @Test
    void getPowerOutput() {
        double expected = 250;
        double actual = scoot.getPowerOutput();
        assertEquals(expected, actual);
    }

    @Test
    void getWeight() {
        double expected = 13;
        double actual = scoot.getWeight();
        assertEquals(expected, actual);
    }

    @Test
    void getMaxPayload() {
        double expected = 5;
        double actual = scoot.getMaxPayload();
        assertEquals(expected, actual);
    }

    @Test
    void getPharmacyID() {
        int expected = 1;
        int actual = scoot.getPharmacyId();
        assertEquals(expected, actual);
    }

    @Test
    void getLocked() {
        scoot.setLocked(false);
        assertEquals(false,scoot.getLocked());
    }

    @Test
    void isLocked() {
        assertEquals(false,scoot.isLocked());
    }

    @Test
    void setLocked() {
        scoot.setLocked(true);
        assertEquals(true,scoot.getLocked());
    }

    @Test
    void setStateOfCharge() {
        scoot.setStateOfCharge(78);
        assertEquals(78,scoot.getStateOfCharge());
    }

    @Test
    void testToString() {
        assertEquals("Scooter{" +
                "ID=" + scoot.getId() +
                ", maxBatteryCapacity=" + scoot.getMaxBatteryCapacity() +
                ", stateOfCharge=" + scoot.getStateOfCharge() +
                ", powerOutput=" + scoot.getPowerOutput() +
                ", weight=" + scoot.getWeight() +
                ", maxPayload=" + scoot.getMaxPayload() +
                ", pharmacyID=" + scoot.getPharmacyId() +
                ", locked=" + scoot.getLocked() +
                '}', scoot.toString());
    }
}