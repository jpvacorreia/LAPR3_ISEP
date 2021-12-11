package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.PharmacyDB;
import lapr.project.data.StockDB;
import lapr.project.data.StockLineDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class StockTest {
    Platform plat;
    Stock stock;
    StockDB stockDB;
    ArrayList<Product> list;
    StockLineDB stockLineDB;
    PharmacyDB pharmacyDB;


    @BeforeEach
    void TestInstance() {
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setStockDB(mock(StockDB.class));
        stockDB = plat.getStockDB();
        plat.setStockLineDB(mock(StockLineDB.class));
        stockLineDB = plat.getStockLineDB();
        plat.setPharmacyDB(mock(PharmacyDB.class));
        pharmacyDB = plat.getPharmacyDB();

        list = new ArrayList<>();
        Product p1 = new Product(1);
        Product p2 = new Product(1);
        Product p3 = new Product(1);
        Product p4 = new Product(1);
        Product p5 = new Product(1);
        Product p6 = new Product(1);

        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);

    }
    List<StockLine> stockList;

    @BeforeEach
    void setUp() {
        stock = new Stock(1);
        StockLine sl = new StockLine(1, 1, 3);
        StockLine sl2 = new StockLine(1, 2, 3);
        StockLine sl3 = new StockLine(1, 3, 3);
        StockLine sl4 = new StockLine(1, 4, 3);

        stockList = new ArrayList<>();
        stockList.add(sl);
        stockList.add(sl2);
        stockList.add(sl3);
        stockList.add(sl4);

    }

    @Test
    void getPharmacyID() {
        assertEquals(1, stock.getPharmacyID());
    }

    @Test
    void setStock() {
        stock.setStock(stockList);
        assertEquals(stockList.size(), stock.getStock().size());
    }

    @Test
    void updateStock() throws IOException {
        stock = mock(Stock.class);
        stock.setStock(stockList);
        Date date = new Date();
        ArrayList<OrderLine> cart = new ArrayList<>();
        cart.add(new OrderLine(1,1,5));
        cart.add(new OrderLine(1,2,6));
        Order order = new Order(1, date, 1 , cart, 1);
        Pharmacy ph = new Pharmacy("ph",1,1,"1-1","e",1,1,1,1,1,1);
        ph.setId(1);
        List<Pharmacy> list = new ArrayList<>();
        list.add(ph);
        StockLine sl = new StockLine(1,1,1);
        when(pharmacyDB.getPharmacyByID(1)).thenReturn(ph);
        when(pharmacyDB.getAllPharmacies()).thenReturn(list);
        when(stockLineDB.getStockLine(anyInt(),anyInt())).thenReturn(sl);
        when(stockLineDB.updateStockLine(1,1,1)).thenReturn(true);
        stock.updateStock(order);
        verify(stock,times(1)).updateStock(order);
    }
}