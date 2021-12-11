package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.Constants;


public class RegisterCourierController {
    RegisterCourier rcour;
    RegisterUser rusr;
    Courier cour;
    User usr;

    public RegisterCourierController() {
        rcour = ApplicationPOT.getInstance().getPlatform().getRegisterCourier();
        rusr = ApplicationPOT.getInstance().getPlatform().getRegisterUser();
    }

    public Courier newCourier(String name, double niss, int nif, double weight, int scooterId, int pharmacyId, String email, String password) {
        cour = rcour.newCourier(name, niss, nif, weight, scooterId, pharmacyId, email);
        usr = rusr.newUser(email, password, Constants.COURIER);
        if (rusr.registerUser(usr)) {
            if (rcour.registerCourier(cour)) {
                return cour;
            }
            rusr.delete(usr);
        }
        return null;
    }

}
