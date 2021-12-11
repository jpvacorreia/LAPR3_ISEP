package lapr.project.data;

import lapr.project.model.Scooter;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScooterDB extends DataHandler {

    public Scooter getScooterByID(int ID) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getScooterByID(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, ID);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            Scooter scooter=null;
            if (rSet.next()) {
                ID = rSet.getInt(1);
                double maxBatteryCapacity = rSet.getDouble(2);
                double stateOfCharge = rSet.getDouble(3);
                double powerOutput = rSet.getDouble(4);
                double weight = rSet.getDouble(5);
                double maxPaylaod = rSet.getDouble(6);
                int pharmacyID = rSet.getInt(7);
                scooter = new Scooter(ID, maxBatteryCapacity, stateOfCharge,
                        powerOutput, weight, maxPaylaod, pharmacyID);
            }
            callStmt.close();
            if (scooter != null){
                return scooter;
            }
            throw new SQLException();
        } catch (SQLException e) {
            throw new IllegalArgumentException("No scooter with given ID " + ID);
        }
    }


    public boolean addScooter(Scooter s) {
        return addScooter(s.getId(), s.getMaxBatteryCapacity(), s.getStateOfCharge(), s.getPowerOutput(), s.getWeight(), s.getMaxPayload(), s.getPharmacyId());
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Adiciona o Scooter especificado à tabela "Scooter".
     *
     * @param id                 Scooter identification number.
     * @param maxBatteryCapacity Scooter maximum Battery capacity.
     * @param stateOfCharge      Scooter state of charge.
     * @param powerOutput        Scooter power output.
     * @param weight             Scooter gross weight.
     * @param maxPayload         Scooter maximum payload.
     * @param pharmacyID         Scooter pharmacy identification number.
     */
    private boolean addScooter(int id, double maxBatteryCapacity, double stateOfCharge, double powerOutput, double weight, double maxPayload, int pharmacyID) {
        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call addScooter(?,?,?,?,?,?,?) }");
            callStmt.setInt(1, id);
            callStmt.setDouble(2, maxBatteryCapacity);
            callStmt.setDouble(3, stateOfCharge);
            callStmt.setDouble(4, powerOutput);
            callStmt.setDouble(5, weight);
            callStmt.setDouble(6, maxPayload);
            callStmt.setInt(7, pharmacyID);
            callStmt.execute();
            callStmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Cannot add Scooter, please check input data!");
            //e.printStackTrace();
            return false;
        }
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Remove o Scooter especificado da tabela "Scooter".
     *
     * @param ID Scooter identification number.
     */
    public boolean removeScooter(int ID) {

        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call removeScooter(?) }");

            callStmt.setLong(1, ID);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates a Scooter on the "Scooter" table.
     *
     * @param s Scooter instance.
     */
    public boolean updateScooter(Scooter s) {
        return updateScooter(s.getId(), s.getMaxBatteryCapacity(), s.getStateOfCharge(), s.getPowerOutput(), s.getWeight(), s.getMaxPayload(), s.getPharmacyId());
    }

    private boolean updateScooter(int id, double maxBatteryCapacity, double stateOfCharge, double powerOutput, double weight, double maxPayload, int pharmacyID) {
        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call updateScooter(?,?,?,?,?,?,?) }");

            callStmt.setInt(1, id);
            callStmt.setDouble(2, maxBatteryCapacity);
            callStmt.setDouble(3, stateOfCharge);
            callStmt.setDouble(4, powerOutput);
            callStmt.setDouble(5, weight);
            callStmt.setDouble(6, maxPayload);
            callStmt.setInt(7, pharmacyID);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

