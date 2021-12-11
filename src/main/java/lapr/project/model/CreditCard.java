package lapr.project.model;


public class CreditCard {
    long creditCardNumber;
    int validity_year;
    int validity_month;
    int cvv;

    public CreditCard(long creditCardNumber, int validity_year, int validity_month, int cvv) {
        this.creditCardNumber = creditCardNumber;
        this.validity_year = validity_year;
        this.validity_month = validity_month;
        this.cvv = cvv;
    }

    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    public int getValidity_year() {
        return validity_year;
    }

    public int getValidity_month() {
        return validity_month;
    }

    public int getCvv() {
        return cvv;
    }


    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardNumber=" + creditCardNumber +
                ", validity_year=" + validity_year +
                ", validity_month=" + validity_month +
                '}';
    }
}
