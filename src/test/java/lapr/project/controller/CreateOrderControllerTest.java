package lapr.project.controller;

import lapr.project.data.ClientDB;
import lapr.project.data.ClientOrderDB;
import lapr.project.data.PharmacyDB;
import lapr.project.data.StockDB;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateOrderControllerTest {
    CreateOrderController coct;
    CreateOrder co;
    ClientDB clientDB;
    PharmacyDB pharmacyDB;
    Platform platform;
    ArrayList<OrderLine> orderLines = new ArrayList<>();
    StockDB stockDB;
    List<Pharmacy> pharmacies = new ArrayList<>();
    ClientOrderDB clientOrderDB;

    @BeforeEach
    void setUp(){
        coct = new CreateOrderController();
        platform = ApplicationPOT.getInstance().getPlatform();
        platform.setClientDB(mock(ClientDB.class));
        clientDB = platform.getClientDB();
        platform.setPharmacyDB(mock(PharmacyDB.class));
        pharmacyDB = platform.getPharmacyDB();
        platform.setCreateOrder(mock(CreateOrder.class));
        co = platform.getCreateOrder();
        platform.setStockDB(mock(StockDB.class));
        stockDB = platform.getStockDB();
        platform.setClientOrderDB(mock(ClientOrderDB.class));
        clientOrderDB = platform.getClientOrderDB();

    }

    @Test
    void createOrder() {
        Client client = new Client("c","c","c","1-1",1,1,1,1,1,1,1, 15);
        Order order = new Order(0,1,new Date(),1,orderLines,1);
        when(clientDB.getClient(1)).thenReturn(client);
        when(pharmacyDB.getAllPharmacies()).thenReturn(pharmacies);
        when(co.createOrder(1,orderLines)).thenReturn(order);
        coct.co = new CreateOrder();
        assertEquals(0, coct.createOrder(1,orderLines).getOrderID());
    }

    @Test
    void confirmedOrder() throws IOException {
        Stock stock = new Stock(1);
        Client client = new Client("c","c","c","1-1",1,1,1,1,1,1,1, 15);
        Order order = new Order(1,1,new Date(),1,orderLines,1);
        when(stockDB.getStock(anyInt())).thenReturn(stock);
        when(clientDB.addClient(client)).thenReturn(true);
        when(clientOrderDB.addClientOrder(order)).thenReturn(true);
        when(co.confirmedOrder(order,orderLines)).thenReturn(true);
        when(coct.confirmedOrder(order,orderLines)).thenReturn(true);
        assertTrue(coct.confirmedOrder(order, orderLines));
    }

}