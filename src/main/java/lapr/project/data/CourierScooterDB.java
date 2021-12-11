package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;
import java.util.Date;

import lapr.project.model.CourierScooter;

public class CourierScooterDB extends DataHandler {
     
    /**
     * Exemplo de invocação de uma "stored function".
     * <p>
     * Devolve o registo do CourierScooter especificado existente na tabela
     * "CourierScooter".
     *
     * @param scooterId o id da Scooter.
     * @return o registo do id especificado ou null, se esse registo não
     * existir.
     */
    public CourierScooter getCourierScooterByScooterId (int scooterId) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getCourierScooterByScooterId(?) }");

            // Regista o tipo de dados SQL para interpretar o resultado obtido.
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, scooterId);

            callStmt.execute();

            // Guarda o cursor retornado num objeto "ResultSet".
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                
                Date dateIn = rSet.getTimestamp(1);
                Date dateOut = rSet.getTimestamp(2);
                int idScooter = rSet.getInt(3);
                int idCourier = rSet.getInt(4);

                CourierScooter cs = new CourierScooter(dateIn, dateOut, idScooter, idCourier);
                callStmt.close();

                return cs;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No CourierScooter with Scooter ID:" + scooterId);
    }
    
    public boolean addCourierScooter (CourierScooter courierScooter) {
        
        return addCourierScooter(courierScooter.getDateIn(), courierScooter.getDateOut(), courierScooter.getIdScooter(), courierScooter.getIdCourier());
    
    }
    
    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Adiciona o courier especificado à tabela "CourierScooter".
     *
     * @param dateIn     CourierScooter's date in.
     * @param dateOut    CourierScooter's date out.
     * @param scooterId  CourierScooter's scooter id.
     * @param courierId  CourierScooter's courier id.
     */

    private boolean addCourierScooter(Date dateIn, Date dateOut, int scooterId, int courierId) {

        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call addCourierScooter(?,?,?,?) }");
            
            java.sql.Timestamp sqlStartDate1 = new java.sql.Timestamp(dateIn.getTime());
            java.sql.Timestamp sqlStartDate2 = new java.sql.Timestamp(dateOut.getTime());

            callStmt.setTimestamp(1, sqlStartDate1);
            callStmt.setTimestamp(2, sqlStartDate2);
            callStmt.setInt(3, scooterId);
            callStmt.setInt(4, courierId);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
