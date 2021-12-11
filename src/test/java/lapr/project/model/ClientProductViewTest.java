package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClientProductViewTest {

    Platform plat;
    StockLineDB stockLineDB;
    ArrayList<Product> list;
    ArrayList<StockLine> stockList;
    Stock stock;
    ClientProductView cpv;
    ProductDB productDB;
    @BeforeEach
    void TestInstance() {
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setStockLineDB(mock(StockLineDB.class));
        stockLineDB = plat.getStockLineDB();
        plat.setProductDB(mock(ProductDB.class));
        productDB = plat.getProductDB();
        cpv = new ClientProductView();
        list = new ArrayList<>();
        Product p1 = new Product(1);
        list.add(p1);
        stock = new Stock(1);
        StockLine sl = new StockLine(1, 2, 3);
        stockList = new ArrayList<>();
        stockList.add(sl);

    }

   @Test
    void getProducts() {
        when(productDB.getAllProducts()).thenReturn(list);
        assertEquals(list.size(),cpv.getProducts().size());
    }
}