package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.StockLineDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class StockLineTest {

    StockLine sl;
    Platform platform;
    StockLineDB slDB;

    @BeforeEach
    void setUp() {
        platform = ApplicationPOT.getInstance().getPlatform();
        platform.setStockLineDB(mock(StockLineDB.class));
        slDB = platform.getStockLineDB();
        sl = new StockLine(1,2,3);

    }

    @Test
    void getPharmacyID() {
        assertEquals(1,sl.getPharmacyID());
    }

    @Test
    void getProductID() {
        assertEquals(2,sl.getProductID());
    }

    @Test
    void getQuantity() {
        assertEquals(3,sl.getQuantity());
    }

    @Test
    void addQuantity() {
        sl.addQuantity(3);
        assertEquals(6,sl.getQuantity());
    }

    @Test
    void update() {
        int qtt = sl.getQuantity();
        when(slDB.updateStockLine(anyInt(),anyInt(),anyInt())).thenReturn(true);
        assertTrue(sl.update(1));

        int updatedQtt = sl.getQuantity();
        assertTrue(((qtt - updatedQtt)) < qtt);
    }
}