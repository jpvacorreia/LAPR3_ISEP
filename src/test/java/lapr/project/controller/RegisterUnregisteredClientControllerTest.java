package lapr.project.controller;

import lapr.project.data.ClientDB;
import lapr.project.data.UserDB;
import lapr.project.model.*;
import lapr.project.utils.Constants;
import lapr.project.utils.PasswordGeneration;
import lapr.project.data.api.SendEmail;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

class RegisterUnregisteredClientControllerTest {
    RegisterUnregisteredClientController rucc;
    RegisterClient regClnt;
    RegisterUser regUser;
    Platform plat;
    ClientDB cDB;
    UserDB uDB;
    Client clnt;
    Client clnt2;
    User user;
    SendEmail sEmail;

    @BeforeEach
    void setUp() {
        rucc = new RegisterUnregisteredClientController();
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setClientDB(mock(ClientDB.class));
        cDB = plat.getClientDB();
        plat.setUserDB(mock(UserDB.class));
        uDB = plat.getUserDB();
        plat.setSendEmail(mock(SendEmail.class));
        sEmail = plat.getSendEmail();
        plat.setRegisterUser(mock(RegisterUser.class));
        plat.setRclnt(mock(RegisterClient.class));
        regClnt = plat.getRegisterClient();
        regUser = plat.getRegisterUser();


        clnt = new Client("client1", "oldfdsjb", "rua estranha", "1234-567",
                1561.28651, 15651.513, 123456789, 12356489, 2022, 9, 124, 15);
        clnt.addCredits(15);
        clnt2 = new Client("client1", "oldfdsjb", "rua estranha", "1234-567",
                1561.28651, 15651.513, 12349, 123456789, 2022, 9, 124, 15);
        clnt2.addCredits(15);

        user = new User(clnt.getEmail(),  PasswordGeneration.generatePassword(), Constants.CLIENT);

    }

}