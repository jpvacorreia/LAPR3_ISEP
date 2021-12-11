package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.*;
import lapr.project.utils.Constants;
import lapr.project.utils.GraphOperations;
import lapr.project.utils.Pair;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int orderID;
    private int clientID;
    private int pharmacyID;
    private int state;
    private Date orderDate;
    private ArrayList<OrderLine> cart;

    public Order(int orderID, int clientID, Date orderDate, int state, ArrayList<OrderLine> cart, int pharmacyID){
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderDate = new Date(orderDate.getTime());
        this.state = state;
        this.cart = new ArrayList<>(cart);
        this.pharmacyID = pharmacyID;
    }

    public Order (int clientID, Date orderDate, int state, ArrayList<OrderLine> cart, int pharmacyID){
        this.clientID = clientID;
        this.state = state;
        this.orderDate = new Date(orderDate.getTime());
        this.cart = new ArrayList<>(cart);
        this.pharmacyID = pharmacyID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setCart(ArrayList<OrderLine> cart) {
        this.cart = new ArrayList<>(cart);
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getClientID() {
        return clientID;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getState() {
        return state;
    }

    public void setState(int state){
        this.state = state;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = new Date(orderDate.getTime());
    }

    public void setPharmacyID(int pharmacyID) {
        this.pharmacyID = pharmacyID;
    }

    public ArrayList<OrderLine> getCart() {
        return new ArrayList<>(cart);
    }

    public int getPharmacyID() {
        return pharmacyID;
    }

    /**
     * Processes the payment associated with a specific order.
     *
     * @param useCredits        boolean indicating if the shipping costs are to be paid by Client's credits
     *
     * @return True             returns true if the method finishes making the payment.
     */
    public boolean makePayment(boolean useCredits){
        Pharmacy ph = ApplicationPOT.getInstance().getPlatform().getPharmacyDB().getPharmacyByID(pharmacyID);
        Client c = ApplicationPOT.getInstance().getPlatform().getClientDB().getClientByID(clientID);
        double shipping = GraphOperations.calculateDistance(Pair.createPair(ph.getAddress().getCoordinateX(), ph.getAddress().getCoordinateY()), Pair.createPair(c.getAddress().getCoordinateX(), c.getAddress().getCoordinateY())) * Constants.SHIPPING_RATIO;
        double total = calculatePrice(cart);
        ApplicationPOT.getInstance().getPlatform().getClientDB().getClientByID(this.clientID).addCredits((int)total);
        if(!useCredits) {
            total = total + shipping;
        }else{
            ApplicationPOT.getInstance().getPlatform().getClientDB().getClientByID(this.clientID).removeCredits();
        }

        ApplicationPOT.getInstance().getPlatform().getClientDB().updateCredits(this.clientID, ApplicationPOT.getInstance().getPlatform().getClientDB().getClientByID(this.clientID).getCredits());
        Invoice invoice = generateInvoice();
        String subject = "Here's your Invoice!";
        String body = invoice.toString(total);
        ApplicationPOT.getInstance().getPlatform().getSendEmail().sendEmail(ApplicationPOT.getInstance().getPlatform().getClientDB().getClientByID(this.clientID).getEmail(), subject, body);
        setState(Constants.PROCESSING);
        ApplicationPOT.getInstance().getPlatform().getClientOrderDB().updateOrderStatus(this.orderID, Constants.PROCESSING);
        return true;
    }

    /**
     * Creates an invoice associated with a specific order.
     *
     * @return inv      Returns the created invoice
     */
    public Invoice generateInvoice(){
        InvoiceDB invDB = ApplicationPOT.getInstance().getPlatform().getInvoiceDB();
        InvoiceLineDB invLineDB = ApplicationPOT.getInstance().getPlatform().getInvoiceLineDB();
        Invoice inv = new Invoice(this.orderID, Date.from(Instant.now()));
        invDB.addInvoice(inv);
        int invID = invDB.getLastInvoiceID();
        inv.setInvoiceID(invID);
        for(OrderLine ol : cart){
            InvoiceLine invLine = new InvoiceLine(invID, ol.getIdOrder(), ol.getIdProduct());
            invLineDB.addInvoiceLine(invLine);
        }
        inv.setLines(invLineDB.getInvoiceLinesFromInvoiceID(invID));
        return inv;
    }

    /**
     * Processes the order and calculates the overall price to be paid for the order.
     *
     * @param cart          List of Order Lines to calculate the sum.
     *
     * @return total        Sum to be paid for.
     */
    public double calculatePrice(ArrayList<OrderLine> cart){
        double total = 0;
        ProductDB prodDB = ApplicationPOT.getInstance().getPlatform().getProductDB();
        for (OrderLine ol :cart){
            Product p = prodDB.getProduct(ol.getIdProduct());
            total = total + p.getPrice() * ol.getQuantity();
        }
        return total;
    }

    /**
     * Adds a Client's order to the Data Base.
     *
     * @param order         Order made by the Client.
     *
     * @return True         True if the order is successfully added.
     */
    public static boolean addClientOrder(Order order){
        return ApplicationPOT.getInstance().getPlatform().getClientOrderDB().addClientOrder(order);
    }


    /**
     * Deletes this Order from the DataBase
     *
     * @return True         True if the order is successfully removed
     */
    public boolean delete(){
        return ApplicationPOT.getInstance().getPlatform().getClientOrderDB().removeClientOrder(this.orderID);
    }


    /**
     * Returns a list of Orders from the Data Base where their state is pending.
     *
     * @return list         List of orders that are pending
     */
    public static ArrayList<Order> getAllPendingOrders() {
        return ApplicationPOT.getInstance().getPlatform().getClientOrderDB().getAllOrdersWithState(Constants.PROCESSING);
    }

    /**
     * Removes a product from the cart (list of products) of an order yet to be processed.
     *
     * @param productID     Product identification number to be removed from order
     *
     * @return true         True if the products is successfully removed from an order cart.
     */
    public boolean removeProduct(int productID){
        for(OrderLine ol : cart){
            if(ol.getIdProduct() == productID){
                return ol.remove();
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", clientID=" + clientID +
                ", pharmacyID=" + pharmacyID +
                ", state=" + state +
                ", orderDate=" + orderDate +
                ", cart=" + cart +
                '}';
    }
}
