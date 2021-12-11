package lapr.project.data;

import lapr.project.controller.ApplicationPOT;
import lapr.project.model.Order;
import lapr.project.model.OrderLine;
import lapr.project.model.Product;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientOrderDB extends DataHandler{

    public int getLastClientOrderID(int clientID) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getLastClientOrderID(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, clientID);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet  = (ResultSet) callStmt.getObject(1);
            int id = -1;
            if (rSet.next()) {
                id = rSet.getInt(1);
            }
            callStmt.close();
            if (id != -1){
                return id;
            }
            throw new SQLException();
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new IllegalArgumentException("No orders were found");
        }
    }

    public ArrayList<Order> getClientOrders(int clientID) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getClientOrders(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, clientID);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            ArrayList<Order> orders = new ArrayList<>();
            int size = rSet.getFetchSize();
            while (size > 0) {
                if (rSet.next()) {

                    int orderID = rSet.getInt(1);
                    int clientIDfromDB = rSet.getInt(2);
                    Date orderDate = rSet.getTimestamp(3);
                    int status = rSet.getInt(4);
                    int pharmacyID = rSet.getInt(5);
                    OrderLineDB olDB = new OrderLineDB();
                    ArrayList<OrderLine> arrayList = olDB.getOrderLinesFromOrder(orderID);

                    Order order = new Order(orderID, clientIDfromDB, orderDate, status, arrayList, pharmacyID);
                    orders.add(order);
                }
                size--;
            }
            callStmt.close();

            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Error");
    }

    public boolean addClientOrder(Order ord) {
        boolean flag = false;
        flag = addClientOrder(ord.getClientID(), ord.getOrderDate(),ord.getState(),ord.getPharmacyID());
        int orderID = getLastClientOrderID(ord.getClientID());
        ord.setOrderID(orderID);
        OrderLineDB ordLineDB = ApplicationPOT.getInstance().getPlatform().getOrderLineDB();
        for(OrderLine ol : ord.getCart()) {
            ol.setIdOrder(orderID);
            ordLineDB.addOrderLine(ol);
        }
        return flag;
    }


    /**
     *
     * @param clientID
     * @param date
     * @param status
     */
    private boolean addClientOrder(int clientID, Date date, int status, int phID) {

        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call addClientOrder(?,?,?,?) }");

            java.sql.Timestamp sqlStartDate = new java.sql.Timestamp(date.getTime());

            callStmt.setInt(1, clientID);
            callStmt.setTimestamp(2, sqlStartDate);
            callStmt.setInt(3,status);
            callStmt.setInt(4,phID);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateOrderStatus(int orderID, int status) {

        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call updateOrderStatus(?,?) }");

            callStmt.setInt(1, orderID);
            callStmt.setInt(2, status);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Remove o ClientOrder especificado da tabela "ClientOrder".
     *
     * @param orderID           Order identification number
     */
    public boolean removeClientOrder(int orderID) {

        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call removeCreditCard(?) }");

            callStmt.setInt(1, orderID);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // THIS METHOD CAN BE REPLACED WITH getAllOrdersWithState(int state)

/*    public ArrayList<Order> getAllReadyOrders(){
        ArrayList<Order> array = new ArrayList<>();
        OrderLineDB ol = new OrderLineDB();
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllReadyOrders() }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            // Executa a invocação da função "getSailor".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            int size = rSet.getFetchSize();
            for (int i = 0; i<size; i++) {
                if (rSet.next()) {
                    int idOrder = rSet.getInt(1);
                    int clientID = rSet.getInt(2);
                    Date orderDate = rSet.getTimestamp(3);
                    int stateID = rSet.getInt(4);
                    ArrayList<OrderLine> cart = ol.getOrderLinesFromOrder(idOrder);
                    Order or = new Order(idOrder, clientID, orderDate, stateID, cart);
                    array.add(or);
                }
            }
            return array;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public ArrayList<Order> getAllOrdersWithState(int state) {
        ArrayList<Order> array = new ArrayList<>();
        OrderLineDB ol = new OrderLineDB();
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllOrdersWithState(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.setInt(2, state);

            // Executa a invocação da função "getSailor".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            while (rSet.next()) {
                int idOrder = rSet.getInt(1);
                int clientID = rSet.getInt(2);
                Date orderDate = rSet.getTimestamp(3);
                int stateID = rSet.getInt(4);
                int pharmacyID = rSet.getInt(5);
                ArrayList<OrderLine> cart = ol.getOrderLinesFromOrder(idOrder);
                Order or = new Order(idOrder, clientID, orderDate, stateID, cart, pharmacyID);
                array.add(or);
            }

            callStmt.close();

            return array;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
