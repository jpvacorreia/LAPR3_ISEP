package lapr.project.model;

public class InvoiceLine {

    int invoiceLineID;
    int invoiceID;
    int orderID;
    int productID;

    public InvoiceLine(int invoiceLineID, int invoiceID, int orderID, int productID){
        this.invoiceLineID = invoiceLineID;
        this.invoiceID = invoiceID;
        this.orderID = orderID;
        this.productID = productID;
    }

    public InvoiceLine(int invoiceID, int orderID, int productID){
        this.invoiceID = invoiceID;
        this.orderID = orderID;
        this.productID = productID;
    }

    public int getInvoiceLineID() {
        return invoiceLineID;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public void setInvoiceLineID(int invoiceLineID) {
        this.invoiceLineID = invoiceLineID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String toString(){
        return Product.getProductByID(this.productID).getName() + " - " +Product.getProductByID(this.productID).getPrice();
    }
}
