package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CreateOrderTest {

    CreateOrder co;
    ClientDB clientDB;
    PharmacyDB pharmacyDB;
    Platform platform;
    ArrayList<OrderLine> orderLines = new ArrayList<>();
    StockDB stockDB;
    StockLineDB slDB;
    ClientOrderDB coDB;

    ArrayList<Pharmacy> listPh;
    ArrayList<OrderLine> cart;
    Client client;
    Order order;
    Pharmacy ph;
    Pharmacy ph2;


    @BeforeEach
    void setUp(){
        platform = ApplicationPOT.getInstance().getPlatform();
        platform.setClientDB(mock(ClientDB.class));
        clientDB = platform.getClientDB();
        platform.setPharmacyDB(mock(PharmacyDB.class));
        pharmacyDB = platform.getPharmacyDB();
        //platform.setCreateOrder(mock(CreateOrder.class));
        co = platform.getCreateOrder();
        platform.setStockDB(mock(StockDB.class));
        stockDB = platform.getStockDB();
        platform.setStockLineDB(mock(StockLineDB.class));
        slDB = platform.getStockLineDB();
        platform.setClientOrderDB(mock(ClientOrderDB.class));
        coDB = platform.getClientOrderDB();

        client = new Client("c","c","c","1-1",1,1,1,1,1,1,1, 15);
        order = new Order(1,1,new Date(),1,orderLines,1);
        ph = new Pharmacy("p1", 111111111, 1, "4815-668", "address",4.31231423, 4.1235123,111,11,11,1);
        ph2 = new Pharmacy("p2", 111111112, 1, "4815-668", "address",4.31231450, 4.1235150,111,11,11,1);

        listPh = new ArrayList<>();
        listPh.add(ph);
        listPh.add(ph2);

        cart = new ArrayList<>();
        cart.add(new OrderLine(1,2,3));
        cart.add(new OrderLine(1,3,4));
    }

    @Test
    void createOrder() {
        co = mock(CreateOrder.class);

        when(clientDB.getClient(1)).thenReturn(client);
        when(pharmacyDB.getAllPharmacies()).thenReturn(listPh);
        when(co.createOrder(1,cart)).thenReturn(order);
        Order orderA = co.createOrder(1,cart);
        assertEquals(1, orderA.getOrderID());
    }

    @Test
    void confirmedOrder() throws IOException {

        Stock stock = new Stock(1);
        Client client = new Client("c","c","c","1-1",1,1,1,1,1,1,1, 15);
        Order order = new Order(1,1,new Date(),1,cart,1);
        when(stockDB.getStock(anyInt())).thenReturn(stock);
        when(clientDB.addClient(client)).thenReturn(true);
        when(slDB.updateStockLine(anyInt(), anyInt(), anyInt())).thenReturn(true);
        when(pharmacyDB.getPharmacyByID(anyInt())).thenReturn(ph);
        when(pharmacyDB.getAllPharmacies()).thenReturn(listPh);
        when(coDB.addClientOrder(order)).thenReturn(true);
        when(co.confirmedOrder(order,orderLines)).thenReturn(true);
        assertTrue(co.confirmedOrder(order, orderLines));
    }

}