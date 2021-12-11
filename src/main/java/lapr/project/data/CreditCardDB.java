package lapr.project.data;

import lapr.project.model.CreditCard;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditCardDB extends DataHandler {
    /**
     * Exemplo de invocação de uma "stored function".
     * <p>
     * Devolve o registo do credit card especificado existente na tabela
     * "CreditCard".
     *
     * @param creditCardNumber Credit Card Number
     * @return o registo do id especificado ou null, se esse registo não
     * existir.
     */
    public CreditCard getCreditCard(long creditCardNumber) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getCreditCard(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setDouble(2, creditCardNumber);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int validity_year = rSet.getInt(2);
                int validity_month = rSet.getInt(3);
                int ccv = rSet.getInt(4);
                callStmt.close();

                return new CreditCard(creditCardNumber, validity_year, validity_month, ccv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Credit Card with the number:" + creditCardNumber);
    }

    public void addCreditCard(CreditCard creditCard) {
        addCreditCard(creditCard.getCreditCardNumber(), creditCard.getValidity_year(), creditCard.getValidity_month(), creditCard.getCvv());
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Adiciona o creditCard especificado à tabela "CreditCard".
     *
     * @param creditCardNumber      creditCard number
     * @param validity_year         creditCard year of validity
     * @param validity_month        creditCard month of validity
     * @param cvv                   creditCard verification code.
     */
    private void addCreditCard(double creditCardNumber, int validity_year, int validity_month, int cvv) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addCreditCard(?,?,?,?) }");

            callStmt.setDouble(1, creditCardNumber);
            callStmt.setInt(2, validity_year);
            callStmt.setInt(3, validity_month);
            callStmt.setInt(4, cvv);

            callStmt.execute();

            callStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Remove o creditCard especificado da tabela "creditCard".
     *
     * @param creditCardNumber Credit Card Number
     */
    public void removeCreditCard(double creditCardNumber) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call removeCreditCard(?) }");

            callStmt.setDouble(1, creditCardNumber);

            callStmt.execute();

            callStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
