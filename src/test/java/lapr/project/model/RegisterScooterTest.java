package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.CourierDB;
import lapr.project.data.CourierScooterDB;
import lapr.project.data.ScooterDB;
import lapr.project.data.api.SendEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import lapr.project.utils.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class RegisterScooterTest {

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
        plat.setRegisterScooter(new RegisterScooter());
        rScoot = plat.getRegisterScooter();
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
    void newScooter() {
        Scooter actual = rScoot.newScooter(20211, 10000, 100, 250, 13, 5, 1);
        assertEquals(scoot1.toString(), actual.toString());
    }

    @Test
    void saveScooter() {
        when(sDB.getScooterByID(20211)).thenReturn(scoot1);
        when(sDB.addScooter(scoot1)).thenReturn(false);
        assertFalse(rScoot.saveScooter(scoot1));

        when(sDB.getScooterByID(20211)).thenThrow(new IllegalArgumentException());
        when(sDB.addScooter(scoot1)).thenReturn(true);
        assertTrue(rScoot.saveScooter(scoot1));

        when(sDB.getScooterByID(anyInt())).thenThrow(new IllegalArgumentException());
        when(sDB.addScooter(scoot2)).thenReturn(false);
        assertFalse(rScoot.saveScooter(scoot2));
    }

    @Test
    void removeScooter() {
        when(sDB.getScooterByID(20212)).thenReturn(scoot1);
        when(sDB.removeScooter(scoot2.getId())).thenReturn(true);
        assertTrue(rScoot.removeScooter(scoot2));
        when(sDB.removeScooter(scoot1.getId())).thenReturn(false);
        assertFalse(rScoot.removeScooter(scoot1));

        when(sDB.getScooterByID(20211)).thenThrow(new IllegalArgumentException());
        assertFalse(rScoot.removeScooter(scoot1));
    }

    @Test
    void updateScooter() {
        when(sDB.getScooterByID(20211)).thenThrow(new IllegalArgumentException());
        assertFalse(rScoot.updateScooter(scoot1));

        when(sDB.getScooterByID(anyInt())).thenReturn(scoot1);
        when(sDB.updateScooter(scoot1)).thenReturn(true);
        assertTrue(rScoot.updateScooter(scoot1));
        when(sDB.updateScooter(scoot2)).thenReturn(false);
        assertFalse(rScoot.updateScooter(scoot2));
    }

    @Test
    void getScooterByID() {
        when(sDB.getScooterByID(anyInt())).thenReturn(scoot1);
        assertEquals(scoot1.toString(), sDB.getScooterByID(20211).toString());
        verify(sDB).getScooterByID(20211);
    }

    
    @Test 
    void energyWastedBetweenTwoPoints(){
        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();
        Edge e1 = new Edge("Rua da Street", 30, v1, v2, false, 0);
        double weight = 100;
        int windDirection = 0;
        double expected = 5979.34026;
        double actual = RegisterScooter.energyWastedBetweenTwoPoints(v1, v2, e1, weight, windDirection);
        assertEquals(expected, actual, 0.1);
        weight = 250;
        windDirection = 60;
        Pair<Double,Double> pair1 = new Pair<>(4.15113,-8.4123);
        Pair<Double,Double> pair2 = new Pair<>(4.16113,-8.5123);
        v1.setCoordinates(pair1);
        v2.setCoordinates(pair2);
        Edge e2 = new Edge("Teste",0.1,v1,v2, false, 60);
        assertEquals(23.758769,RegisterScooter.energyWastedBetweenTwoPoints(v1,v2,e2,weight, windDirection), 0.1);
        assertNotEquals(8.17268112,RegisterScooter.energyWastedBetweenTwoPoints(v1,v2,e2,weight, windDirection));
    }
}