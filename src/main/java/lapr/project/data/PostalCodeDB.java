package lapr.project.data;

import lapr.project.model.PostalCode;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostalCodeDB extends DataHandler {
    /**
     * Exemplo de invocação de uma "stored function".
     * <p>
     * Devolve o registo do postal code especificado existente na tabela
     * "PostalCode".
     *
     * @param idPostalCode  PostalCode id
     * @return o registo do id especificado ou null, se esse registo não
     * existir.
     */
    public PostalCode getPostalCode(int idPostalCode) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPostalCode(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setLong(2, idPostalCode);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int city = rSet.getInt(1);
                int local = rSet.getInt(2);
                callStmt.close();

                return new PostalCode(city + "-" + local);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Postal Code with id:" + idPostalCode);
    }

    public void addPostalCode(PostalCode postalCode) {
        addPostalCode(postalCode.getCity(), postalCode.getLocal());
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Adiciona o PostalCode especificado à tabela "PostalCode".
     *
     * @param city    postal code city
     * @param local   postal code local.
     */
    private void addPostalCode(int city, int local) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addPostalCode(?,?) }");

            callStmt.setInt(1, city);
            callStmt.setInt(2, local);

            callStmt.execute();

            callStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Remove o PostalCode especificado da tabela "PostalCode".
     *
     * @param city      PostalCode city id.
     * @param local     PostalCode local id.
     */
    public void removePostalCode(int city, int local) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call removePostalCode(?,?) }");

            callStmt.setInt(1, city);
            callStmt.setInt(2, local);

            callStmt.execute();

            callStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * Devolve o registo do postal code especificado existente na tabela
     * "PostalCode".
     *
     * @param city      PostalCode city id.
     * @param local     PostalCode local id.
     */
    public int getPostalCodeIdByAtributes(int city, int local) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPostalCodeIdByAtributes(?,?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, city);
            callStmt.setInt(3, local);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int pcId = rSet.getInt(1);
                callStmt.close();

                return pcId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Postal Code with city like " + city + "and local like " + local);
    }


}
