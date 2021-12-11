package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.AddressDB;
import lapr.project.data.PharmacyDB;
import lapr.project.data.api.ProximityMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProximityMapTest {
    Platform platform;
    PharmacyDB pharmacyDB;
    AddressDB addressDB;
    ProximityMap map;
    Pharmacy ph;

    @BeforeEach
    void setUp(){
        platform = ApplicationPOT.getInstance().getPlatform();
        platform.setPharmacyDB(mock(PharmacyDB.class));
        platform.setAddressDB(mock(AddressDB.class));
        addressDB = platform.getAddressDB();
        pharmacyDB = platform.getPharmacyDB();
        ph = new Pharmacy("p1", 111111111, 1, "4815-668", "address",4.31231423, 4.1235123,111,11,11,1);
        when(pharmacyDB.getPharmacyByNif(anyInt())).thenReturn(ph);
        when(addressDB.getAddress(anyDouble(),anyDouble())).thenReturn(ph.getAddress());
        map = new ProximityMap(true);
    }

    @Test
    void testToString() {
        assertEquals("ProximityMap{" +
                "map=" + map.getMap()+
                '}',map.toString());
    }


    @Test
    void getMap() {
        assertEquals(33,map.getMap().numVertices());
        assertNotEquals(0,map.getMap().numVertices());
    }
}