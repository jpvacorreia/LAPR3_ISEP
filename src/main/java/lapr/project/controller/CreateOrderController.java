package lapr.project.controller;


import lapr.project.model.CreateOrder;
import lapr.project.model.Order;
import lapr.project.model.OrderLine;


import java.io.IOException;
import java.util.ArrayList;

public class CreateOrderController {

    CreateOrder co;
    public CreateOrderController(){
        co = ApplicationPOT.getInstance().getPlatform().getCreateOrder();
    }

    public Order createOrder(int clientID, ArrayList<OrderLine> array){
        return co.createOrder(clientID,array);
    }

    public boolean confirmedOrder (Order order, ArrayList<OrderLine> cart) throws IOException {
        return co.confirmedOrder(order,cart);
    }
}
