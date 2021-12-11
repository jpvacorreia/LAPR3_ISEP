package lapr.project.model;

import lapr.project.data.ProductDB;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class ProductTest {

    ProductDB pDB = mock(ProductDB.class);

    private Product p1 = new Product(1,"p1",1,1,1);
    private Product p2 = new Product(2,"p2",2,2,2);

    @Test
    void getName() {
        assertEquals("p1",p1.getName());
        assertEquals("p2",p2.getName());
    }

    @Test
    void getPrice() {
        assertEquals(2,p2.getPrice());
        assertEquals(1,p1.getPrice());
    }


    @Test
    void getId() {
        assertEquals(1,p1.getId());
        assertEquals(2,p2.getId());
    }

    @Test
    void getWeight() {
        assertEquals(1,p1.getWeight());
        assertEquals(2,p2.getWeight());
    }

    @Test
    void getId_type() {
        assertEquals(1,p1.getId_type());
        assertEquals(2,p2.getId_type());
    }

    @Test
    void testProductConstructor() {
        Product prod1 = new Product(2.2);
        Product prod2 = new Product(2.2);
        assertEquals(prod1.getPrice(), prod2.getPrice());
    }

    @Test
    void testGetProductByID() {
        Product prod1 = new Product(1,"p1",1,1,1);
        Product prod2 = new Product(1,"p1",1,1,1);

        String prod2Name = prod2.getName();
        int id = prod2.getId();
        when(pDB.getProduct(anyInt())).thenReturn(prod2);

        assertEquals(prod2Name, pDB.getProduct(prod1.getId()).getName());
    }
}