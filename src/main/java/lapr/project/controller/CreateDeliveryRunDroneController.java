package lapr.project.controller;

import lapr.project.data.api.DeliveryRoute;
import lapr.project.model.DeliveryRun;
import lapr.project.model.DeliveryRunRegistry;
import lapr.project.model.Order;

import java.util.ArrayList;

public class CreateDeliveryRunDroneController {
    DeliveryRunRegistry drr;
    DeliveryRun dr;

    public CreateDeliveryRunDroneController(){
        this.drr = ApplicationPOT.getInstance().getPlatform().getDeliveryRunRegistry();
    }

    public DeliveryRun createDeliveryRun(int idDrone, ArrayList<Order> toDeliver, int pharmacyID){
        dr = drr.createDeliveryRun(idDrone, toDeliver, pharmacyID);
        return dr;
    }

    public boolean confirmDeliveryRun(){
        return drr.confirmDeliveryRun(dr);
    }

    public DeliveryRoute generateDeliveryRoute(int choice) throws RuntimeException{
        dr = drr.generateDeliveryRouteDrone(choice, dr);
        return dr.getDeliveryRoute();
    }
}
