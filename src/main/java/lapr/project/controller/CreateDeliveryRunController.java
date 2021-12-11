package lapr.project.controller;

import lapr.project.data.api.DeliveryRoute;
import lapr.project.model.*;

import java.util.ArrayList;

public class CreateDeliveryRunController {

    DeliveryRunRegistry drr;
    DeliveryRun dr;

    public CreateDeliveryRunController(){
        this.drr = ApplicationPOT.getInstance().getPlatform().getDeliveryRunRegistry();
    }

    public DeliveryRun createDeliveryRun(String courierEmail, ArrayList<Order> toDeliver, int pharmacyID){

        dr = drr.createDeliveryRun(courierEmail, toDeliver, pharmacyID);
        return dr;
    }

    public boolean confirmDeliveryRun(){
        return drr.confirmDeliveryRun(dr);
    }

    public DeliveryRoute generateDeliveryRoute(int choice) throws RuntimeException{
        dr = drr.generateDeliveryRoute(choice, dr);
        return dr.getDeliveryRoute();
    }

    public DeliveryRunRegistry getDrr() {
        return drr;
    }

    public void setDrr(DeliveryRunRegistry drr) {
        this.drr = drr;
    }

}
