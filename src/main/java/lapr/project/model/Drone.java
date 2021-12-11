package lapr.project.model;


public class Drone {
    private final int ID;
    private final double maxBatteryCapacity;
    private double stateOfCharge;
    private final double powerOutput;
    private final double weight;
    private final double maxPayload;
    private final int pharmacyID;
    private boolean locked;

    public Drone(int ID, double maxBatteryCapacity, double stateOfCharge, double powerOutput, double weight, double maxPayload, int pharmacyID) {
        this.ID = ID;
        this.maxBatteryCapacity = maxBatteryCapacity;
        this.stateOfCharge = stateOfCharge;
        this.powerOutput = powerOutput;
        this.weight = weight;
        this.maxPayload = maxPayload;
        this.pharmacyID = pharmacyID;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getID() {
        return ID;
    }

    public double getMaxBatteryCapacity() {
        return maxBatteryCapacity;
    }

    public double getStateOfCharge() {
        return stateOfCharge;
    }

    public double getPowerOutput() {
        return powerOutput;
    }

    public double getWeight() {
        return weight;
    }

    public double getMaxPayload() {
        return maxPayload;
    }

    public int getPharmacyID() {
        return pharmacyID;
    }

    public void setStateOfCharge(double stateOfCharge) {
        this.stateOfCharge = stateOfCharge;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "ID=" + ID +
                ", maxBatteryCapacity=" + maxBatteryCapacity +
                ", stateOfCharge=" + stateOfCharge +
                ", powerOutput=" + powerOutput +
                ", weight=" + weight +
                ", maxPayload=" + maxPayload +
                ", pharmacyID=" + pharmacyID +
                ", locked=" + locked +
                '}';
    }
}
