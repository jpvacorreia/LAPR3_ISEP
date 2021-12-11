package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class DeliveryRunLineDB extends DataHandler{
    public void addDeliveryRunLine(int orderID, int id) {
        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addDeliveryRunLine(?, ?) }");
            callStmt.setInt(1, orderID);
            callStmt.setInt(2, id);
            callStmt.execute();
            callStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
