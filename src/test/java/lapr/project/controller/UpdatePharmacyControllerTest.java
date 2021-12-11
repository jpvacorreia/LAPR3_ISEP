package lapr.project.controller;

import lapr.project.data.PharmacyDB;
import lapr.project.model.Pharmacy;
import lapr.project.model.Platform;
import lapr.project.model.UpdatePharmacy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class UpdatePharmacyControllerTest {
    Platform plat;
    PharmacyDB pDB;
    Pharmacy ph;
    Pharmacy ph2;
    UpdatePharmacy uph;
    UpdatePharmacyController uphc;



    @BeforeEach
    void TestInstance() {
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setPharmacyDB(mock(PharmacyDB.class));
        pDB = plat.getPharmacyDB();
        plat.setUpdatePharmacy(mock(UpdatePharmacy.class));
        uph = plat.getUpdatePharmacy();
        uphc = new UpdatePharmacyController();

        ph = new Pharmacy("p1", 111111111, 1, "4815-668", "address",4.31231423, 4.1235123,111,11,11,1);
        ph2 = new Pharmacy("p2", 111111111, 1, "4815-668", "address",4.31231423, 4.1235123,111,11,11,1);
    }


    @Test
    void removePharmacy() {

        when(pDB.getPharmacyByNif(1)).thenThrow(new IllegalArgumentException());
        when(pDB.removePharmacy(anyInt())).thenReturn(false);
        assertFalse(uphc.removePharmacy(1));

        when(pDB.getPharmacyByNif(111111111)).thenReturn(ph);
        when(pDB.removePharmacy(anyInt())).thenReturn(true);
        when(uph.removePharmacy(anyInt())).thenReturn(true);
        assertTrue(uphc.removePharmacy(ph.getNif()));

        when(pDB.getPharmacyByID(anyInt())).thenThrow(new IllegalArgumentException());
        when(uph.removePharmacy(anyInt())).thenReturn(false);
        assertFalse(uphc.removePharmacy(2));
    }

    @Test
    void getPharmacy() {
        when(pDB.getPharmacyByNif(anyInt())).thenReturn(ph);
        when(UpdatePharmacy.getPharmacyByNif(anyInt())).thenReturn(ph);
        when(UpdatePharmacy.getPharmacyByNif(anyInt())).thenReturn(ph);
        assertEquals(ph,uphc.getPharmacy(111111111));
    }

    @Test
    void addPharmacy() {
        when(pDB.getPharmacyByNif(111111111)).thenThrow(new IllegalArgumentException());
        when(pDB.addPharmacy(ph)).thenReturn(true);
        uphc.up = new UpdatePharmacy();
        assertTrue(uphc.addPharmacy(ph));

        when(pDB.getPharmacyByNif(anyInt())).thenReturn(ph);
        assertFalse(uphc.addPharmacy(ph));
    }

    @Test
    void updatePharmacy() {
        when(pDB.updatePharmacy(ph)).thenReturn(true);
        when(uph.updatePharmacy(ph)).thenReturn(true);
        assertTrue(uphc.updatePharmacy(ph));
        assertNotEquals(false, uphc.updatePharmacy(ph));

        when(pDB.updatePharmacy(ph)).thenThrow(new IllegalArgumentException());
        when(uph.updatePharmacy(ph)).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> {
           uphc.updatePharmacy(ph);
        });

        when(pDB.updatePharmacy(ph2)).thenReturn(false);
        assertFalse(uphc.updatePharmacy(ph2));
    }
}