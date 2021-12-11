package lapr.project.controller;

import lapr.project.model.RegisterScooter;
import lapr.project.model.Scooter;

public class RegisterScooterController {
    RegisterScooter rscoot;
    Scooter scoot;

    public RegisterScooterController() {
        this.rscoot = ApplicationPOT.getInstance().getPlatform().getRegisterScooter();
    }

    public RegisterScooter getRscoot() {
        return rscoot;
    }

    public boolean newScooter(int id, double maxBatteryCapacity, double stateOfCharge, double powerOutput, double weight, double maxPayload, int pharmacyId) {
        try {
            scoot = rscoot.newScooter(id, maxBatteryCapacity, stateOfCharge, powerOutput, weight, maxPayload, pharmacyId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveScooter() {
        return rscoot.saveScooter(scoot);
    }

    public boolean removeScooter() {
        return rscoot.removeScooter(scoot);
    }

    public boolean updateScooter() {
        return rscoot.updateScooter(scoot);
    }
}
