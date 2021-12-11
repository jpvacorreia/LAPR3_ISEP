package lapr.project.data;


import lapr.project.model.Product;
import lapr.project.model.User;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDB extends DataHandler {
    /**
     * Exemplo de invocação de uma "stored function".
     * <p>
     * Devolve o registo do User especificado existente na tabela
     * "User".
     *
     * @param productId product identification number
     * @return o registo do id especificado ou null, se esse registo não
     * existir.
     */
    public Product getProduct(int productId) {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getProduct(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, productId);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int prodId = rSet.getInt(1);
                String prodName = rSet.getString(2);
                double prodPrice = rSet.getDouble(3);
                double prodWeight = rSet.getDouble(4);
                int prodType = rSet.getInt(5);


                Product prod = new Product(prodId, prodName, prodWeight, prodPrice, prodType);

                callStmt.close();
                return prod;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No product with id:" + productId);
    }

    public ArrayList<Product> getAllProducts() {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllProducts() }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            int size = rSet.getFetchSize();
            ArrayList<Product> arrayList = new ArrayList<>();
            while (rSet.next()) {
                int prodId = rSet.getInt(1);
                String prodName = rSet.getString(2);
                double prodPrice = rSet.getDouble(3);
                double prodWeight = rSet.getDouble(4);
                int prodType = rSet.getInt(5);

                Product prod = new Product(prodId, prodName, prodWeight, prodPrice, prodType);

                arrayList.add(prod);
            }
            callStmt.close();
            return arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No products");
    }

    public void addProduct(Product p) {
        addProduct(p.getId(), p.getName(), p.getWeight(), p.getPrice(), p.getId_type());
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Adiciona o User especificado à tabela "User".
     *
     * @param id      Product id
     * @param name    Product name
     * @param weight  Product weight
     * @param price   Product price
     * @param id_type Product type id
     */

    private void addProduct(int id, String name, double weight, double price, int id_type) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call addPlatformProduct(?,?,?,?,?) }");

            callStmt.setInt(1, id);
            callStmt.setString(2, name);
            callStmt.setDouble(3, weight);
            callStmt.setDouble(4, price);
            callStmt.setInt(5, id_type);

            callStmt.execute();

            callStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Remove o Product especificado da tabela "Product".
     *
     * @param productId Product identification id.
     */
    public void removeProduct(String productId) {

        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call removeProduct(?) }");

            callStmt.setString(1, productId);

            callStmt.execute();

            callStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
