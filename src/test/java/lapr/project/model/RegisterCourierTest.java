package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.CourierDB;
import lapr.project.utils.Constants;
import lapr.project.utils.PasswordGeneration;
import lapr.project.data.api.SendEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class RegisterCourierTest {
    Platform plat;
    RegisterCourier rCourier;
    CourierDB cDB;
    Courier cour;
    Courier cour2;
    User user;
    User user3;
    SendEmail sEmail;

    @BeforeEach
    void setUp() {
        rCourier = new RegisterCourier();
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setCourierDB(mock(CourierDB.class));
        cDB = plat.getCourierDB();
        plat.setSendEmail(mock(SendEmail.class));
        sEmail = plat.getSendEmail();

        cour = new Courier("Courier1", 1234567898, 123456789, 78.7, 78, 1, "courier1@email.com");
        cour2 = new Courier("Courier2", 1234567898, 12345678, 78.7, 78, 1, "courier1@email.com");

        user = ApplicationPOT.getInstance().getPlatform().getRegisterUser().newUser(cour.getEmail(),  PasswordGeneration.generatePassword(), Constants.COURIER);
        user3 = new User("email", "pwd", 2);
    }

    @Test
    void newCourier() {
        assertEquals(cour.getName(),rCourier.newCourier("Courier1", 1234567898, 123456789, 78.7, 78, 1, "courier1@email.com").getName());
    }

    @Test
    void registerCourier() {
        when(cDB.getCourier(123456789)).thenReturn(cour2);
        when(cDB.addCourier(cour2)).thenReturn(false);
        assertFalse(rCourier.registerCourier(cour2));

        when(cDB.getCourier(anyInt())).thenThrow(new IllegalArgumentException());
        when(cDB.addCourier(cour)).thenReturn(true);
        assertTrue(rCourier.registerCourier(cour));

        when(cDB.addCourier(cour2)).thenReturn(false);
        assertFalse(rCourier.registerCourier(cour2));



    }

    @Test
    void delete() {
        when(cDB.removeCourier(anyInt())).thenReturn(true);
        assertTrue(rCourier.delete(cour));
        verify(cDB).removeCourier(anyInt());

        when(cDB.removeCourier(cour.getNif())).thenReturn(false);
        assertFalse(rCourier.delete(cour));

        when(cDB.getCourier(cour.getNif())).thenThrow(new IllegalArgumentException());
        assertFalse(rCourier.delete(cour));
    }

    @Test
    void getCourierNIFByEmail() {
        when(cDB.getCourierNIFByEmail(anyString())).thenReturn(1);
        int actual = rCourier.getCourierNIFByEmail("ja1958@gmail.com");
        assertEquals(1, actual);
    }

    @Test
    void getCourier(){
        when(cDB.getCourier(123456789)).thenReturn(cour);
        assertEquals(cour, RegisterCourier.getCourier(123456789));

    }


}
