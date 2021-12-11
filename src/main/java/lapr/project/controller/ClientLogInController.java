package lapr.project.controller;

import lapr.project.model.*;

public class ClientLogInController {

    protected LogIn logIn;

    public ClientLogInController(){
        logIn = ApplicationPOT.getInstance().getPlatform().getLogIn();
    }

    public int logIn(String email, String pwd){
        int userType = logIn.logIn(email, pwd);
        if (userType != 0)
            return userType;
        return 0;
    }

    public int getClientIdByEmail(String email){
        return logIn.getClientIdByEmail(email);
    }
}
