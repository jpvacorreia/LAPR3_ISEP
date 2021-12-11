package lapr.project.model;

public class Scooter {
    private int id;
    private double maxBatteryCapacity;
    private double stateOfCharge;
    private double powerOutput;
    private double weight;
    private double maxPayload;
    private int pharmacyId;
    private boolean locked;

    public Scooter(int id, double maxBatteryCapacity, double stateOfCharge, double powerOutput, double weight, double maxPayload, int pharmacyId) {
        this.id = id;
        this.maxBatteryCapacity = maxBatteryCapacity;
        this.stateOfCharge = stateOfCharge;
        this.powerOutput = powerOutput;
        this.weight = weight;
        this.maxPayload = maxPayload;
        this.pharmacyId = pharmacyId;
        this.locked = false;
    }

    public void setStateOfCharge(double stateOfCharge) {
        this.stateOfCharge = stateOfCharge;
    }

    public void setLocked(boolean locked){ this.locked = locked;}

    /**
     * Checks if scooter is locked
     *
     * @return true when lock, false when unlocked
     */
    public boolean isLocked() { return this.getLocked();}

    public boolean getLocked() { return this.locked; }

    public double getMaxPayload() {
        return maxPayload;
    }

    public int getId() {
        return id;
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

    public int getPharmacyId() {
        return pharmacyId;
    }
    
    @Override
    public String toString() {
        return "Scooter{" +
                "ID=" + id +
                ", maxBatteryCapacity=" + maxBatteryCapacity +
                ", stateOfCharge=" + stateOfCharge +
                ", powerOutput=" + powerOutput +
                ", weight=" + weight +
                ", maxPayload=" + maxPayload +
                ", pharmacyID=" + pharmacyId +
                ", locked=" + locked +
                '}';
            //return new ScooterDB().removeScooter(this.getID());
    }
    
}
