package lapr.project.data;

import lapr.project.model.Order;
import lapr.project.model.OrderLine;
import lapr.project.model.Product;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class OrderLineDB extends DataHandler{

    public ArrayList<OrderLine> getOrderLinesFromOrder(int orderID){
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getOrderLinesFromOrder(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, orderID);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            int cursorSize = rSet.getFetchSize();
            ArrayList<OrderLine> arrayList = new ArrayList<>();
            for (int j=0; j<cursorSize; j++) {
                if (rSet.next()) {
                    int orderid = rSet.getInt(1);
                    int productid = rSet.getInt(2);
                    int quantity = rSet.getInt(3);
                    OrderLine ol = new OrderLine(orderid, productid, quantity);
                    arrayList.add(ol);
                }
            }
            callStmt.close();
            return arrayList;
        } catch (SQLException e) {
            throw new IllegalArgumentException("No OrderLines found");
        }
    }

    public void addOrderLine(OrderLine ol) {
        addOrderLine(ol.getIdOrder(), ol.getIdProduct(), ol.getQuantity());
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Adiciona o creditCard especificado à tabela "CreditCard".
     *
     * @param idOrder           Order identification number
     * @param idProduct         Product identification number
     * @param quantity          Product quantity ordered
     * */
    private void addOrderLine(int idOrder, int idProduct, int quantity) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addOrderLine(?,?,?) }");

            callStmt.setInt(1, idOrder);
            callStmt.setInt(2, idProduct);
            callStmt.setInt(3, quantity);

            callStmt.execute();

            callStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Remove o ClientOrder especificado da tabela "ClientOrder".
     *
     * @param orderID           Order identification number
     */
    public boolean removeOrderLine(int orderID, int idProduct) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call removeOrderLine(?,?) }");

            callStmt.setInt(1, orderID);
            callStmt.setInt(2, idProduct);

            callStmt.execute();

            callStmt.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
