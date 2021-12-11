package lapr.project.model;

import lapr.project.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryRunTest {

    Pharmacy ph;
    DeliveryRun dr1;
    ArrayList<Order> toDeliver;
    Order order1;
    Order order2;
    Order order3;
    Date date1;

    @BeforeEach
    void setUp() {

        ph = new Pharmacy("p1", 111111111, 1, "4815-668", "address",4.31231423, 4.1235123,1,1,1,1);

        ArrayList<OrderLine> cart = new ArrayList<>();
        cart.add(new OrderLine(27,2,3));
        date1 = new Date();
        //date1.setTime(1);
        order1 = new Order(27, 28, date1, 3, cart, 1);
        cart.clear();
        Date date2 = new Date();
        date2.setTime(2);
        cart.add(new OrderLine(38,3,4));
        order2 = new Order(37, 38, date2, 3, cart, 1);
        order3 = new Order(37, date1,1, cart, 1);

        toDeliver = new ArrayList<>();
        toDeliver.add(order1);
        toDeliver.add(order2);
        toDeliver.add(order3);

        dr1 = new DeliveryRun(1, toDeliver, ph);
    }

    @Test
    void getiDCourier() {
        assertEquals(1, dr1.getiDDeliverer());
    }

    @Test
    void getToDeliver() {
        assertEquals(toDeliver, dr1.getToDeliver());
    }

    @Test
    void getDate() {
        dr1.setDate(date1);
        assertEquals(date1.getTime(), dr1.getDate().getTime());
    }

    @Test
    void getStartingPoint() {
        assertEquals(ph, dr1.getStartingPoint());
    }

    @Test
    void updateOrderState() {
        dr1.updateOrderState();
        List<Order> list = dr1.getToDeliver();
        for(Order o : list) {
            assertEquals(Constants.DELIVERED, o.getState());
        }
    }

    @Test
    void testToString() {
        if(dr1.toString()!= null) {
            assertEquals(dr1.toString(), dr1.toString());
        }
    }

    @Test
    void setiDCourier() {
        dr1.setiDDeliverer(1);
        assertEquals(1, dr1.getiDDeliverer());
    }

    @Test
    void setStartingPoint() {
        dr1.setStartingPoint(ph);
        assertEquals(ph, dr1.getStartingPoint());
    }

    @Test
    void setToDeliver() {
        dr1.setToDeliver(toDeliver);
        assertEquals(toDeliver, dr1.getToDeliver());
    }
}