package lapr.project.data;

import lapr.project.model.ParkingSlotDrones;
import lapr.project.model.ParkingSlotsScooters;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class ParkingSlotDronesDB extends DataHandler{

    public boolean addParkingSlotDrones(ParkingSlotDrones parkingSlotDrones) {
        int hasCC = 0;
        if (parkingSlotDrones.getHasChargingCapabilities()){
            hasCC = 1;
        }
        return addParkingSlotDrones(hasCC, parkingSlotDrones.getStateOfUse(),parkingSlotDrones.getIdParkingSlot());
    }

    private boolean addParkingSlotDrones(int hasCC, int state, int parkSlotId) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addParkingSlotDrones(?,?,?) }");

            callStmt.setInt(1, hasCC);
            callStmt.setInt(2, state);
            callStmt.setInt(3, parkSlotId);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
