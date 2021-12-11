package lapr.project.model;


import lapr.project.utils.Constants;

public class Client {
    String name;
    String email;
    Address address;
    int nif;
    CreditCard credCard;
    int credits;

    public Client(String name, String email, String address, String postalCode, double coordinateX, double coordinateY,
                  int nif, long creditCardNumber, int validity_year, int validity_month, int ccv, double elevation) {
        this.name = name;
        this.email = email;
        this.address = new Address(address, postalCode, coordinateX, coordinateY, elevation);
        this.nif = nif;
        this.credCard = new CreditCard(creditCardNumber, validity_year, validity_month, ccv);
        this.credits = 0;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CreditCard getCredCard() {
        return credCard;
    }

    public void setCredCard(CreditCard credCard) {
        this.credCard = credCard;
    }

    public int getNif() {
        return nif;
    }

    public int getCredits() {
        return credits;
    }

    /**
     * Adds a fixed ammount of credits to a client.
     *
     * @param credits credits to add to a client
     */
    public void addCredits(int credits) {
        this.credits += credits;
    }

    /**
     * Remove credits from client after an order is paid for
     *
     */
    public void removeCredits(){
        this.credits = this.credits - Constants.MINIMUM_CREDITS_FOR_FREE_SHIPPING;
    }




}
