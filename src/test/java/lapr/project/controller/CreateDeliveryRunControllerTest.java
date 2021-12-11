package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CreateDeliveryRunControllerTest {
    CreateDeliveryRunController cdrc;
    DeliveryRunRegistry deliveryRunRegistry;
    Platform plat;
    RegisterCourier rc;
    CourierDB cDB;
    ScooterDB sDB;
    Courier courier;
    Scooter scooter;
    Pharmacy ph;
    Date date1;
    Order order1;
    Order order2;
    Order order3;
    ArrayList<Order> toDeliver;
    DeliveryRun dr1;
    PharmacyDB phDB;
    ProductDB pDB;
    Product p;
    DeliveryRunDB drDB;

    @BeforeEach
    void setUp() {
        cdrc = new CreateDeliveryRunController();

        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setCourierDB(mock(CourierDB.class));
        cDB = plat.getCourierDB();
        plat.setScooterDB(mock(ScooterDB.class));
        sDB = plat.getScooterDB();
        plat.setPharmacyDB(mock(PharmacyDB.class));
        phDB = plat.getPharmacyDB();
        plat.setProductDB(mock(ProductDB.class));
        pDB = plat.getProductDB();
        plat.setrCour(mock(RegisterCourier.class));
        rc = plat.getRegisterCourier();
        plat.setDeliveryRunDB(mock(DeliveryRunDB.class));
        drDB = plat.getDeliveryRunDB();

        plat.setDeliveryRunRegistry(mock(DeliveryRunRegistry.class));
        deliveryRunRegistry = plat.getDeliveryRunRegistry();

        cdrc.drr = deliveryRunRegistry;

        scooter = new Scooter(78, 10000, 65.4, 250, 13, 5, 1);
        courier = new Courier(1,"Courier1", 1234567898, 123456789, 78.7, 78, 1, "courier1@email.com");

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
        order3 = new Order(42, 37, date1,1, cart, 1);

        toDeliver = new ArrayList<>();
        toDeliver.add(order1);
        toDeliver.add(order2);
        toDeliver.add(order3);

        dr1 = new DeliveryRun(1, toDeliver, ph);
    }




    @Test
    void confirmDeliveryRun() {
        when(drDB.addDeliveryRun(dr1)).thenReturn(true);
        when(cdrc.drr.confirmDeliveryRun(dr1)).thenReturn(true);
        cdrc.dr = dr1;
        assertTrue(cdrc.confirmDeliveryRun());
    }

    @Test
    void getDrr() {
        assertEquals(cdrc.drr, cdrc.getDrr());
    }

    @Test
    void setDrr() {
        cdrc.setDrr(deliveryRunRegistry);
        assertEquals(deliveryRunRegistry,cdrc.getDrr());
        DeliveryRunRegistry ddr2 = new DeliveryRunRegistry();
        assertNotEquals(ddr2,cdrc.getDrr());
    }
}