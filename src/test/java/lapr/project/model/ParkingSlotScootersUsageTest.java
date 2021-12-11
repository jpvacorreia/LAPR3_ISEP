package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ParkingSlotScootersUsageTest {
    ParkingSlotScootersUsage parkingSlotScootersUsage;
    @BeforeEach
    void setUp() {
        Date date1 = new Date();
        date1.setTime(1);
        Date date2 = new Date();
        date2.setTime(2);
       parkingSlotScootersUsage = new ParkingSlotScootersUsage(1,1,date1,date2);
    }

    @Test
    void setIdScooter() {
        parkingSlotScootersUsage.setIdScooter(3);
        assertEquals(3,parkingSlotScootersUsage.getIdScooter());
    }

    @Test
    void setDateIN() {
        Date date = new Date();
        date.setTime(5);
        parkingSlotScootersUsage.setDateIN(date);
        assertEquals(5,parkingSlotScootersUsage.getDateIN().getTime());
    }

    @Test
    void setDateOUT() {
        Date date = new Date();
        date.setTime(6);
        parkingSlotScootersUsage.setDateOUT(date);
        assertEquals(6,parkingSlotScootersUsage.getDateOUT().getTime());
    }

    @Test
    void setIdParkingSlot() {
        parkingSlotScootersUsage.setIdParkingSlot(11);
        assertEquals(11,parkingSlotScootersUsage.getIdParkingSlot());
    }

    @Test
    void getIdParkingSlot() {
        assertEquals(1,parkingSlotScootersUsage.getIdParkingSlot());
    }

    @Test
    void getDateIN() {
        assertEquals(1,parkingSlotScootersUsage.getDateIN().getTime());
    }

    @Test
    void getDateOUT() {
        assertEquals(2,parkingSlotScootersUsage.getDateOUT().getTime());
    }

    @Test
    void getIdScooter() {
        assertEquals(1,parkingSlotScootersUsage.getIdScooter());
    }
}