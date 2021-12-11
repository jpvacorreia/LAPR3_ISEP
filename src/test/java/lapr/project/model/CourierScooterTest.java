package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.CourierScooterDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class CourierScooterTest {

    CourierScooter cs1;
    CourierScooter cs2;

    CourierScooterDB csDB;
    Platform plat;

    @BeforeEach
    void TestInstance() {
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setCourierScooterDB(mock(CourierScooterDB.class));
        csDB = plat.getCourierScooterDB();
        
        Date dateIn1 = new Date();
        dateIn1.setTime(1);
        Date dateOut1 = new Date();
        dateOut1.setTime(2);
        int scooterId1 = 10;
        int courierId1 = 75;
        cs1 = new CourierScooter(dateIn1, dateOut1, scooterId1, courierId1);
        
        Date dateIn2 = new Date();
        dateIn2.setTime(3);
        Date dateOut2 = new Date();
        dateOut2.setTime(4);
        int scooterId2 = 15;
        int courierId2 = 60;
        cs2 = new CourierScooter(dateIn2, dateOut2, scooterId2, courierId2);
        
    }

    @Test
    void getIdScooterTest() {
        assertEquals(10, cs1.getIdScooter());
        assertEquals(15, cs2.getIdScooter());
    }

    @Test
    void getIdCourierTest() {
        assertEquals(75, cs1.getIdCourier());
        assertEquals(60, cs2.getIdCourier());
    }

    @Test
    void getDateInTest() {
        assertEquals(1, cs1.getDateIn().getTime());
        assertEquals(3, cs2.getDateIn().getTime());
    }

    @Test
    void getDateOutTest() {
        assertEquals(2, cs1.getDateOut().getTime());
        assertEquals(4, cs2.getDateOut().getTime());
    }
}