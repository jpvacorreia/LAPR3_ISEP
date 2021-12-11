package lapr.project.data;

import lapr.project.model.InvoiceLine;

import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InvoiceLineDB extends DataHandler{

    public boolean addInvoiceLine(InvoiceLine il) {
        return addInvoiceLine(il.getInvoiceID(), il.getProductID(), il.getOrderID());
    }


    private boolean addInvoiceLine(int idInvoice, int idProduct, int idOrder) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addInvoiceLine(?,?,?) }");

            callStmt.setInt(1, idInvoice);
            callStmt.setInt(2, idProduct);
            callStmt.setInt(3, idOrder);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<InvoiceLine> getInvoiceLinesFromInvoiceID(int invoiceID){
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getInvoiceLinesFromInvoiceID(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, invoiceID);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            int cursorSize = rSet.getFetchSize();
            ArrayList<InvoiceLine> arrayList = new ArrayList<>();
            while (rSet.next()) {
                int invoiceID_DB = rSet.getInt(1);
                int invoiceLineID = rSet.getInt(2);
                int productid = rSet.getInt(3);
                    int orderid = rSet.getInt(4);
                    InvoiceLine il = new InvoiceLine(invoiceID_DB, invoiceLineID, orderid, productid);
                arrayList.add(il);
            }
            callStmt.close();
            return arrayList;
        } catch (SQLException e) {
            throw new IllegalArgumentException("No InvoiceLines found");
        }
    }
}
