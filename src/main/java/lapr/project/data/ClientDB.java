package lapr.project.data;

import lapr.project.controller.ApplicationPOT;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.CreditCard;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDB extends DataHandler {
    /**
     * Exemplo de invocação de uma "stored function".
     * <p>
     * Devolve o nif do cliente especificado na tabela com o mesmo email
     *
     * @param email do cliente
     * @return nif do cliente
     */
    public int getClientIdByEmail(String email) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getClientIdByEmail(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setString(2, email);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            int rSet = (Integer) callStmt.getObject(1);
            callStmt.close();

            return rSet;
        } catch (SQLException e) {
            throw new IllegalArgumentException("No Client with logged email.");
        }

    }

    public Client getClient(int clientNIF) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getClient(?) }");
            Client clnt = handleResultSet(callStmt, clientNIF);
            if(clnt != null) {
                return clnt;
            }
            throw new SQLException();
        } catch (SQLException e) {
            throw new IllegalArgumentException("No Client with ID:" + clientNIF);
        }
    }

    public boolean addClient(Client client) {
        ApplicationPOT.getInstance().getPlatform().getAddressDB().addAddress(client.getAddress());

        ApplicationPOT.getInstance().getPlatform().getCreditCardDB().addCreditCard(client.getCredCard());

        return addClient(client.getName(), client.getNif(), client.getCredits(), client.getCredCard().getCreditCardNumber(),
                client.getAddress().getCoordinateX(), client.getAddress().getCoordinateY(), client.getEmail());

    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Adiciona o client especificado à tabela "Client".
     *
     * @param name     client's name.
     * @param email    client's email.
     * @param coordX   client's coordinate X.
     * @param coordY   client's coordinate Y.
     * @param nif      client's nif.
     * @param credCard client's CreditCard.
     * @param credits  client's credits.
     */

    private boolean addClient(String name, int nif, int credits, long credCard, double coordX, double coordY, String email) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addClient(?,?,?,?,?,?,?) }");

            callStmt.setString(1, name);
            callStmt.setDouble(2, nif);
            callStmt.setInt(3, credits);
            callStmt.setLong(4, credCard);
            callStmt.setDouble(5, coordX);
            callStmt.setDouble(6, coordY);
            callStmt.setString(7, email);

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
     * Remove o client especificado da tabela "Client".
     *
     * @param clientNIF o NIF Client.
     */
    public boolean removeClient(int clientNIF) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call removeClient(?) }");

            callStmt.setLong(1, clientNIF);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCredits(int clientid, int credits){
        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call updateCredits(?, ?) }");

            callStmt.setInt(1, clientid);
            callStmt.setInt(2, credits);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Client getClientByID(int ID) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getClientByID(?) }");
            return handleResultSet(callStmt, ID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Client with ID:" + ID);
    }

    private Client handleResultSet(CallableStatement callStmt, int parameter) throws SQLException {
        // Regista o tipo de dados SQL para interpretar o resultado obtido.
        callStmt.registerOutParameter(1, OracleTypes.CURSOR);
        callStmt.setInt(2, parameter);

        callStmt.execute();

        // Guarda o cursor retornado num objeto "ResultSet".
        ResultSet rSet = (ResultSet) callStmt.getObject(1);

        if (rSet.next()) {

            String clientName = rSet.getString(2);
            int clientNif = rSet.getInt(3);
            int clientCredits = rSet.getInt(4);
            long clientCreditCardNumber = rSet.getLong(5);
            double clientCoordX = rSet.getDouble(6);
            double clientCoordY = rSet.getDouble(7);
            String clientEmail = rSet.getString(8);

            AddressDB addressDB = new AddressDB();
            Address addr = addressDB.getAddress(clientCoordX, clientCoordY);

            CreditCardDB ccDB = new CreditCardDB();
            CreditCard credCard = ccDB.getCreditCard(clientCreditCardNumber);


            Client clnt = new Client(clientName, clientEmail, addr.getStreet(),
                    addr.getPostalCode().getLocal() + "-" + addr.getPostalCode().getLocal(),
                    clientCoordX, clientCoordY, clientNif, credCard.getCreditCardNumber(),
                    credCard.getValidity_year(), credCard.getValidity_month(), credCard.getCvv(), addr.getElevation());

            clnt.addCredits(clientCredits);
            callStmt.close();

            return clnt;
        }
        return null;
    }
}
