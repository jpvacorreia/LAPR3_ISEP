package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.utils.Constants;
import lapr.project.utils.GraphOperations;
import lapr.project.utils.Pair;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateOrder {

    public CreateOrder() {

    }

    /**
     * Creates an order from a client and a list of order lines whichs contain the products ordered.
     * Returns the created order.
     *
     * @param clientID      Client identification number.
     * @param array         List of order lines to be added to the order.
     *
     * @return order        Order to be delivered
     */
    public Order createOrder(int clientID, ArrayList<OrderLine> array) {
        int pharmacyID = -1;
        double distance = Double.MAX_VALUE;
        Client c = ApplicationPOT.getInstance().getPlatform().getClientDB().getClientByID(clientID);
        List<Pharmacy> list = ApplicationPOT.getInstance().getPlatform().getPharmacyDB().getAllPharmacies();
        for (Pharmacy ph : list) {
            double calc = GraphOperations.calculateDistance(Pair.createPair(ph.getAddress().getCoordinateX(), ph.getAddress().getCoordinateY()), Pair.createPair(c.getAddress().getCoordinateX(), c.getAddress().getCoordinateY()));
            if (calc < distance) {
                distance = calc;
                pharmacyID = ph.getId();
            }
        }
        return new Order(clientID, Date.from(Instant.now()), Constants.PENDING, array, pharmacyID);
    }

    /**
     * After an order is made, each product stock is updated and the order confirmed for processing.
     *
     * @param order         Order object to be confirmed
     * @param cart          List of order lines corresponding to the cart associated with the order.
     *
     * @return true/false   Returns true if the order has been successfully confirmed into the platform and false otherwise
     */
    public boolean confirmedOrder(Order order, ArrayList<OrderLine> cart) throws IOException {
        order.setCart(cart);
        order.setState(Constants.PROCESSING);
        Stock stock = ApplicationPOT.getInstance().getPlatform().getStockDB().getStock(order.getPharmacyID());
        stock.updateStock(order);
        return Order.addClientOrder(order);
    }

}
