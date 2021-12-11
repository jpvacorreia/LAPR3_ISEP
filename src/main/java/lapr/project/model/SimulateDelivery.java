package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.model.Order;

import java.util.List;


public class SimulateDelivery {

    /**
     * Retrieves all orders ready to deliver
     *
     * @return list with orders ready to deliver
     */
    public List<Order> getAllReadyOrders() {
        return Order.getAllPendingOrders();
    }
}
