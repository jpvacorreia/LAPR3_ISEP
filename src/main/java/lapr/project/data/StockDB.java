package lapr.project.data;

import lapr.project.controller.ApplicationPOT;
import lapr.project.model.Stock;
import lapr.project.model.StockLine;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDB extends DataHandler{

    public boolean addStock(int pharmacyID){
        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addStock(?) }");

            callStmt.setInt(1, pharmacyID);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Stock getStock(int pharmacyID){
        List<StockLine> stockLineList = new ArrayList<>();
        StockLineDB sldb = ApplicationPOT.getInstance().getPlatform().getStockLineDB();
        try{
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ ? = call getStock(?) }");

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, pharmacyID);

            callStmt.execute();

            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if(rSet.next()){
                int phID = rSet.getInt(1);
                List<StockLine> lines = sldb.getStockLines(phID);

                Stock stock = new Stock(phID);
                stock.setStock(lines);
                return stock;
            }
            callStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

