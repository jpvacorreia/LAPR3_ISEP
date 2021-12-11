package lapr.project.data;


import lapr.project.controller.ApplicationPOT;
import lapr.project.model.User;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDB extends DataHandler {
    /**
     * Exemplo de invocação de uma "stored function".
     * <p>
     * Devolve o registo do User especificado existente na tabela
     * "User".
     *
     * @param userEmail o NIF do Client.
     * @return o registo do id especificado ou null, se esse registo não
     * existir.
     */
    public User getUser(String userEmail) {

        closeAll();
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPlatformUser(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, userEmail);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String email = rSet.getString(1);
                String password = rSet.getString(2);
                int userType = rSet.getInt(3);

                User user = ApplicationPOT.getInstance().getPlatform().getRegisterUser().newUser(email, password, userType);
                callStmt.close();
                return user;
            }
            callStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }

    public boolean addUser(User user) {
        return addUser(user.getEmail(), user.getPassword(), user.getUserType());
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Adiciona o User especificado à tabela "User".
     *
     * @param email         User's email.
     * @param password      User's password.
     * @param userType      User's type.
     */

    private boolean addUser(String email, String password, int userType) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addPlatformUser(?,?,?) }");

            callStmt.setString(1, email);
            callStmt.setString(2, password);
            callStmt.setInt(3, userType);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Cannot register User, please check input data");
            //e.printStackTrace();
        }
        return false;
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Remove o User especificado da tabela "User".
     *
     * @param userEmail User's email.
     * @return
     */
    public boolean removeUser(String userEmail) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call removePlatformUser(?) }");

            callStmt.setString(1, userEmail);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
