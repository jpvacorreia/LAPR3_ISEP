package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.PharmacyDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UpdatePharmacyTest {

    Platform plat;
    PharmacyDB pDB;
    Pharmacy ph;
    UpdatePharmacy uph;


    @BeforeEach
    void TestInstance() {
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setPharmacyDB(mock(PharmacyDB.class));
        pDB = plat.getPharmacyDB();
        uph = new UpdatePharmacy();

        ph = new Pharmacy("p1", 111111111, 1, "4815-668", "address", 4.31231423, 4.1235123, 111, 11, 11, 1);
    }


    @Test
    void removePharmacy() {
        when(pDB.getPharmacyByNif(1)).thenThrow(new IllegalArgumentException());
        when(pDB.removePharmacy(anyInt())).thenReturn(false);
        assertFalse(uph.removePharmacy(1));

        when(pDB.getPharmacyByNif(111111111)).thenReturn(ph);
        when(pDB.removePharmacy(anyInt())).thenReturn(true);
        assertTrue(uph.removePharmacy(ph.getNif()));

        when(pDB.getPharmacyByID(anyInt())).thenThrow(new IllegalArgumentException());
        assertFalse(uph.removePharmacy(2));
    }

    @Test
    void getPharmacy() {
        when(pDB.getPharmacyByNif(anyInt())).thenReturn(ph);
        assertEquals(ph, UpdatePharmacy.getPharmacyByNif(111111111));
    }

    @Test
    void addPharmacy() {
        when(pDB.getPharmacyByNif(111111111)).thenThrow(new IllegalArgumentException());
        when(pDB.addPharmacy(ph)).thenReturn(true);
        assertTrue(uph.addPharmacy(ph));

        when(pDB.getPharmacyByNif(anyInt())).thenReturn(ph);
        assertFalse(uph.addPharmacy(ph));
    }

    @Test
    void updatePharmacy() {
        when(pDB.updatePharmacy(ph)).thenReturn(true);
        assertTrue(uph.updatePharmacy(ph));
        assertNotEquals(false, uph.updatePharmacy(ph));

        when(pDB.updatePharmacy(ph)).thenReturn(false);
        assertFalse(uph.updatePharmacy(ph));

        when(pDB.updatePharmacy(ph)).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> {
            uph.updatePharmacy(ph);
        });
    }
}