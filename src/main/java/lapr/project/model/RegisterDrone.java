package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.utils.Constants;
import lapr.project.utils.EnergyCalculations;


public class RegisterDrone {
    Drone drone;

    public RegisterDrone() {
        this.drone = null;
    }

    /**
     * Creates Drone instance in java
     *
     * @param id - Drone's identification number
     * @param maxBatteryCapacity - Drone's maxBatteryCapacity
     * @param stateOfCharge - Drone's stateOfCharge
     * @param powerOutput - Drone's Power Output
     * @param weight - Drone's weight
     * @param maxPayload - Drone's max payload
     * @param pharmacyId - Drone's pharmacy owner id
     * @return new Drone instance
     */
    public Drone newDrone(int id, double maxBatteryCapacity, double stateOfCharge, double powerOutput, double weight, double maxPayload, int pharmacyId) {
        this.drone = new Drone(id, maxBatteryCapacity, stateOfCharge, powerOutput, weight, maxPayload, pharmacyId);
        return drone;
    }

    /**
     * Saves a Drone into the DataBase
     *
     * @param drone - Drone to be saved
     * @return true when no exceptions are lifted
     **/
    public boolean saveDrone(Drone drone) {
        return save(drone);
    }

    /**
     * Deletes a Drone from the DataBase
     *
     * @param drone - Drone to be removed
     * @return true when no exceptions are lifted
     **/
    public boolean removeDrone(Drone drone) {
        return remove(drone);
    }

    /**
     * Updates a Drone from the DataBase
     *
     * @param drone - Drone to be updated
     * @return true when no exceptions are lifted
     **/
    public boolean updateDrone(Drone drone) {
        return update(drone);
    }

    /**
     * Retrieves a Drone from the Database by its identification number
     *
     * @param id - Drone's identification number
     * @return Drone retrieved
     */
    public Drone getDroneByID(int id) {
        Drone returnable;
        try {
            returnable = ApplicationPOT.getInstance().getPlatform().getDroneDB().getDroneById(id);
            if (returnable != null) {
                return returnable;
            }
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException ex) {
            throw ex;
        }
    }

    /**
     * Adds a Drone into the DataBase
     *
     * @param drone - Drone to be saved
     * @return true when no exceptions are lifted
     **/
    private boolean addDrone(Drone drone) {
        return ApplicationPOT.getInstance().getPlatform().getDroneDB().addDrone(drone);
    }

    /**
     * Saves a Drone into the DataBase
     *
     * @param drone - Drone to be saved
     * @return true when no exceptions are lifted
     **/
    private boolean save(Drone drone) {
        try {
            getDroneByID(drone.getID());
        } catch (IllegalArgumentException ex) {
            return addDrone(drone);
        }
        return false;
    }
    
    /**
     * Deletes a Drone from the DataBase
     *
     * @param drone - Drone to be removed
     * @return true when no exceptions are lifted
     **/
    private boolean remove(Drone drone) {
        try {
            getDroneByID(drone.getID());
            return ApplicationPOT.getInstance().getPlatform().getDroneDB().removeDrone(drone.getID());
        } catch (IllegalArgumentException ex) {
            System.out.println("Drone does not exist.");
        }
        return false;
    }

    /**
     * Updates a Drone from the DataBase
     *
     * @param drone - Drone to be updated
     * @return true when no exceptions are lifted
     **/
    private boolean update(Drone drone) {
        try {
            getDroneByID(drone.getID());
            return ApplicationPOT.getInstance().getPlatform().getDroneDB().updateDrone(drone);
        } catch (IllegalArgumentException ex) {
            System.out.println("Drone does not exist.");
        }
        return false;
    }

    /**
     * Calculates the energy wasted between two addresses in the route
     * @param distanceE - double representing the path
     * @param weight - Length
     * @param windDirection - Direction of the wind
     * @return a double with the calculated energy
     */
    public static double energyWastedBetweenTwoPointsD(double distanceE, double weight, int windDirection) {
        double distance = (distanceE * 1000);
        double angle = Math.toRadians(windDirection);
        double energy1 = ((weight * Constants.GRAVITATIONAL_ACCELERATION * distance) / (Constants.LIFT_RATIO * Constants.DRONE_EFFICIENCY));
        double energy2 = ((Constants.DRONE_PAVIO * distance) / (Constants.DRONE_HORIZONTAL_VELOCITY * Constants.WIND_VELOCITY * Math.cos(angle)));
        double totalEnergy = energy1 + energy2;
        double finalEnergy = EnergyCalculations.convertJoulesToWH(totalEnergy);
        double energyUpandDown = EnergyCalculations.energyWastedBetweenTwoPointsYD(weight);
        finalEnergy += energyUpandDown;
        return finalEnergy;
    }
}