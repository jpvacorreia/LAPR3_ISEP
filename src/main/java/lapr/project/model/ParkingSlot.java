package lapr.project.model;


public class ParkingSlot {

    private int id;
    private final int pharmacyId;

    public ParkingSlot(int id, int pharmacyId) {
        this.id = id;
        this.pharmacyId = pharmacyId;
    }
    public ParkingSlot(int pharmacyId){
        this.pharmacyId = pharmacyId;
    }

    public int getId() {
        return id;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

}
