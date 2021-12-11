package lapr.project.model;

public class Pharmacy {

    private int id;
    private int nif;
    private String name;
    private int idAdmin;
    private Address address;
    private int voltageInput;
    private int currentInput;
    private int maxScooters;

    public Pharmacy(int id, String name, int nif, int idAdmin, Address address, int voltageInput, int currentInput, int maxScooters){
        this.id = id;
        this.name = name;
        this.nif = nif;
        this.idAdmin = idAdmin;
        this.address = address;
        this.currentInput = currentInput;
        this.voltageInput = voltageInput;
        this.maxScooters = maxScooters;
    }

    public Pharmacy(String name, int nif, int idAdmin, String postalcode, String address,double x, double y,int voltageInput, int currentInput, int maxScooters,double elevation){
        this.name = name;
        this.nif = nif;
        this.idAdmin = idAdmin;
        this.address = new Address(address,postalcode,x,y,elevation);
        this.currentInput = currentInput;
        this.voltageInput = voltageInput;
        this.maxScooters = maxScooters;
    }


    public int getCurrentInput() {
        return currentInput;
    }

    public int getVoltageInput() {
        return voltageInput;
    }

    public int getMaxScooters() {
        return maxScooters;
    }

    public String getName() {
        return name;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public Address getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setVoltageInput(int voltageInput) {
        this.voltageInput = voltageInput;
    }

    public void setCurrentInput(int currentInput) {
        this.currentInput = currentInput;
    }

    public void setMaxScooters(int maxScooters) {
        this.maxScooters = maxScooters;
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "id=" + id +
                ", nif=" + nif +
                ", name='" + name + '\'' +
                ", idAdmin=" + idAdmin +
                ", address=" + address +
                '}';
    }


}
