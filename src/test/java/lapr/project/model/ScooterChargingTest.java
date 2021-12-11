package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.*;
import lapr.project.data.api.FileConnectionWithC;
import lapr.project.data.api.SendEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ScooterChargingTest {

    ScooterCharging sc;
    ParkingSlotScootersUsageDB pssuDB;
    ParkingSlotDB psDB;
    ScooterDB sDB;
    Platform plat;
    PharmacyDB phDB;
    SendEmail sEmail;
    CourierScooterDB csDB;
    ParkingSlotScootersUsage parkingSlotScootersUsage;
    Date date1;
    DeliveryRunDB runDB;
    RegisterScooter rScoot;
    CourierDB cDB;


    @BeforeEach
    void setUp() {

        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setScooterCharging(mock(ScooterCharging.class));
        sc = plat.getScooterCharging();
        plat.setParkingSlotScootersUsageDB(mock(ParkingSlotScootersUsageDB.class));
        pssuDB = plat.getParkingSlotScootersUsageDB();
        plat.setParkingSlotDB(mock(ParkingSlotDB.class));
        psDB = plat.getParkingSlotDB();
        plat.setScooterDB(mock(ScooterDB.class));
        sDB = plat.getScooterDB();
        plat.setPharmacyDB(mock(PharmacyDB.class));
        phDB = plat.getPharmacyDB();
        plat.setSendEmail(mock(SendEmail.class));
        sEmail = plat.getSendEmail();
        plat.setCourierScooterDB(mock(CourierScooterDB.class));
        csDB = plat.getCourierScooterDB();
        plat.setDeliveryRunDB(mock(DeliveryRunDB.class));
        runDB = plat.getDeliveryRunDB();
        plat.setRegisterScooter(mock(RegisterScooter.class));
        rScoot = plat.getRegisterScooter();
        plat.setCourierDB(mock(CourierDB.class));
        cDB = plat.getCourierDB();
        date1 = new Date();
        date1.setTime(1);
        sc = new ScooterCharging();
        parkingSlotScootersUsage = new ParkingSlotScootersUsage(1,1,date1, date1);
    }

    @Test
    void placeScooterToCharge(){
        Scooter scooter = new Scooter(1,1,1,1,1,1,1);
        Date date = new Date();
        date.setTime(1);
        CourierScooter cs = new CourierScooter(date,date,1,1);
        Courier courier = new Courier("c",1,1,1,1,1,"e");
        when(runDB.getLastCourierDeliveryRunDate(anyInt())).thenReturn(date);
        when(csDB.addCourierScooter(cs)).thenReturn(true);
        assertEquals(date.getTime(),sc.placeScooterToCharge(courier,scooter).getTime());
    }

    @Test
    void getParkSlotsScootersInUse() {
        ArrayList<ParkingSlot> arrayList = new ArrayList<>();
        when(sc.getParkSlotsScootersInUse(1,date1)).thenReturn(arrayList);
        int size = sc.getParkSlotsScootersInUse(1,new Date()).size();
        assertEquals(0,size);
    }

    @Test
    void saveParkingSlotScootersUsage() {
        when(pssuDB.getParkingSlotScootersUsage(1,1,date1)).thenReturn(parkingSlotScootersUsage);
        when(pssuDB.addParkingSlotScootersUsage(parkingSlotScootersUsage)).thenReturn(true);
        when(sc.saveParkingSlotScootersUsage(parkingSlotScootersUsage)).thenThrow(new IllegalArgumentException());
        assertTrue(sc.saveParkingSlotScootersUsage(parkingSlotScootersUsage));
        assertNotEquals(false,sc.saveParkingSlotScootersUsage(parkingSlotScootersUsage));

        when(sc.saveParkingSlotScootersUsage(parkingSlotScootersUsage)).thenReturn(true);
        assertTrue(sc.saveParkingSlotScootersUsage(parkingSlotScootersUsage));
        assertNotEquals(false,sc.saveParkingSlotScootersUsage(parkingSlotScootersUsage));

    }

    @Test
    void removeParkingSlotScootersUsage() {

        when(pssuDB.removeParkingSlotScootersUsage(1,1,date1)).thenReturn(true);
        assertTrue(sc.removeParkingSlotScootersUsage(parkingSlotScootersUsage));
        assertNotEquals(false,sc.removeParkingSlotScootersUsage(parkingSlotScootersUsage));

        when(pssuDB.removeParkingSlotScootersUsage(1,1,date1)).thenReturn(false);
        assertFalse(sc.removeParkingSlotScootersUsage(parkingSlotScootersUsage));
        assertNotEquals(true,sc.removeParkingSlotScootersUsage(parkingSlotScootersUsage));
    }

    @Test
    void updateParkingSlotScootersUsage() {
        when(pssuDB.removeParkingSlotScootersUsage(1,1,date1)).thenReturn(true);
        when(sc.removeParkingSlotScootersUsage(parkingSlotScootersUsage)).thenReturn(true);
        when(pssuDB.addParkingSlotScootersUsage(parkingSlotScootersUsage)).thenReturn(true);
        assertTrue(sc.updateParkingSlotScootersUsage(parkingSlotScootersUsage));
        assertNotEquals(false,sc.updateParkingSlotScootersUsage(parkingSlotScootersUsage));
        when(pssuDB.removeParkingSlotScootersUsage(1,1,date1)).thenReturn(false);
        when(pssuDB.addParkingSlotScootersUsage(parkingSlotScootersUsage)).thenReturn(false);
        assertFalse(sc.updateParkingSlotScootersUsage(parkingSlotScootersUsage));
        assertNotEquals(true,sc.updateParkingSlotScootersUsage(parkingSlotScootersUsage));
    }

    @Test
    void getParkingSlotScootersUsage() {
        ParkingSlotScootersUsage newPSSU = new ParkingSlotScootersUsage(0,0,date1,date1);
        when(pssuDB.getParkingSlotScootersUsage(1,1,date1)).thenReturn(parkingSlotScootersUsage);
        assertEquals(parkingSlotScootersUsage,ScooterCharging.getParkingSlotScootersUsage(1,1,date1));
        assertNotEquals(newPSSU,ScooterCharging.getParkingSlotScootersUsage(1,1,date1));
        when(pssuDB.getParkingSlotScootersUsage(1,1,date1)).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> {
            ScooterCharging.getParkingSlotScootersUsage(1,1,date1);
        });

    }

    @Test
    void getVacantChargeableScooterParkingSlot(){
        ParkingSlot ps = new ParkingSlot(1,1);
        when(psDB.getVacantChargeableScooterParkingSlot(anyInt())).thenReturn(ps);
        assertEquals(1,sc.getVacantChargeableScooterParkingSlot(1).getPharmacyId());
        assertNotEquals(2,sc.getVacantChargeableScooterParkingSlot(1).getPharmacyId());
    }


    @Test
    void getActualTime() {
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(30);
        expected.add(0);
        ArrayList<Integer> result = sc.getActualTime(1.50);
        assertEquals(expected.get(0),result.get(0));
        assertEquals(expected.get(1),result.get(1));
        assertEquals(expected.get(2),result.get(2));
    }

    @Test
    void calculateChargedBattery() {
        assertEquals(94.7,sc.calculateChargedBattery(30,13214123,1241235,12312321),0.1);
    }

    @Test
    void normalTimeToDateTime() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(30);
        arrayList.add(15);
        assertEquals(5415000,sc.normalTimeToDateTime(arrayList));
    }
}