package lapr.project.data;

import lapr.project.model.Drone;
import lapr.project.model.Scooter;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DroneDB extends DataHandler {

    public ArrayList<Drone> getAllDrones() {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllDrones() }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            int size = rSet.getFetchSize();
            ArrayList<Drone> drones = new ArrayList<>();
            while (rSet.next()) {
                int id = rSet.getInt(1);
                double maxBatteryCapacity = rSet.getDouble(2);
                double maxPaylaod = rSet.getDouble(3);
                double stateOfCharge = rSet.getDouble(4);
                double powerOutput = rSet.getDouble(5);
                double weight = rSet.getDouble(6);
                int pharmacyID = rSet.getInt(7);
                Drone drone = new Drone(id, maxBatteryCapacity, stateOfCharge,
                        powerOutput, weight, maxPaylaod, pharmacyID);
                drones.add(drone);
            }
            callStmt.close();
            if (drones.size() > 0) {
                return drones;
            }
            throw new SQLException();
        } catch (SQLException e) {
            throw new IllegalArgumentException("No Drones in Database");
        }
    }

    public Drone getDroneById(int ID) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getDroneById(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, ID);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            Drone drone = null;

            if (rSet.next()) {
                ID = rSet.getInt(1);
                double maxBatteryCapacity = rSet.getDouble(2);
                double maxPaylaod = rSet.getDouble(3);
                double stateOfCharge = rSet.getDouble(4);
                double powerOutput = rSet.getDouble(5);
                double weight = rSet.getDouble(6);
                int pharmacyID = rSet.getInt(7);
                drone = new Drone(ID, maxBatteryCapacity, stateOfCharge,
                        powerOutput, weight, maxPaylaod, pharmacyID);
                callStmt.close();
            }

            if (drone != null) {
                return drone;
            }
            throw new SQLException();
        } catch (SQLException e) {
        }
        throw new IllegalArgumentException("No Drone with given ID " + ID);
    }


    public boolean addDrone(Drone s) {
        return addDrone(s.getID(), s.getMaxBatteryCapacity(), s.getStateOfCharge(), s.getPowerOutput(), s.getWeight(), s.getMaxPayload(), s.getPharmacyID());
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Adiciona o Drone especificado à tabela "Drone".
     *
     * @param id                 Drone identification number.
     * @param maxBatteryCapacity Drone maximum Battery capacity.
     * @param stateOfCharge      Drone state of charge.
     * @param powerOutput        Drone power output.
     * @param weight             Drone gross weight.
     * @param maxPayload         Drone maximum payload.
     * @param pharmacyID         Drone pharmacy identification number.
     */
    private boolean addDrone(int id, double maxBatteryCapacity, double stateOfCharge, double powerOutput, double weight, double maxPayload, int pharmacyID) {
        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call addDrone(?,?,?,?,?,?,?) }");

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
            System.out.println("Cannot add Drone, please check input data!");
            //e.printStackTrace();
            return false;
        }
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Remove o Drone especificado da tabela "Drone".
     *
     * @param ID Drone identification number.
     */
    public boolean removeDrone(int ID) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call removeDrone(?) }");

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
     * @param d Scooter instance.
     */
    public boolean updateDrone(Drone d) {
        return updateDrone(d.getID(), d.getMaxBatteryCapacity(), d.getStateOfCharge(), d.getPowerOutput(), d.getWeight(), d.getMaxPayload(), d.getPharmacyID());
    }

    private boolean updateDrone(int id, double maxBatteryCapacity, double stateOfCharge, double powerOutput, double weight, double maxPayload, int pharmacyID) {
        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call updateDrone(?,?,?,?,?,?,?) }");

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

