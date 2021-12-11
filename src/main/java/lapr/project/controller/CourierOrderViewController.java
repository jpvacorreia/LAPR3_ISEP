package lapr.project.controller;

import lapr.project.model.CourierOrderView;
import lapr.project.model.Order;

import java.util.ArrayList;

public class CourierOrderViewController {

    private CourierOrderView cov;

    public CourierOrderViewController(){
        this.cov = ApplicationPOT.getInstance().getPlatform().getCov();
    }

    public ArrayList<Order> getAllOrders(){
        return cov.getAllOrders();
    }
}
