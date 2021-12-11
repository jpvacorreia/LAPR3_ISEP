package lapr.project.data;

import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.Courier;
import lapr.project.model.CreditCard;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourierDB extends DataHandler {
    
    
    /**
     * Exemplo de invocação de uma "stored function".
     * <p>
     * Devolve o registo do courier especificado existente na tabela
     * "Courier".
     *
     * @param courierNIF o NIF do Courier.
     * @return o registo do id especificado ou null, se esse registo não
     * existir.
     */
    public Courier getCourier(int courierNIF) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getCourier(?) }");
            Courier cour = handleResultSet(callStmt,courierNIF);
            if (cour != null){
                return cour;
            }
            throw new SQLException();
        } catch (SQLException e) {
            throw new IllegalArgumentException("No Courier with ID:" + courierNIF);
        }
    }
    
    /**
     * Exemplo de invocação de uma "stored function".
     * <p>
     * Devolve o registo do courier especificado existente na tabela
     * "Courier".
     *
     * @param courierId o id do Courier.
     * @return o registo do id especificado ou null, se esse registo não
     * existir.
     */
    public Courier getCourierById (int courierId) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getCourierById(?) }");
            Courier courier = handleResultSet(callStmt,courierId);
            if (courier != null)
                return courier;
            throw new SQLException();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Courier with ID:" + courierId);
    }

    public boolean addCourier(Courier courier) {

        return addCourier(courier.getName(), courier.getNiss(), courier.getNif(), courier.getWeight(), courier.getScooterId(), courier.getPharmacyId(), courier.getEmail());

    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Adiciona o courier especificado à tabela "Courier".
     *
     * @param name       courier's name.
     * @param niss       courier's niss.
     * @param nif        courier's nif.
     * @param weight     courier's weight.
     * @param scooterId  courier's scooterId.
     * @param pharmacyId courier's pharmacyId.
     * @param email      courier's email.
     */

    private boolean addCourier(String name, double niss, int nif, double weight, int scooterId, int pharmacyId, String email) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addCourier(?,?,?,?,?,?,?) }");

            callStmt.setString(1, name);
            callStmt.setDouble(2, niss);
            callStmt.setInt(3, nif);
            callStmt.setDouble(4, weight);
            callStmt.setInt(5, scooterId);
            callStmt.setInt(6, pharmacyId);
            callStmt.setString(7, email);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Cannot register Courier, please check input data");
            //e.printStackTrace();
        }
        return false;
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Remove o courier especificado da tabela "Courier".
     *
     * @param courierNIF o NIF Courier.
     */
    public boolean removeCourier(int courierNIF) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call removeCourier(?) }");

            callStmt.setLong(1, courierNIF);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getCourierNIFByEmail(String courierEmail) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getCourierNIFByEmail(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setString(2, courierEmail);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            int rSet = (Integer) callStmt.getObject(1);

            return rSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Courier with email:" + courierEmail);
    }

    private Courier handleResultSet(CallableStatement callStmt, int parameter) throws SQLException {
        // Regista o tipo de dados SQL para interpretar o resultado obtido.
        callStmt.registerOutParameter(1, OracleTypes.CURSOR);
        callStmt.setInt(2, parameter);

        callStmt.execute();

        // Guarda o cursor retornado num objeto "ResultSet".
        ResultSet rSet = (ResultSet) callStmt.getObject(1);
        Courier cour = null;
        if (rSet.next()) {
            int id = rSet.getInt(1);
            String courierName = rSet.getString(2);
            double courierNiss = rSet.getDouble(3);
            int courierNif = rSet.getInt(4);
            double courierWeight = rSet.getDouble(5);
            int courierScooterId = rSet.getInt(6);
            int courierPharmacyId = rSet.getInt(7);
            String courierEmail = rSet.getString(8);

            cour = new Courier(id,courierName, courierNiss, courierNif, courierWeight, courierScooterId, courierPharmacyId, courierEmail);
            callStmt.close();
        }
        callStmt.close();
        return cour;
    }
}