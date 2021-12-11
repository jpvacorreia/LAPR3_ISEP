package lapr.project.controller;

import lapr.project.data.CourierDB;
import lapr.project.data.UserDB;
import lapr.project.model.*;
import lapr.project.utils.Constants;
import lapr.project.utils.PasswordGeneration;
import lapr.project.data.api.SendEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegisterCourierControllerTest {
    Platform plat;
    RegisterCourier rCourier;
    CourierDB cDB;
    Courier cour;
    Courier cour2;
    User user;
    SendEmail sEmail;
    RegisterCourierController rCourC;
    UserDB userDB;

    @BeforeEach
    void setUp() {
        rCourC = new RegisterCourierController();
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setCourierDB(mock(CourierDB.class));
        cDB = plat.getCourierDB();
        plat.setSendEmail(mock(SendEmail.class));
        sEmail = plat.getSendEmail();
        plat.setrCour(mock(RegisterCourier.class));
        rCourier = plat.getRegisterCourier();
        plat.setUserDB(mock(UserDB.class));
        userDB = plat.getUserDB();

        cour = new Courier("Courier1", 1234567898, 123456789, 78.7, 78, 1, "courier1@email.com");
        cour2 = new Courier("Courier2", 1234567898, 12345678, 78.7, 78, 1, "courier1@email.com");

        user = ApplicationPOT.getInstance().getPlatform().getRegisterUser().newUser(cour.getEmail(), PasswordGeneration.generatePassword(), Constants.COURIER);

    }

    @Test
    void newCourier() {
        when(cDB.getCourier(anyInt())).thenThrow(new IllegalArgumentException());
        when(cDB.addCourier(cour)).thenReturn(true);
        when(userDB.getUser(anyString())).thenThrow(new IllegalArgumentException());
        when(userDB.addUser(any())).thenReturn(true);
        rCourC.rcour = new RegisterCourier();
        Courier actual = rCourC.newCourier("Courier1", 1234567898, 123456789, 78.7, 78, 1, "courier1@email.com", "password");
        //assertEquals(cour.getName(), actual.getName());
    }
}
