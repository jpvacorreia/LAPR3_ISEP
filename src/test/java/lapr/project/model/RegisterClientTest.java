package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.ClientDB;
import lapr.project.data.UserDB;
import lapr.project.utils.Constants;
import lapr.project.utils.PasswordGeneration;
import lapr.project.data.api.SendEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class RegisterClientTest {

    RegisterClient regClnt;
    Platform plat;
    ClientDB cDB;
    UserDB uDB;
    Client clnt;
    Client clnt2;
    User user;
    SendEmail sEmail;

    @BeforeEach
    void setUp() {
        regClnt = new RegisterClient();
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setClientDB(mock(ClientDB.class));
        cDB = plat.getClientDB();
        plat.setUserDB(mock(UserDB.class));
        uDB = plat.getUserDB();
        plat.setSendEmail(mock(SendEmail.class));
        sEmail = plat.getSendEmail();

        clnt = new Client("client1", "oldfdsjb", "rua estranha", "1234-567",
                1561.28651, 15651.513, 123456789, 12356489, 2022, 9, 124, 15);
        clnt.addCredits(15);
        clnt2 = new Client("client1", "oldfdsjb", "rua estranha", "1234-567",
                1561.28651, 15651.513, 12349, 123456789, 2022, 9, 124, 15);
        clnt2.addCredits(15);

        user = ApplicationPOT.getInstance().getPlatform().getRegisterUser().newUser(clnt.getEmail(),  PasswordGeneration.generatePassword(), Constants.CLIENT);

    }

    @Test
    void newClient() {
        Client clnt3 = regClnt.newClient("client1", "oldfdsjb", "rua estranha", "1234-567",
                1561.28651, 15651.513, 123456789, 12356489, 2022, 9, 124, 15);
        clnt3.addCredits(15);
        assertEquals(clnt.getName(), clnt3.getName());
        assertEquals(clnt.getEmail(), clnt3.getEmail());
        assertEquals(clnt.getNif(), clnt3.getNif());
        assertEquals(clnt.getAddress().toString(), clnt3.getAddress().toString());
        assertEquals(clnt.getCredCard().toString(), clnt3.getCredCard().toString());
        assertEquals(clnt.getAddress().getPostalCode().toString(), clnt3.getAddress().getPostalCode().toString());
    }

    @Test
    void getClientIdByEmail() {
        when(cDB.getClientIdByEmail(anyString())).thenReturn(1);
        int actual = regClnt.getClientIdByEmail("ja1958@gmail.com");
        assertEquals(1, actual);
    }

    @Test
    void testGetClient() {
        when(cDB.getClient(123456789)).thenReturn(clnt);
        String clnt1Name = clnt.getName();
        assertEquals(clnt1Name, cDB.getClient(123456789).getName());
    }

    @Test
    void testSave() {
        when(cDB.getClient(12356489)).thenReturn(clnt);
        assertFalse(regClnt.registerClient(clnt));


        when(cDB.getClient(clnt.getNif())).thenThrow(new IllegalArgumentException());
        when(cDB.addClient(clnt)).thenReturn(true);
        assertTrue(regClnt.registerClient(clnt));
        assertNotEquals(false, regClnt.registerClient(clnt));


        when(cDB.addClient(clnt)).thenReturn(false);
        assertFalse(regClnt.registerClient(clnt));

    }

    @Test
    void testRemove() {
        when(cDB.getClient(clnt.getNif())).thenReturn(clnt);
        when(cDB.removeClient(clnt.getNif())).thenReturn(true);
        assertTrue(regClnt.remove(clnt));
        verify(cDB).removeClient(anyInt());

        when(cDB.removeClient(clnt.getNif())).thenReturn(false);
        assertFalse(regClnt.remove(clnt));

        when(cDB.getClient(clnt2.getNif())).thenThrow(new IllegalArgumentException());
        assertFalse(regClnt.remove(clnt2));


    }

}
