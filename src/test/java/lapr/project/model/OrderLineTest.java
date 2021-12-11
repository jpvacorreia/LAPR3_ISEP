package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.OrderLineDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class OrderLineTest {

    OrderLine ol;
    OrderLine ol2;
    Platform plat;
    OrderLineDB olDB;

    @BeforeEach
    void setUp() {
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setOrderLineDB(mock(OrderLineDB.class));
        olDB = plat.getOrderLineDB();

        ol = new OrderLine(1,1,2);
        ol2 = new OrderLine(1,3);

    }

    @Test
    void setIdOrder() {
        ol.setIdOrder(10);
        assertEquals(10, ol.getIdOrder());

    }


    @Test
    void addQuantity() {
        ol.addQuantity(2);
        assertEquals(4,ol.getQuantity());
    }

    @Test
    void getIdOrder() {
        assertEquals(1, ol.getIdOrder());
    }

    @Test
    void getIdProduct() {
        assertEquals(1, ol.getIdProduct());
        assertEquals(1, ol2.getIdProduct());
    }

    @Test
    void getQuantity() {
        assertEquals(2,ol.getQuantity());
        assertEquals(3,ol2.getQuantity());
    }

    @Test
    void remove(){
        when(olDB.removeOrderLine(anyInt(), anyInt())).thenReturn(true);
        assertTrue(ol.remove());
    }
}