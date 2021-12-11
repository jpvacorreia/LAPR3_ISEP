package lapr.project.model;

import lapr.project.controller.ApplicationPOT;

public class OrderLine {

    int idOrder;
    int idProduct;
    int quantity;

    public OrderLine(int idOrder, int idProduct, int quantity){
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public OrderLine(int idProduct, int quantity){
        this.quantity = quantity;
        this.idProduct = idProduct;
    }

    public void addQuantity(int quantity){this.quantity = this.quantity + quantity;}

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * Removes an order line corresponding to a product from the Data Base.
     *
     * @return true         True if the products is successfully removed from an order cart, false otherwise
     */
    public boolean remove(){
        ApplicationPOT.getInstance().getPlatform().getOrderLineDB().removeOrderLine(this.idOrder, this.idProduct);
        return true;
    }
}
