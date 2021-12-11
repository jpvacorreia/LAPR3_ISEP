package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {

    CreditCard cc;

    @BeforeEach
    void setUp(){
        cc = new CreditCard(12356489, 2022, 9, 124);
    }

    @Test
    void getCreditCardNumber() {
        assertEquals(12356489,cc.getCreditCardNumber());
    }

    @Test
    void getValidity_year() {
        assertEquals(2022,cc.getValidity_year());
    }

    @Test
    void getValidity_month() {
        assertEquals(9,cc.getValidity_month());
    }

    @Test
    void getCvv() {
        assertEquals(124,cc.getCvv());
    }

    @Test
    void testToString() {
        assertEquals("CreditCard{" +
                "creditCardNumber=" + 12356489 +
                ", validity_year=" + 2022 +
                ", validity_month=" + 9 +
                '}',cc.toString());
    }
}