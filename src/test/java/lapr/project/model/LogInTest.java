package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.ClientDB;
import lapr.project.data.UserDB;
import lapr.project.utils.Constants;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class LogInTest {

    Platform plat;
    ClientDB cDB;
    RegisterUser rUser;
    UserDB uDB;
    User user;
    LogIn logIn;

    @BeforeEach
    void setUp() {
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setLogIn(mock(LogIn.class));
        logIn = plat.getLogIn();
        plat.setClientDB(mock(ClientDB.class));
        cDB = plat.getClientDB();
        plat.setUserDB(mock(UserDB.class));
        uDB = plat.getUserDB();
        rUser = plat.getRegisterUser();
        user = rUser.newUser("ja1958@gmail.com","a1b2c3d4", Constants.CLIENT);
    }

}