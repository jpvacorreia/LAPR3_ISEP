package lapr.project.model;

import lapr.project.controller.ApplicationPOT;

public class StockLine {
    int pharmacyID;
    int productID;
    int quantity;

    public StockLine(int pharmacyID, int productID, int quantity) {
        this.pharmacyID = pharmacyID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public int getPharmacyID() {
        return pharmacyID;
    }

    public int getProductID() {
        return productID;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * Adds a number (quantity) to the present quantity (this.quantity)
     *
     * @param quantity - Quantity ordered of the same product in the same pharmacy at the moment it gets called
     */
    public void addQuantity(int quantity){
        this.quantity = this.quantity + quantity;
    }

    /**
     * Updates this StockLine in the database when an order with the product inlcuded
     * on this StockLine in the same pharmacy is ordered
     *
     * @param ordered - Quantity ordered
     * @return boolean - True when updates successfully
     */
    public boolean update(int ordered){
        this.quantity = this.quantity - ordered;
        return ApplicationPOT.getInstance().getPlatform().getStockLineDB().updateStockLine(this.pharmacyID, this.productID, this.quantity); }

}
