package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.*;
import lapr.project.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientLogInControllerTest {

    private ClientLogInController ctrl;
    private UserDB userDB;
    private Platform plat;
    private RegisterClient registerClient;
    private User user;
    private LogIn logIn;
    private RegisterUser ruser;
    private ClientDB clientDB;

    @BeforeEach
    void setUp(){
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setUserDB(mock(UserDB.class));
        userDB = plat.getUserDB();
        plat.setRclnt(mock(RegisterClient.class));
        registerClient = plat.getRegisterClient();
        ctrl = new ClientLogInController();
        plat.setLogIn(mock(LogIn.class));
        logIn = plat.getLogIn();
        plat.setRegisterUser(mock(RegisterUser.class));
        ruser = plat.getRegisterUser();
        plat.setClientDB(mock(ClientDB.class));
        clientDB = plat.getClientDB();
        user = ruser.newUser("ja1958@gmail.com","a1b2c3d4", Constants.CLIENT);
    }

    @Test
    void getClientIdByEmail() {
        when(clientDB.getClientIdByEmail(anyString())).thenReturn(1);
        when(registerClient.getClientIdByEmail(anyString())).thenReturn(1);
        when(logIn.getClientIdByEmail(anyString())).thenReturn(1);
        ctrl.logIn = new LogIn();
        assertEquals(1,ctrl.getClientIdByEmail("email"));
    }

}