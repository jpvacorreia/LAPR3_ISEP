package lapr.project.data;

import lapr.project.model.Invoice;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class InvoiceDB extends DataHandler{

    public boolean addInvoice(Invoice inv) {
        return addInvoice(inv.getOrderID(), inv.getInvoiceData());
        //int invoiceID = getLastInvoiceID();

    }

    private boolean addInvoice(int orderID, Date date) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addInvoice(?,?) }");

            java.sql.Timestamp sqlStartDate = new java.sql.Timestamp(date.getTime());

            callStmt.setInt(1, orderID);
            callStmt.setTimestamp(2, sqlStartDate);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getLastInvoiceID(){
        int invoiceID = 0;
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getLatestInvoice() }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            // Executa a invocação da função "getLatestInvoice".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if(rSet.next()) {
                invoiceID = rSet.getInt(1);
            }
            callStmt.close();

            return invoiceID;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
