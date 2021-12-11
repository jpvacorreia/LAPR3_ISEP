package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.ClientDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class ClientTest {

    Platform plat;

    ClientDB cDB;

    Client clnt;
    Client clnt2;
    Address add1;

    @BeforeEach
    void TestInstance() {
        plat = ApplicationPOT.getInstance().getPlatform();
        plat.setClientDB(mock(ClientDB.class));
        cDB = plat.getClientDB();

        clnt = new Client("client1", "oldfdsjb", "rua estranha", "1234-567",
                1561.28651, 15651.513, 123456789, 12356489, 2022, 9, 124, 15);
        clnt.addCredits(15);

        clnt2 = new Client("client1", "oldfdsjb", "rua estranha", "1234-567",
                1561.28651, 15651.513, 12349, 123456789, 2022, 9, 124, 15);
        clnt2.addCredits(15);

        add1 = new Address("rua estranha", "1234-567", 1561.28651, 15651.513, 75);


    }

    @Test
    void getName() {
        String expected = "client1";
        String actual = clnt.getName();
        assertEquals(expected, actual);
    }

    @Test
    void getEmail() {
        String expected = "oldfdsjb";
        String actual = clnt.getEmail();
        assertEquals(expected, actual);
    }

    @Test
    void getAddress() {
        Address add2 = clnt.getAddress();
        String expected = add1.getStreet();
        String actual = add2.getStreet();
        assertEquals(expected, actual);

        double expected2 = add1.getCoordinateX();
        double actual2 = add2.getCoordinateX();
        assertEquals(expected2, actual2);

        double expected3 = add1.getCoordinateY();
        double actual3 = add2.getCoordinateY();
        assertEquals(expected3, actual3);
    }

    @Test
    void getPostalCode() {
        Address add1 = new Address("rua estranha", "1234-567", 1561.28651, 15651.513);
        add1.setElevation(76);
        Address add2 = clnt.getAddress();
        PostalCode pc1 = add1.getPostalCode();
        PostalCode pc2 = add2.getPostalCode();
        String p1 = pc1.toString();
        String p2 = pc2.toString();
        assertEquals(p1, p2);
    }

    @Test
    void getCredCard() {
        CreditCard cc1 = new CreditCard(12356489, 2022, 9, 124);
        CreditCard cc2 = clnt.getCredCard();
        double expected = cc1.getCreditCardNumber();
        double actual = cc2.getCreditCardNumber();
        assertEquals(expected, actual);

        int expected2 = cc1.getValidity_month();
        int actual2 = cc2.getValidity_month();
        assertEquals(expected2, actual2);

        int expected3 = cc1.getValidity_year();
        int actual3 = cc2.getValidity_year();
        assertEquals(expected3, actual3);

        int expected4 = cc1.getCvv();
        int actual4 = cc2.getCvv();
        assertEquals(expected4, actual4);
    }

    @Test
    void getNif() {
        int expected = 123456789;
        int actual = clnt.getNif();
        assertEquals(expected, actual);
    }

    @Test
    void getCredits() {
        int expected = 15;
        Client clnt2 = new Client("client1", "oldfdsjb", "rua estranha", "1234-567",
                1561.28651, 15651.513, 123456789, 12356489, 2022, 9, 124, 15);
        clnt2.addCredits(15);
        int actual = clnt2.getCredits();
        assertEquals(expected, actual);
    }

    @Test
    void setCredits() {
        int expected = clnt.getCredits() + 20;
        clnt.addCredits(20);
        int actual = clnt.getCredits();
        assertEquals(expected, actual);
    }

    @Test
    void setCreditCard() {
        CreditCard expected = new CreditCard(123456, 2022, 5, 356);
        clnt.setCredCard(expected);
        assertEquals(expected, clnt.getCredCard());
    }

    @Test
    void setAddress() {
        Address add1 = new Address("rua estranha", "1234-567", 1561.28651, 15651.513);
        clnt.setAddress(add1);
        assertEquals(add1, clnt.getAddress());
    }

    @Test
    void getElevation() {
        assertEquals(75, add1.getElevation());
    }


}