package lapr.project.data;

import lapr.project.controller.ApplicationPOT;
import lapr.project.model.Address;
import lapr.project.model.PostalCode;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDB extends DataHandler {
    /**
     * Exemplo de invocação de uma "stored function".
     * <p>
     * Devolve o registo do address especificado existente na tabela
     * "Address".
     *
     * @param coordinateX o identificador da Address.
     * @param coordinateY o identificador da Address.
     * @return o registo do id especificado ou null, se esse registo não
     * existir.
     */
    public Address getAddress(double coordinateX, double coordinateY) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAddress(?,?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            // Especifica o parâmetro de entrada da função "getAddress".
            callStmt.setDouble(2, coordinateX);
            callStmt.setDouble(3, coordinateY);

            // Executa a invocação da função "getAddress".
            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                String street = rSet.getString(1);
                double coordX = rSet.getDouble(2);
                double coordY = rSet.getDouble(3);
                int postalCodeId = rSet.getInt(4);
                double elevation = rSet.getDouble(5);

                PostalCodeDB postalCodeDB = new PostalCodeDB();
                PostalCode pc = postalCodeDB.getPostalCode(postalCodeId);

                callStmt.close();
                return new Address(street, pc.getCity() + "-" + pc.getLocal(), coordX, coordY,elevation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Address with coordinates:" + coordinateX + " and " + coordinateY);
    }

    public boolean addAddress(Address address) {
        ApplicationPOT.getInstance().getPlatform().getPostalCodeDB().addPostalCode(address.getPostalCode());

        int pcid = ApplicationPOT.getInstance().getPlatform().getPostalCodeDB().getPostalCodeIdByAtributes(address.getPostalCode().getCity(), address.getPostalCode().getLocal());

        return addAddress(address.getStreet(), address.getCoordinateX(), address.getCoordinateY(), pcid, address.getElevation());
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Adiciona o address especificado à tabela "Address".
     *
     * @param street        the address
     * @param coordinateX   Address's coordinateX
     * @param coordinateY   Address's coordinateY
     * @param pcid          Address's postal code id
     */
    private boolean addAddress(String street, double coordinateX, double coordinateY, int pcid, double elevation) {

        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call addAddress(?,?,?,?,?) }");

            callStmt.setString(1, street);
            callStmt.setDouble(2, coordinateX);
            callStmt.setDouble(3, coordinateY);
            callStmt.setInt(4, pcid);
            callStmt.setDouble(5, elevation);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            //e.printStackTrace();
            return false;
        }
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Remove o address especificado da tabela "Address".
     *
     * @param coordinateX coordinate X from address.
     * @param coordinateY coordinate Y from address.
     */
    public void removeAddress(double coordinateX, double coordinateY) {

        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call removeAddress(?,?) }");

            callStmt.setDouble(1, coordinateX);
            callStmt.setDouble(2, coordinateY);

            callStmt.execute();

            callStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
