package lapr.project.model;


import lapr.project.controller.ApplicationPOT;

public class RegisterCourier{

    /**
     * Creates an object of Courier from the data passed as parameter
     *
     * @param name          Courier's name
     * @param niss          Courier's NISS
     * @param nif           Courier's NIF
     * @param weight        Courier's weight
     * @param scooterId     Courier's prefered scooter identification number
     * @param pharmacyId    Pharmacy identification number to which the Courier works for
     * @param email         Courier's email
     *
     * @return Object of Courier class
     */
    public Courier newCourier(String name, double niss, int nif, double weight, int scooterId, int pharmacyId, String email) {
        return new Courier(name, niss, nif, weight, scooterId, pharmacyId, email);
    }


    /**
     * Registers a new Courier into the Data Base
     *
     * @param cour      Object of Courier class
     * @return True     True if the Courier is successfully added into the Data Base
     */
    public boolean registerCourier(Courier cour) {
        return save(cour);
    }


    public static Courier getCourier(int courierNIF) {
        return ApplicationPOT.getInstance().getPlatform().getCourierDB().getCourier(courierNIF);
    }

    /**
     * Saves a Courier passed as parameter into the DataBase
     *
     * @param cour      Object of Courier class
     * @return True     True if Courier is successfully added to the Data Base
     **/
    private boolean save(Courier cour) {
        try {
            getCourier(cour.getNif());
        } catch (IllegalArgumentException ex) {
            //Of the record does not exist, save it
            return ApplicationPOT.getInstance().getPlatform().getCourierDB().addCourier(cour);
        }
        return false;
    }

    /**
     * Removes a Courier passed as parameter from the DataBase
     *
     * @param cour      Object of Courier class
     * @return True     True if Courier is successfully removed from the Data Base
     **/
    public boolean delete(Courier cour){
        try {
            getCourier(cour.getNif());
            return ApplicationPOT.getInstance().getPlatform().getCourierDB().removeCourier(cour.getNif());
        } catch (IllegalArgumentException ex){
            System.out.println("Courier does not exist in the database");
        }
        return false;
    }

    /**
     * Retrieves a Courier NIF from the Data Base using their email as identifier.
     *
     * @param courierEmail          Courier's email
     * @return id                   Courier's NIF
     */
    public int getCourierNIFByEmail(String courierEmail){
        return ApplicationPOT.getInstance().getPlatform().getCourierDB().getCourierNIFByEmail(courierEmail);
    }

}
