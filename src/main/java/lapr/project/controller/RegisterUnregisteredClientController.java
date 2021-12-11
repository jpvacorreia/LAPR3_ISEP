package lapr.project.controller;

import lapr.project.model.Client;
import lapr.project.model.RegisterClient;
import lapr.project.model.RegisterUser;
import lapr.project.model.User;

import java.util.Date;

public class RegisterUnregisteredClientController {
    private final int USERTYPE = 1;
    RegisterClient rclnt;
    RegisterUser rusr;
    Client clnt;
    User usr;

    public RegisterUnregisteredClientController() {
        rclnt = ApplicationPOT.getInstance().getPlatform().getRegisterClient();
        rusr = ApplicationPOT.getInstance().getPlatform().getRegisterUser();
    }


    public Client newClient(String name, String email, String password, String address, String postalCode, double coordinateX, double coordinateY,
                          int nif, int creditCardNumber, int validity_year, int validity_month, int ccv, double elevation) {
        clnt = rclnt.newClient(name, email, address, postalCode, coordinateX, coordinateY, nif, creditCardNumber, validity_year, validity_month, ccv, elevation);
        usr = rusr.newUser(email, password, USERTYPE);
        if (rusr.registerUser(usr)) {
            rclnt.registerClient(clnt);
            return clnt;
        }
        return null;
    }

}
