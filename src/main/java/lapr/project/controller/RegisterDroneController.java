package lapr.project.controller;

import lapr.project.model.Drone;
import lapr.project.model.RegisterDrone;

public class RegisterDroneController {
    RegisterDrone rdrone;
    Drone drone;

    public RegisterDroneController() {
        this.rdrone = ApplicationPOT.getInstance().getPlatform().getRegisterDrone();
    }

    public boolean newDrone(int id, double maxBatteryCapacity, double stateOfCharge, double powerOutput, double weight, double maxPayload, int pharmacyId) {
        try {
            drone = rdrone.newDrone(id, maxBatteryCapacity, stateOfCharge, powerOutput, weight, maxPayload, pharmacyId);
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveDrone() {
        return rdrone.saveDrone(drone);
    }

    public boolean removeDrone() {
        return rdrone.removeDrone(drone);
    }

    public boolean updateDrone() {
        return rdrone.updateDrone(drone);
    }
}
