package lapr.project.model;

public class ParkingSlotsScooters {
    int idParkingSlot;
    int stateOfUse;
    boolean hasChargingCapabilities;

    public ParkingSlotsScooters(int idParkingSlot, int stateOfUse, boolean hasChargingCapabilities){
        this.idParkingSlot = idParkingSlot;
        this.stateOfUse = stateOfUse;
        this.hasChargingCapabilities = hasChargingCapabilities;
    }

    public int getIdParkingSlot() {
        return idParkingSlot;
    }

    public boolean getHasChargingCapabilities() {
        return hasChargingCapabilities;
    }

    public int getStateOfUse() {
        return stateOfUse;
    }

    public void setIdParkingSlot(int idParkingSlot) {
        this.idParkingSlot = idParkingSlot;
    }

    public void setHasChargingCapabilities(boolean hasChargingCapabilities) {
        this.hasChargingCapabilities = hasChargingCapabilities;
    }

    public void setStateOfUse(int stateOfUse) {
        this.stateOfUse = stateOfUse;
    }
}
