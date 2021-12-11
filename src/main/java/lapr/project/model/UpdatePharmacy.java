package lapr.project.model;


import lapr.project.controller.ApplicationPOT;

public class UpdatePharmacy {

    /**
     * Deletes this Pharmacy from the DataBase
     *
     * @param pharmacyNIF - Pharmacy's nif to delete
     * @return boolean - True when it deletes, false when an Exception is caught
     **/
    public boolean removePharmacy(int pharmacyNIF) {
        return delete(pharmacyNIF);
    }

    /**
     * Saves a Pharmacy into the DataBase
     *
     * @param pharmacy - Pharmacy to save
     * @return boolean - True when it adds, false when an Exception is caught
     **/
    public boolean addPharmacy(Pharmacy pharmacy) {
        return save(pharmacy);
    }

    /**
     * Updates a pharmacy existing in the database
     *
     * @param pharmacy - Pharmacy to update
     * @return boolean - True when it updates, false when an Exception is thrown
     */
    public boolean updatePharmacy(Pharmacy pharmacy){ return update(pharmacy);}

    /**
     * Retrieves a Pharmacy from the DataBase
     *
     * @param nif Pharmacy NIF number.
     */
    public static Pharmacy getPharmacyByNif(int nif) {
        try {
            Pharmacy returnable = ApplicationPOT.getInstance().getPlatform().getPharmacyDB().getPharmacyByNif(nif);
            if (returnable != null) {
                return returnable;
            }
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException ex) {
            throw ex;
        }

    }

    /**
     * Retrieves a Pharmacy from the DataBase
     *
     * @param id Pharmacy identification number.
     */
    public static Pharmacy getPharmacyByID(int id) {
        try {
            Pharmacy returnable = ApplicationPOT.getInstance().getPlatform().getPharmacyDB().getPharmacyByID(id);
            if (returnable != null) {
                return returnable;
            }
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException ex) {
            throw ex;
        }

    }

    /**
     * Saves a Pharmacy into the DataBase
     *
     * @param ph - Pharmacy to save
     * @return boolean - True when it adds, false when an Exception is caught
     **/
    private boolean save(Pharmacy ph) {
        try {
            getPharmacyByNif(ph.getNif());
        } catch (IllegalArgumentException ex) {
            //Of the record does not exist, save it
            return ApplicationPOT.getInstance().getPlatform().getPharmacyDB().addPharmacy(ph);
        }
        return false;
    }

    /**
     * Updates a pharmacy existing in the database
     *
     * @param ph - Pharmacy to update
     * @return boolean - True when it updates, false when an Exception is thrown
     */
    private boolean update (Pharmacy ph){
        return ApplicationPOT.getInstance().getPlatform().getPharmacyDB().updatePharmacy(ph);
    }

    /**
     * Deletes this Pharmacy from the DataBase
     *
     * @param nif - Pharmacy's nif to delete
     * @return boolean - True when it deletes, false when an Exception is caught
     **/
    private boolean delete(int nif) {
        try {
            getPharmacyByNif(nif);
            return ApplicationPOT.getInstance().getPlatform().getPharmacyDB().removePharmacy(nif);
        } catch (IllegalArgumentException ex) {
            System.out.println("Pharmacy does not exist.");
        }
        return false;
    }
}
