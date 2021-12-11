package lapr.project.model;

public class Address {
    String street;
    double coordinateX;
    double coordinateY;
    PostalCode postalCode;
    private double elevation;

    public Address (String address, String postalCode, double coordinateX, double coordinateY) {
        this.street = address;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.postalCode = new PostalCode(postalCode);
        this.elevation = 0;
    }

    public Address (String address, String postalCode, double coordinateX, double coordinateY, double elevation) {
        this.street = address;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.postalCode = new PostalCode(postalCode);
        this.elevation = elevation;
    }

    public String getStreet() {
        return street;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY +
                ", postalCode=" + postalCode +
                '}';
    }
}
