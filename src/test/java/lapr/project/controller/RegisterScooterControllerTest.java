package lapr.project.controller;

import lapr.project.data.CourierDB;
import lapr.project.data.CourierScooterDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.*;
import lapr.project.data.api.SendEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegisterScooterControllerTest {

    RegisterScooterController rScootC;
    Platform plat;
    RegisterScooter rScoot;
    Scooter scoot1;
    Scooter scoot2;
    ScooterDB sDB;
    SendEmail sEmail;
    CourierScooter cs;
    CourierScooterDB csDB;
    CourierDB cDB;
    Courier courier;

    @BeforeEach
    void setUp() {
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setRegisterScooter(mock(RegisterScooter.class));
        rScoot = plat.getRegisterScooter();
        rScootC = new RegisterScooterController();
        rScootC.rscoot = rScoot;

        plat.setScooterDB(mock(ScooterDB.class));
        sDB = plat.getScooterDB();
        plat.setCourierScooterDB(mock(CourierScooterDB.class));
        csDB = plat.getCourierScooterDB();
        plat.setCourierDB(mock(CourierDB.class));
        cDB = plat.getCourierDB();
        plat.setSendEmail(mock(SendEmail.class));
        sEmail = plat.getSendEmail();

        scoot1 = new Scooter(20211, 10000, 100, 250, 13, 5, 1);
        scoot2 = new Scooter(20212, 10000, 65.4, 250, 13, 5, 1);

        rScootC.scoot = scoot1;

        Date dateIn2 = new Date();
        dateIn2.setTime(3);
        Date dateOut2 = new Date();
        dateOut2.setTime(4);
        int scooterId2 = 15;
        int courierId2 = 60;
        cs = new CourierScooter(dateIn2, dateOut2, scooterId2, courierId2);

        courier = new Courier(1,"Courier1", 1234567898, 123456789, 78.7, 78, 1, "courier1@email.com");

    }

    @Test
    void getRscoot() {
        assertEquals(rScootC.rscoot,rScootC.getRscoot());
    }

    @Test
    void newScooter() {
        assertTrue(rScootC.newScooter(20211, 10000, 100, 250, 13, 5, 1));
        when(rScoot.newScooter(20211, 10000, 100, 250, 13, 5, 1)).thenThrow(new IllegalArgumentException());
        assertFalse(rScootC.newScooter(20211, 10000, 100, 250, 13, 5, 1));
    }

    @Test
    void saveScooter() {
        when(sDB.getScooterByID(anyInt())).thenReturn(scoot2);
        when(sDB.addScooter(scoot2)).thenReturn(false);
        when(rScoot.saveScooter(scoot2)).thenReturn(false);
        assertFalse(rScootC.saveScooter());
    }

    @Test
    void removeScooter() {
        when(sDB.removeScooter(anyInt())).thenReturn(true);
        when(rScoot.removeScooter(scoot2)).thenReturn(true);
        rScootC.scoot = scoot2;
        rScootC.rscoot = rScoot;
        assertTrue(rScootC.removeScooter());

        when(sDB.removeScooter(anyInt())).thenReturn(false);
        when(rScoot.removeScooter(scoot2)).thenReturn(false);
        rScootC.scoot = scoot2;
        rScootC.rscoot = rScoot;
        assertFalse(rScootC.removeScooter());
    }

    @Test
    void updateScooter() {
        when(sDB.getScooterByID(20211)).thenThrow(new IllegalArgumentException());
        assertFalse(rScoot.updateScooter(scoot1));

        when(sDB.getScooterByID(anyInt())).thenReturn(scoot1);
        when(sDB.updateScooter(scoot1)).thenReturn(true);
        when(rScoot.updateScooter(scoot1)).thenReturn(true);
        rScootC.scoot = scoot1;
        rScootC.rscoot = rScoot;
        assertTrue(rScootC.updateScooter());

        when(sDB.updateScooter(scoot1)).thenReturn(false);
        when(rScoot.updateScooter(scoot1)).thenReturn(false);
        rScootC.scoot = scoot1;
        rScootC.rscoot = rScoot;
        assertFalse(rScootC.updateScooter());
    }
}