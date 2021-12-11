package lapr.project.data;

import lapr.project.model.ParkingSlotScootersUsage;
import lapr.project.model.ParkingSlotScootersUsage;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ParkingSlotScootersUsageDB extends DataHandler {

    public ParkingSlotScootersUsage getParkingSlotScootersUsage(int parkingSlotID,int scooterID, Date dateIN) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getParkingSlotScootersUsage(?,?,?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, parkingSlotID);
            callStmt.setInt(3, scooterID);
            callStmt.setTimestamp(4,(java.sql.Timestamp) dateIN);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                
                Date dateIn = rSet.getTimestamp(1);
                Date dateOut = rSet.getTimestamp(2);
                int scooterid = rSet.getInt(3);
                int psID = rSet.getInt(4);
                callStmt.close();

                return new ParkingSlotScootersUsage(psID,scooterid,dateIn,dateOut);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No ParkingSlotScooterUsage with those values");
    }

    public ParkingSlotScootersUsage getParkingSlotScootersLastUsage(int idPark){
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getParkingSlotScootersLastUsage(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, idPark);


            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                Date dateIn = rSet.getTimestamp(1);
                Date dateOut = rSet.getTimestamp(2);
                int scooterid = rSet.getInt(3);
                int psID = rSet.getInt(4);
                callStmt.close();

                return new ParkingSlotScootersUsage(psID,scooterid,dateIn,dateOut);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("ParkingSlot never used");
    }
    
    public boolean addParkingSlotScootersUsage(ParkingSlotScootersUsage parkingSlotScootersUsage) {

        return addParkingSlotScootersUsage(parkingSlotScootersUsage.getIdParkingSlot(), parkingSlotScootersUsage.getIdScooter(), parkingSlotScootersUsage.getDateIN(), parkingSlotScootersUsage.getDateOUT());
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Adiciona o ParkingSlotScootersUsage especificado à tabela "ParkingSlotScootersUsage".
     *
     * @param parkSlotId ParkingSlotScootersUsage's parkingSlotId.
     * @param scooterId  ParkingSlotScootersUsage's scooterId.
     * @param dateIN     ParkingSlotScootersUsage's dateIn.
     * @param dateOUT    ParkingSlotScootersUsage's dateOut.
     */

    private boolean addParkingSlotScootersUsage(int parkSlotId, int scooterId, Date dateIN, Date dateOUT) {

        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call addParkingSlotScootersUsage(?,?,?,?) }");
            java.sql.Timestamp sDateIN = new java.sql.Timestamp(dateIN.getTime());
            java.sql.Timestamp sDateOUT = new java.sql.Timestamp(dateOUT.getTime());
            callStmt.setInt(1, parkSlotId);
            callStmt.setInt(2, scooterId);
            callStmt.setTimestamp(3,sDateIN);
            callStmt.setTimestamp(4,sDateOUT);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Remove o ParkingSlotScootersUsage especificado da tabela "ParkingSlotScootersUsage".
     *
     * @param parkingSlotId o Id ParkingSlot.
     * @param scooterId o Id Scooter
     * @param dateIN a data de entrada
     */
    public boolean removeParkingSlotScootersUsage(int parkingSlotId, int scooterId, Date dateIN) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call removeParkingSlotScootersUsage(?,?,?) }");

            callStmt.setInt(1, parkingSlotId);
            callStmt.setInt(2, scooterId);
            callStmt.setTimestamp(3,(java.sql.Timestamp) dateIN);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ParkingSlotScootersUsage getParkingSlotScootersLastUsageByScooterId(int scooterId) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getParkingSlotScootersLastUsage(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, scooterId);


            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                Date dateIn = rSet.getTimestamp(1);
                Date dateOut = rSet.getTimestamp(2);
                int scooterid = rSet.getInt(3);
                int psID = rSet.getInt(4);
                callStmt.close();

                return new ParkingSlotScootersUsage(psID,scooterid,dateIn,dateOut);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("ParkingSlot never used");
    }



}
