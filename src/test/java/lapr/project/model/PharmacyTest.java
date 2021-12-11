package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.PharmacyDB;
import lapr.project.data.api.ProximityMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PharmacyTest {
    Platform plat;
    PharmacyDB pDB;
    UpdatePharmacy up;
    ArrayList<Product> list;
    Pharmacy ph;
    Pharmacy ph2;
    Pharmacy ph3;


    @BeforeEach
    void TestInstance() {
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setPharmacyDB(mock(PharmacyDB.class));
        pDB = plat.getPharmacyDB();
        plat.setUpdatePharmacy(mock(UpdatePharmacy.class));
        up = plat.getUpdatePharmacy();

        ph = new Pharmacy("p1", 111111111, 1, "4815-668", "address", 4.31231423, 4.1235123, 111, 11, 11,1);
        Address add1 = new Address("rua estranha", "1234-567", 1561.28651, 15651.513, 75);
        ph2 = new Pharmacy(1, "p1", 111111111, 1, add1,  111, 11, 11);


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


    @Test
    void getCurrentInput() {
        assertEquals(11, ph.getCurrentInput());
    }

    @Test
    void getVoltageInput() {
        assertEquals(111, ph.getVoltageInput());
    }

    @Test
    void getMaxScooters() {
        assertEquals(11, ph.getMaxScooters());
    }


    @Test
    void getName() {
        assertEquals("p1", ph.getName());
    }

    @Test
    void getIdAdmin() {
        assertEquals(1, ph.getIdAdmin());
    }

    @Test
    void getAddress() {
        assertEquals(4.31231423, ph.getAddress().getCoordinateX());
    }

    @Test
    void getId() {
        assertEquals(0, ph.getId());
    }

    @Test
    void getNif() {
        assertEquals(111111111, ph.getNif());
    }

    @Test
    void setId() {
        ph.setId(10);
        assertEquals(10, ph.getId());
    }

    @Test
    void getPharmacy() {
        when(pDB.getPharmacyByNif(anyInt())).thenReturn(ph);
        assertEquals(ph, UpdatePharmacy.getPharmacyByNif(111111111));
    }

    @Test
    void getPharmacyByID() {
        when(pDB.getPharmacyByID(anyInt())).thenReturn(ph);
        assertEquals(ph, UpdatePharmacy.getPharmacyByID(1));
    }


    @Test
    void testToString() {
        assertEquals("Pharmacy{" +
                "id=" + ph.getId() +
                ", nif=" + ph.getNif() +
                ", name='" + ph.getName() + '\'' +
                ", idAdmin=" + ph.getIdAdmin() +
                ", address=" + ph.getAddress() +
                '}', ph.toString());
    }

    @Test
    void setNif() {
        ph2.setNif(1234);
        assertEquals(1234, ph2.getNif());
    }

    @Test
    void setName() {
        ph2.setName("pharmacy1");
        assertEquals("pharmacy1", ph2.getName());
    }

    @Test
    void setIdAdmin() {
        ph2.setIdAdmin(5);
        assertEquals(5, ph2.getIdAdmin());
    }

    @Test
    void setAddress() {
        Address add1 = new Address("rua estranha", "1234-567", 1561.28651, 15651.513, 75);
        ph2.setAddress(add1);
        assertEquals(add1.toString(), ph2.getAddress().toString());
    }

    @Test
    void setVoltageInput() {
        ph2.setVoltageInput(230);
        assertEquals(230, ph2.getVoltageInput());
    }

    @Test
    void setCurrentInput() {
        ph2.setCurrentInput(13);
        assertEquals(13, ph2.getCurrentInput());
    }

    @Test
    void setMaxScooters() {
        ph2.setMaxScooters(4);
        assertEquals(4, ph2.getMaxScooters());
    }
}