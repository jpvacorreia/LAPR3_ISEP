package lapr.project.model;


import lapr.project.controller.ApplicationPOT;
import lapr.project.utils.Constants;
import lapr.project.utils.Edge;
import lapr.project.utils.Vertex;
import lapr.project.utils.EnergyCalculations;

public class RegisterScooter {
    Scooter scoot;

    /**
     * Creates Scooter instance in java
     *
     * @param id - Scooter's identification number
     * @param maxBatteryCapacity - Scooter's maxBatteryCapacity
     * @param stateOfCharge - Scooter's stateOfCharge
     * @param powerOutput - Scooter's Power Output
     * @param weight - Scooter's weight
     * @param maxPayload - Scooter's max payload
     * @param pharmacyId - Scooter's pharmacy owner id
     * @return new Scooter instance
     */
    public Scooter newScooter(int id, double maxBatteryCapacity, double stateOfCharge, double powerOutput, double weight, double maxPayload, int pharmacyId) {
        this.scoot = new Scooter(id, maxBatteryCapacity, stateOfCharge, powerOutput, weight, maxPayload, pharmacyId);
        return scoot;
    }


    /**
     * Saves a Scooter into the DataBase
     *
     * @param scoot - Scooter to be saved
     * @return true when no exceptions are lifted
     **/
    public boolean saveScooter(Scooter scoot) {
        return save(scoot);
    }

    /**
     * Deletes a Scooter from the DataBase
     *
     * @param scoot - Scooter to be removed
     * @return true when no exceptions are lifted
     **/
    public boolean removeScooter(Scooter scoot) {
        return delete(scoot);
    }

    /**
     * Updates a Scooter from the DataBase
     *
     * @param scoot - Scooter to be updated
     * @return true when no exceptions are lifted
     **/
    public boolean updateScooter(Scooter scoot) {
        return update(scoot);
    }

    /**
     * Sends an email to the Courier when the scooter is done charging
     *
     * @param scooterId - Scooter's identification number
     * @return true when the email goes through
     */
    public boolean scooterDoneCharging(int scooterId) {
        Scooter sc = getScooterByID(scooterId);
        CourierScooter cs = CourierScooter.getCourierScooterByScooterId(scooterId);
        int courierId = cs.getIdCourier();
        Courier cour = ApplicationPOT.getInstance().getPlatform().getCourierDB().getCourierById(courierId);
        String email = cour.getEmail();
        ParkingSlotScootersUsage pssu = ApplicationPOT.getInstance().getPlatform().getParkingSlotScootersUsageDB().getParkingSlotScootersLastUsageByScooterId(scooterId);
        int parkingSlotId = pssu.getIdParkingSlot();
        ParkingSlotsScooters pss = ApplicationPOT.getInstance().getPlatform().getParkingSlotsScootersDB().getParkingSlotsScooters(parkingSlotId);
        pss.setStateOfUse(1);
        ApplicationPOT.getInstance().getPlatform().getParkingSlotsScootersDB().updateParkingSlotsScooters(pss);
        String subject = "Scooter done charging!";
        String body = "Hello! Your Scooter is now done charging and ready for another delivery run!";
        if (sc.getStateOfCharge() == Constants.SCOOTER_FULLY_CHARGED) {
            return ApplicationPOT.getInstance().getPlatform().getSendEmail().sendEmail(email, subject, body);
        }
        return false;
    }

    /**
     * Retrieves a Scooter from the Database by its identification number
     *
     * @param id - Scooter's identification number
     * @return Scooter retrieved
     */
    public static Scooter getScooterByID(int id) {
        Scooter returnable = null;
        try {
            returnable = ApplicationPOT.getInstance().getPlatform().getScooterDB().getScooterByID(id);
            if (returnable != null) {
                return returnable;
            }
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException ex) {
            throw ex;
        }
    }

    /**
     * Adds a Scooter into the DataBase
     *
     * @param scoot - Scooter to be saved
     * @return true when no exceptions are lifted
     **/
    private boolean addScooter(Scooter scoot) {
        return ApplicationPOT.getInstance().getPlatform().getScooterDB().addScooter(scoot);
    }

    /**
     * Saves a Scooter into the DataBase
     *
     * @param scoot - Scooter to be saved
     * @return true when no exceptions are lifted
     **/
    private boolean save(Scooter scoot) {
        try {
            getScooterByID(scoot.getId());
        } catch (IllegalArgumentException ex) {
            return addScooter(scoot);
        }
        return false;
    }


    /**
     * Deletes a Scooter from the DataBase
     *
     * @param scoot - Scooter to be removed
     * @return true when no exceptions are lifted
     **/
    private boolean delete(Scooter scoot) {
        try {
            getScooterByID(scoot.getId());
            return ApplicationPOT.getInstance().getPlatform().getScooterDB().removeScooter(scoot.getId());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /**
     * Updates a Scooter from the DataBase
     *
     * @param scoot - Scooter to be updated
     * @return true when no exceptions are lifted
     **/
    private boolean update(Scooter scoot) {
        try {
            getScooterByID(scoot.getId());
            return ApplicationPOT.getInstance().getPlatform().getScooterDB().updateScooter(scoot);
        } catch (IllegalArgumentException ex) {
            System.out.println("Scooter does not exist.");
        }
        return false;
    }

    /**
     * Calculates the energy wasted between two addresses in the route
     * @param v1 - Vertex orig
     * @param v2 - Vertex dest
     * @param e1 - Edge representing the path
     * @param weight - Length
     * @param windDirection - Direction of the wind
     * @return a double with the calculated energy
     */
    public static double energyWastedBetweenTwoPoints(Vertex v1, Vertex v2, Edge e1, double weight, int windDirection) {
        double distance = e1.getWeight() * 1000;
        double angle = Math.toRadians(windDirection);
        double roadSlopeAngle = Math.atan((v2.getAltitude() - v1.getAltitude()) / distance);
        double totalRoadLoad = EnergyCalculations.getTotalRoadLoadS(weight, roadSlopeAngle, angle);
        double totalPower = EnergyCalculations.getTotalPowerS(totalRoadLoad, angle);
        double timeSpent = EnergyCalculations.getTimeSpentS(distance);
        double energyWasted = totalPower * timeSpent;
        double energyWastedInWH = EnergyCalculations.convertJoulesToWH(energyWasted);
        return energyWastedInWH;
    }

}