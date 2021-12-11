package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.UserDB;
import lapr.project.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class RegisterUserTest {
    Platform plat;
    RegisterUser rUser;
    UserDB uDB;
    User user;
    User user2;


    @BeforeEach
    void setUp() {
        rUser = new RegisterUser();
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setUserDB(mock(UserDB.class));
        uDB = plat.getUserDB();

        user = rUser.newUser("test", "testPWD", Constants.CLIENT);
        user2 = rUser.newUser("test2", "testPWD2", Constants.CLIENT);

    }

    @Test
    public void getUserTypeTest() {
        assertEquals(Constants.CLIENT, user.getUserType());
    }

    @Test
    public void getEmailTest() {
        assertEquals("test", user.getEmail());
    }

    @Test
    public void getPasswordTest() {
        assertEquals("testPWD", user.getPassword());
    }


    @Test
    void registerUser() {
        when(uDB.getUser("test")).thenThrow(new IllegalArgumentException());
        when(uDB.addUser(user)).thenReturn(true);

        assertTrue(rUser.registerUser(user));
        assertNotEquals(false,rUser.registerUser(user));

        assertFalse(rUser.registerUser(user2));
        assertNotEquals(true,rUser.registerUser(user2));

    }

    @Test
    void delete() {
        when(uDB.removeUser(anyString())).thenReturn(true);

        assertEquals(true, rUser.delete(user));
        verify(uDB).removeUser(anyString());
    }
}