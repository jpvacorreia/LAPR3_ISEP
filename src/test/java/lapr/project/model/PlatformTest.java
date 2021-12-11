package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.AddressDB;
import lapr.project.data.CreditCardDB;
import lapr.project.data.OrderLineDB;
import lapr.project.data.PostalCodeDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlatformTest {

    private Platform plat;

    @BeforeEach
    void setUp() {
        plat = ApplicationPOT.getInstance().getPlatform();
    }

    @Test
    void setDeliveryRunRegistry() {
        DeliveryRunRegistry drr = new DeliveryRunRegistry();
        plat.setDeliveryRunRegistry(drr);
        assertEquals(drr, plat.getDeliveryRunRegistry());
    }

    @Test
    void getParkingSlotsScootersDB() {
    }

    @Test
    void getParkingSlotScootersUsageDB() {
    }

    @Test
    void setParkingSlotScootersUsageDB() {
    }

    @Test
    void setParkingSlotsScootersDB() {
    }

    @Test
    void setCpv() {
    }

    @Test
    void setAddressDB() {
        AddressDB newOne = new AddressDB();
        plat.setAddressDB(newOne);
        assertEquals(newOne, plat.getAddressDB());
    }

    @Test
    void setClientDB() {
    }

    @Test
    void setClientOrderDB() {
    }

    @Test
    void setCourierDB() {
    }

    @Test
    void setCourierScooterDB() {
    }

    @Test
    void setCreditCardDB() {
        CreditCardDB ccDB = new CreditCardDB();
        plat.setCreditCardDB(ccDB);
        assertEquals(ccDB,plat.getCreditCardDB());
    }

    @Test
    void setDeliveryRunDB() {
    }

    @Test
    void setDeliveryRunLineDB() {

    }

    @Test
    void setInvoiceDB() {
    }

    @Test
    void setOrderLineDB() {
        OrderLineDB olDB = new OrderLineDB();
        plat.setOrderLineDB(olDB);
        assertEquals(olDB,plat.getOrderLineDB());
    }

    @Test
    void setPharmacyDB() {
    }

    @Test
    void setPostalCodeDB() {
        PostalCodeDB pcDB = new PostalCodeDB();
        plat.setPostalCodeDB(pcDB);
        assertEquals(pcDB,plat.getPostalCodeDB());
    }

    @Test
    void setProductDB() {
    }

    @Test
    void setRclnt() {
        RegisterClient rC = new RegisterClient();
        plat.setRclnt(rC);
        assertEquals(rC,plat.getRegisterClient());
    }

    @Test
    void setrCour() {
        RegisterCourier rC = new RegisterCourier();
        plat.setrCour(rC);
        assertEquals(rC, plat.getRegisterCourier());
    }

    @Test
    void setScooterDB() {
    }

    @Test
    void setUserDB() {
    }

    @Test
    void getRegisterClient() {
    }

    @Test
    void getRegisterCourier() {
    }

    @Test
    void getRclnt() {
    }

    @Test
    void getrCour() {
    }

    @Test
    void getAddressDB() {
    }

    @Test
    void getClientDB() {
    }

    @Test
    void getClientOrderDB() {
    }

    @Test
    void getCourierDB() {
    }

    @Test
    void getCourierScooterDB() {
    }

    @Test
    void getCreditCardDB() {
    }

    @Test
    void getDeliveryRunDB() {
    }

    @Test
    void getDeliveryRunLineDB() {
    }

    @Test
    void getInvoiceDB() {
    }

    @Test
    void getOrderLineDB() {
    }

    @Test
    void getPharmacyDB() {
    }

    @Test
    void getPostalCodeDB() {
    }

    @Test
    void getProductDB() {
    }

    @Test
    void getScooterDB() {
    }

    @Test
    void getUserDB() {
    }

    @Test
    void getSendEmail() {
    }

    @Test
    void setParkingSlotDB() {
    }

    @Test
    void getCpv() {
    }

    @Test
    void getParkingSlotDB() {
    }

    @Test
    void setSendEmail() {
    }

    @Test
    void getRegisterScooter() {
    }

    @Test
    void setRegisterScooter() {
        RegisterScooter rS = new RegisterScooter();
        plat.setRegisterScooter(rS);
        assertEquals(rS,plat.getRegisterScooter());
    }

    @Test
    void getRegisterDrone() {
    }

    @Test
    void setRegisterDrone() {
        RegisterDrone rD = new RegisterDrone();
        plat.setRegisterDrone(rD);
        assertEquals(rD, plat.getRegisterDrone());
    }

    @Test
    void getRegisterUser() {
    }

    @Test
    void setRegisterUser() {
        RegisterUser rU = new RegisterUser();
        plat.setRegisterUser(rU);
        assertEquals(rU, plat.getRegisterUser());
    }

    @Test
    void getDroneDB() {
    }

    @Test
    void setDroneDB() {
    }

    @Test
    void getInvoiceLineDB() {
    }

    @Test
    void setInvoiceLineDB() {
    }

    @Test
    void getDeliveryRunRegistry() {
    }
}