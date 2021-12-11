package lapr.project.data;

import lapr.project.model.Stock;
import lapr.project.model.StockLine;
import oracle.jdbc.OracleTypes;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockLineDB extends DataHandler {

    public void addStockLine(StockLine sl) {
        addStockLine(sl.getPharmacyID(), sl.getProductID(), sl.getQuantity());
    }

    private void addStockLine(int phID, int prodID, int quantity){
        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addStockLine(?,?,?) }");

            callStmt.setInt(1, phID);
            callStmt.setInt(2, prodID);
            callStmt.setInt(3, quantity);

            callStmt.execute();

            callStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<StockLine> getAllStockLines(){
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllStockLines() }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            ArrayList<StockLine> arrayList = new ArrayList<>();
            while(rSet.next()){
                int phid = rSet.getInt(1);
                int productid = rSet.getInt(2);
                int quantity = rSet.getInt(3);
                StockLine sl = new StockLine(phid, productid, quantity);
                arrayList.add(sl);
            }
            callStmt.close();
            return arrayList;
        } catch (SQLException e) {

            throw new IllegalArgumentException("No StockLines found");
        }
    }



    public List<StockLine> getStockLines(int phID){
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getStockLines(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, phID);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            int cursorSize = rSet.getFetchSize();
            ArrayList<StockLine> arrayList = new ArrayList<>();
            while (rSet.next()) {
                int phid = rSet.getInt(1);
                int productid = rSet.getInt(2);
                int quantity = rSet.getInt(3);
                StockLine sl = new StockLine(phid, productid, quantity);
                arrayList.add(sl);
            }
            return arrayList;
        } catch (SQLException e) {
            throw new IllegalArgumentException("No OrderLines found");
        }
    }

    public void removeStockLine(int phID, int idProduct) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call removeStockLine(?,?) }");

            callStmt.setInt(1, phID);
            callStmt.setInt(2, idProduct);

            callStmt.execute();

            callStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateStockLine(int phID, int prodID, int toRemove){
        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call updateStockLine(?,?,?) }");

            callStmt.setInt(1, phID);
            callStmt.setInt(2, prodID);
            callStmt.setInt(3, toRemove);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getQuantity(int phID, int prodID) {
        try {
            CallableStatement callStmt = getConnection().prepareCall("{? = call getQuantity(?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, phID);
            callStmt.setInt(3, prodID);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            int ret = -1;
            if (rSet.next()) {
                ret = rSet.getInt(1);
            }
            callStmt.close();
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public StockLine getStockLine(int phID, int prodID){
        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{? = call getStockLine(?,?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, phID);
            callStmt.setInt(3, prodID);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            StockLine stockLine = null;
            if (rSet.next()) {
                int phIDr = rSet.getInt(1);
                int prodIDr = rSet.getInt(2);
                int quantityR = rSet.getInt(3);
                stockLine = new StockLine(phIDr, prodIDr, quantityR);
            }
            callStmt.close();
            return stockLine;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
