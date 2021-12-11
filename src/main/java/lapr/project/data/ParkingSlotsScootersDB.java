package lapr.project.data;

import lapr.project.model.*;
import lapr.project.model.ParkingSlotsScooters;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ParkingSlotsScootersDB extends DataHandler{

    private ParkingSlotsScooters handleResultSet(CallableStatement callStmt, int parameter) throws SQLException {
        // Regista o tipo de dados SQL para interpretar o resultado obtido.
        callStmt.registerOutParameter(1, OracleTypes.CURSOR);
        callStmt.setInt(2, parameter);
        callStmt.execute();
        // Guarda o cursor retornado num objeto "ResultSet".
        ResultSet rSet = (ResultSet) callStmt.getObject(1);
        if (rSet.next()) {
            char charCharge = (char) rSet.getByte(1);
            int state = rSet.getInt(2);
            int idParkSlot = rSet.getInt(3);
            boolean chargeCapabilities;
            if (charCharge == 1) {
                chargeCapabilities = true;
            } else
                chargeCapabilities = false;
            callStmt.close();

            return new ParkingSlotsScooters(idParkSlot, state, chargeCapabilities);
        }
        callStmt.close();
        return null;
    }



    public ParkingSlotsScooters getParkingSlotsScooters(int parkingSlotID) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getParkingSlotsScooters(?) }");
            ParkingSlotsScooters pss = handleResultSet(callStmt,parkingSlotID);
            if (pss != null){
                return pss;
            }
            throw new SQLException();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No parking slot for scooters with that id");
    }

    public boolean addParkingSlotsScooters(ParkingSlotsScooters parkingSlotsScooters) {
        int hasCC = 0;
        if (parkingSlotsScooters.getHasChargingCapabilities()){
            hasCC = 1;
        }
        return addParkingSlotsScooters(hasCC, parkingSlotsScooters.getStateOfUse(),parkingSlotsScooters.getIdParkingSlot());
    }

    private boolean addParkingSlotsScooters(int hasCC, int state, int parkSlotId) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addParkingSlotsScooters(?,?,?) }");

            callStmt.setInt(1, hasCC);
            callStmt.setInt(2, state);
            callStmt.setInt(3,parkSlotId);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeParkingSlotsScooters(int parkingSlotId) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call removeParkingSlotsScooters(?) }");

            callStmt.setInt(1, parkingSlotId);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateParkingSlotsScooters(ParkingSlotsScooters ps) {
        int cc = 0;
        if (ps.getHasChargingCapabilities()){
            cc = 1;
        }
        return updateParkingSlotsScooters(ps.getIdParkingSlot(), cc, ps.getStateOfUse());
    }

    private boolean updateParkingSlotsScooters(int id, int cc, int state) {
        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call updateParkingSlotsScooters(?,?,?) }");

            callStmt.setInt(1, id);
            callStmt.setInt(2,cc);
            callStmt.setInt(3, state);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
