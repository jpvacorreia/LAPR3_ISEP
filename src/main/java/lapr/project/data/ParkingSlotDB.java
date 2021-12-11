package lapr.project.data;

import lapr.project.model.Courier;
import lapr.project.model.ParkingSlot;
import lapr.project.model.ParkingSlotScootersUsage;
import lapr.project.model.ParkingSlotsScooters;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ParkingSlotDB extends DataHandler {

    private ParkingSlot handleResultSet(CallableStatement callStmt, int parameter) throws SQLException {
        // Regista o tipo de dados SQL para interpretar o resultado obtido.
        callStmt.registerOutParameter(1, OracleTypes.CURSOR);
        callStmt.setInt(2, parameter);
        callStmt.execute();
        // Guarda o cursor retornado num objeto "ResultSet".
        ResultSet rSet = (ResultSet) callStmt.getObject(1);
        if (rSet.next()) {

            int id = rSet.getInt(1);
            int pharmacyId = rSet.getInt(2);
            callStmt.close();

            return  new ParkingSlot(id, pharmacyId);
        }
        return null;
    }

    public ParkingSlot getVacantChargeableScooterParkingSlot(int pharmacyID) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getVacantChargeableScooterParkingSlot(?) }");
            return handleResultSet(callStmt,pharmacyID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No parking slot for scooters with that id");
    }

    public ArrayList<ParkingSlot> getParkingSlotsScootersWithCCInUse(int idPharmacy,Date date) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getParkingSlotsScootersWithCCInUse(?,?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, idPharmacy);
            callStmt.setTimestamp(3,(java.sql.Timestamp) date);
            callStmt.execute();
            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            int size = rSet.getFetchSize();
            ArrayList<ParkingSlot> arrayList = new ArrayList<>();
            for(int i=0; i<size; i++) {
                if (rSet.next()) {

                    int id = rSet.getInt(1);
                    int pharmacyId = rSet.getInt(2);

                    arrayList.add(new ParkingSlot(id, pharmacyId));
                }
            }
            callStmt.close();

            return arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Parking slots in use");
    }

    public ParkingSlot getParkingSlotFromPharmacy(int parkingSlotID) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getParkingSlotFromPharmacy(?) }");
            return handleResultSet(callStmt,parkingSlotID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No parking slot for scooters with that id");
    }

    public boolean addParkingSlot(ParkingSlot parkingSlot) {

        return addParkingSlot(parkingSlot.getPharmacyId());
    }

    private boolean addParkingSlot(int phId) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addParkingSlot(?) }");

            callStmt.setInt(1, phId);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    }


