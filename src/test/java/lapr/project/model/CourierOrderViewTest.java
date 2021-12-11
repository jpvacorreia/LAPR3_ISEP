package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.ClientOrderDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CourierOrderViewTest {
    CourierOrderView cov;
    Platform platform;
    ClientOrderDB coDB;
    ArrayList<Order> list = new ArrayList<>();

    @BeforeEach
    void setUp(){
        cov = new CourierOrderView();
        platform = ApplicationPOT.getInstance().getPlatform();
        platform.setClientOrderDB(mock(ClientOrderDB.class));
        coDB = platform.getClientOrderDB();
    }
    @Test
    void getAllOrders() {
        when(coDB.getAllOrdersWithState(anyInt())).thenReturn(list);
        assertEquals(0,cov.getAllOrders().size());
    }
}