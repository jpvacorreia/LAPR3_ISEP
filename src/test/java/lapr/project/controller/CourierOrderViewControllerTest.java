package lapr.project.controller;

import lapr.project.data.ClientOrderDB;
import lapr.project.model.CourierOrderView;
import lapr.project.model.Order;
import lapr.project.model.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourierOrderViewControllerTest {
    CourierOrderViewController covC;
    Platform platform;
    ClientOrderDB coDB;
    ArrayList<Order> list = new ArrayList<>();

    @BeforeEach
    void setUp(){
        covC = new CourierOrderViewController();
        platform = ApplicationPOT.getInstance().getPlatform();
        platform.setClientOrderDB(mock(ClientOrderDB.class));
        coDB = platform.getClientOrderDB();
    }
    @Test
    void getAllOrders() {
        when(coDB.getAllOrdersWithState(anyInt())).thenReturn(list);
        assertEquals(0,covC.getAllOrders().size());
    }
}