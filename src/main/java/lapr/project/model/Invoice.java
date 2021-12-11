package lapr.project.model;

import java.util.Date;
import java.util.List;

public class Invoice {
    int invoiceID;
    int orderID;
    Date invoiceData;
    List<InvoiceLine> lines;

    public Invoice(int orderID, Date invoiceData){
        this.orderID = orderID;
        this.lines = null;
        this.invoiceData = invoiceData;
    }

    public Invoice(int orderID, Date invoiceData, List<InvoiceLine> lines){
        this.orderID = orderID;
        this.lines = lines;
        this.invoiceData = invoiceData;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public int getOrderID() {
        return orderID;
    }

    public Date getInvoiceData() {
        return invoiceData;
    }

    public List<InvoiceLine> getLines() {
        return lines;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public void setLines(List<InvoiceLine> lines) {
        this.lines = lines;
    }

    public String toString(double total){
        String ret = String.format("Your total is: %.2f", total);
        for(InvoiceLine il : lines){
            ret += "\n" + il.toString();
        }
        return ret;
    }
}
