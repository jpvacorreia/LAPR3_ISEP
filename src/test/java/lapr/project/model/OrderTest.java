package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.*;
import lapr.project.data.api.SendEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderTest {

    Order order1;
    Order order2;
    Order order3;

    ClientOrderDB coDB;
    Platform plat;
    ClientDB cDB;
    Client client;
    ProductDB pDB;
    Product prod;
    SendEmail sEmail;
    InvoiceDB iDB;
    Invoice invoice;
    InvoiceLineDB ilDB;
    InvoiceLine invoiceLine;
    OrderLineDB olDB;





    @BeforeEach
    void TestInstance() {
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setClientOrderDB(mock(ClientOrderDB.class));
        coDB = plat.getClientOrderDB();
        plat.setClientDB(mock(ClientDB.class));
        cDB = plat.getClientDB();
        plat.setProductDB(mock(ProductDB.class));
        pDB = plat.getProductDB();
        plat.setSendEmail(mock(SendEmail.class));
        sEmail = plat.getSendEmail();
        plat.setInvoiceDB(mock(InvoiceDB.class));
        iDB = plat.getInvoiceDB();
        plat.setInvoiceLineDB(mock(InvoiceLineDB.class));
        ilDB = plat.getInvoiceLineDB();
        plat.setOrderLineDB(mock(OrderLineDB.class));
        olDB = plat.getOrderLineDB();


        client = new Client("client1", "oldfdsjb", "rua estranha", "1234-567",
                1561.28651, 15651.513, 123456789, 12356489, 2022, 9, 124, 15);
        client.addCredits(15);

        prod = new Product(1,"p1",1,1,1);


        ArrayList<OrderLine> cart = new ArrayList<>();
        cart.add(new OrderLine(27,2,3));
        Date date1 = new Date();
        date1.setTime(1);
        order1 = new Order(27, 28, date1, 29, cart, 1);
        cart.clear();
        Date date2 = new Date();
        date2.setTime(2);
        cart.add(new OrderLine(38,3,4));
        order2 = new Order(37, 38, date2, 39, cart, 1);
        order3 = new Order(37, date1,1, cart, 1);

        invoice = new Invoice(1,date1);
        invoiceLine = new InvoiceLine(1,2,3);

    }

    @Test
    void setOrderID() {
        assertEquals(27,order1.getOrderID());
        order1.setOrderID(25);
        assertEquals(25,order1.getOrderID());
    }

    @Test
    void setCart() {
        assertEquals(1, order2.getCart().size());
        order2.setCart(new ArrayList<>());
        assertEquals(0, order2.getCart().size());
    }

    @Test
    void getOrderDate() {
        assertEquals(2, order2.getOrderDate().getTime());
        assertEquals(1, order1.getOrderDate().getTime());
    }

    @Test
    void getClientID() {
        assertEquals(28, order1.getClientID());
        assertEquals(38, order2.getClientID());
    }

    @Test
    void getOrderID() {
        assertEquals(27, order1.getOrderID());
        assertEquals(37, order2.getOrderID());
    }

    @Test
    void getState() {
        assertEquals(39, order2.getState());
        assertEquals(29, order1.getState());
    }

    @Test
    void getCart() {
        assertEquals(1,order1.getCart().size());
        assertEquals(1,order2.getCart().size());
    }

    @Test
    void setState() {
        order3.setState(2);
        assertEquals(2, order3.getState());
    }

    @Test
    void testAddClientOrder() {
        when(coDB.addClientOrder(order1)).thenReturn(true);

        assertEquals(true, Order.addClientOrder(order1));
    }

    @Test
    void testDelete() {
        when(coDB.removeClientOrder(order1.getOrderID())).thenReturn(true);

        assertEquals(true, order1.delete());
    }

    @Test
    void getAllPendingOrders() {
        ArrayList<Order> list = new ArrayList<>();
        list.add(order1);
        list.add(order2);
        list.add(order3);
        when(coDB.getAllOrdersWithState(anyInt())).thenReturn(list);

        assertEquals(3, Order.getAllPendingOrders().size());

    }

    @Test
    void getPharmacyID() {
        assertEquals(1,order1.getPharmacyID());
    }

    @Test
    void setClientID() {
        order1.setClientID(1);
        assertEquals(1, order1.getClientID());
    }

    @Test
    void setOrderDate() {
        Date date2 = new Date();
        date2.setTime(2);
        order1.setOrderDate(date2);
        assertEquals(date2, order1.getOrderDate());
    }

    @Test
    void setPharmacyID() {
        order1.setPharmacyID(1);
        assertEquals(1, order1.getPharmacyID());
    }

    @Test
    void removeProduct(){
        int prodTest = 2;
        when(olDB.removeOrderLine(anyInt(), anyInt())).thenReturn(true);
        assertTrue(order1.removeProduct(prodTest));
    }

    @Test
    void removeProductFalse(){
        int prodTest = 9999;
        when(olDB.removeOrderLine(anyInt(), anyInt())).thenReturn(false);
        assertFalse(order1.removeProduct(prodTest));
    }
}

