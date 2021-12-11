package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.ProductDB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InvoiceTest {
    Invoice invoice;
    Platform plat;
    ProductDB pDB;
    ArrayList<InvoiceLine> list = new ArrayList<>();

    @BeforeEach
    void setUp(){
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setProductDB(mock(ProductDB.class));
        pDB = plat.getProductDB();
        Date date = new Date();
        date.setTime(2);
        list.add(new InvoiceLine(1,1,1,1));
        invoice = new Invoice(1,date,list);
    }


    @Test
    void getInvoiceID() {
        assertEquals(0, invoice.getInvoiceID());
    }

    @Test
    void getOrderID() {
        assertEquals(1, invoice.getOrderID());
    }

    @Test
    void getInvoiceData() {
        assertEquals(2,invoice.getInvoiceData().getTime());
    }

    @Test
    void getLines() {
        assertEquals(1,invoice.getLines().size());
    }

    @Test
    void setInvoiceID() {
        invoice.setInvoiceID(90);
        assertEquals(90,invoice.getInvoiceID());
    }

    @Test
    void setLines() {
        list.add(new InvoiceLine(1,1,1,2));
        invoice.setLines(list);
        assertEquals(2,invoice.getLines().size());
    }

    @Test
    void testToString() {
        Product p = new Product(4, "p",12, 21,1);
        when(Product.getProductByID(anyInt())).thenReturn(p);
        String expected = "Your total is: 0.00\np - 21.0";
        assertEquals(expected,invoice.toString(0));
    }
}