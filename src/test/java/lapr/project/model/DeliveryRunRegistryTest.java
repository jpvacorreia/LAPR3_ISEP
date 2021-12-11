package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.DeliveryRunDB;
import lapr.project.data.PharmacyDB;
import lapr.project.data.ProductDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeliveryRunRegistryTest {

    Platform plat;
    PharmacyDB phDB;
    ProductDB pDB;
    Pharmacy ph;
    Product p;
    DeliveryRun dr1;
    ArrayList<Order> toDeliver;
    Order order1;
    Order order2;
    Order order3;

    DeliveryRunDB drDB;

    @BeforeEach
    void setUp() {
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setDeliveryRunRegistry(new DeliveryRunRegistry());
        plat.setPharmacyDB(mock(PharmacyDB.class));
        phDB = plat.getPharmacyDB();
        plat.setProductDB(mock(ProductDB.class));
        pDB = plat.getProductDB();
        plat.setDeliveryRunDB(mock(DeliveryRunDB.class));
        drDB = plat.getDeliveryRunDB();

        ph = new Pharmacy("p1", 111111111, 1, "4815-668", "address",4.31231423, 4.1235123,1,1,1,1);
        p = new Product(1,"p1",1,1,1);

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

        toDeliver = new ArrayList<>();
        toDeliver.add(order1);
        toDeliver.add(order2);
        toDeliver.add(order3);

        dr1 = new DeliveryRun(1, toDeliver, ph);

    }

    @Test
    void confirmDeliveryRun() {
        when(drDB.addDeliveryRun(dr1)).thenReturn(true);
        assertTrue(plat.getDeliveryRunRegistry().confirmDeliveryRun(dr1));

    }
}