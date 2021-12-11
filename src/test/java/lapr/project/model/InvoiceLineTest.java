package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.ProductDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InvoiceLineTest {
    InvoiceLine inv;
    Platform plat;
    ProductDB pDB;

    @BeforeEach
    void setUp(){
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setProductDB(mock(ProductDB.class));
        pDB = plat.getProductDB();
        inv = new InvoiceLine(1,2,3,4);
    }

    @Test
    void getInvoiceLineID() {
        assertEquals(1,inv.getInvoiceLineID());
    }

    @Test
    void getInvoiceID() {
        assertEquals(2,inv.getInvoiceID());
    }

    @Test
    void getOrderID() {
        assertEquals(3,inv.getOrderID());
    }

    @Test
    void getProductID() {
        assertEquals(4,inv.getProductID());
    }

    @Test
    void setOrderID() {

        inv.setOrderID(2);
        assertEquals(2,inv.getOrderID());
        assertNotEquals(1,inv.getOrderID());
    }

    @Test
    void setInvoiceID() {
        inv.setInvoiceID(3);
        assertNotEquals(2,inv.getInvoiceID());
        assertEquals(3,inv.getInvoiceID());
    }

    @Test
    void setInvoiceLineID() {
        inv.setInvoiceLineID(5);
        assertNotEquals(4,inv.getInvoiceLineID());
        assertEquals(5,inv.getInvoiceLineID());
    }

    @Test
    void setProductID() {
        inv.setProductID(9);
        assertEquals(9,inv.getProductID());
        assertNotEquals(1,inv.getProductID());
    }

    @Test
    void testToString() {
        Product p = new Product(4, "p",12, 21,1);
        when(Product.getProductByID(anyInt())).thenReturn(p);
        String expected = "p - "+21.0;
        assertEquals(expected,inv.toString());
    }
}