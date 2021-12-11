package lapr.project.controller;

import lapr.project.model.Pharmacy;
import lapr.project.model.UpdatePharmacy;

public class UpdatePharmacyController {

    protected UpdatePharmacy up;

    public UpdatePharmacyController() {
        up = ApplicationPOT.getInstance().getPlatform().getUpdatePharmacy();
    }

    public boolean removePharmacy(int pharmacyNIF) {
        return up.removePharmacy(pharmacyNIF);
    }

    public Pharmacy getPharmacy(int pharmacyNIF) {
        return UpdatePharmacy.getPharmacyByNif(pharmacyNIF);
    }

    public boolean updatePharmacy(Pharmacy ph) {
        return up.updatePharmacy(ph);
    }

    public boolean addPharmacy(Pharmacy pharmacy) {
        if (up.addPharmacy(pharmacy)) {
            return true;
        }
        //ApplicationPOT.getInstance().getPlatform().getAddressDB().removeAddress(pharmacy.getAddress().getCoordinateX(), pharmacy.getAddress().getCoordinateY());
        return false;
    }
}
