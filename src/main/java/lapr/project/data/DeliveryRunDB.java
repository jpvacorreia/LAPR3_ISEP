package lapr.project.data;

import lapr.project.model.DeliveryRun;
import lapr.project.model.Order;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DeliveryRunDB extends DataHandler {

    public boolean addDeliveryRun(DeliveryRun dl) {
        java.sql.Timestamp sqlStartDate = new java.sql.Timestamp(dl.getDate().getTime());
        addDeliveryRun(dl.getiDDeliverer(), sqlStartDate);
        int ID = getDeliveryRunByIDCourier(dl.getiDDeliverer());
        DeliveryRunLineDB delLineDB = new DeliveryRunLineDB();
        for(Order o : dl.getToDeliver()) {
            delLineDB.addDeliveryRunLine(o.getOrderID(), ID);
        }
        return true;
    }

    private int getDeliveryRunByIDCourier(int iDCourier) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getLastDeliveryRunID(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, iDCourier);
            callStmt.execute();
            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next()) {
                return rSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }

    public Date getLastCourierDeliveryRunDate(int courierID){
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getLastCourierDeliveryRunDate(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, courierID);
            callStmt.execute();
            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            Date date = null;
            if (rSet.next())
                date = rSet.getTimestamp(1);
            callStmt.close();
            if (date != null) {
                return date;
            }
            throw new SQLException();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Adiciona o ClientOrder especificado à tabela "ClientOrder".
     *
     * @param courierID         Client NIF
     */
    private void addDeliveryRun(int courierID, java.sql.Timestamp date) {
        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addDeliveryRun(?, ?) }");
            callStmt.setInt(1, courierID);
            callStmt.setTimestamp(2, date);
            callStmt.execute();
            callStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
