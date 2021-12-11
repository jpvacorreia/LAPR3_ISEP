package lapr.project.data;

import lapr.project.controller.ApplicationPOT;
import lapr.project.model.*;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PharmacyDB extends DataHandler {


    public Pharmacy getPharmacyByNif(int pharmacyNIF) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPharmacy(?) }");
            if (handleResultSet(callStmt, pharmacyNIF, true).size() > 0) {
                callStmt = getConnection().prepareCall("{ ? = call getPharmacy(?) }");
                return handleResultSet(callStmt, pharmacyNIF, true).get(0);
            }
            throw new SQLException();
        } catch (SQLException e) {
            throw new IllegalArgumentException("No Pharmacy with NIF: " + pharmacyNIF);
        }
    }

    public Pharmacy getPharmacyByID(int pharmacyID) {

        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getPharmacyByID(?) }");
            return handleResultSet(callStmt, pharmacyID, true).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Pharmacy with id: " + pharmacyID);
    }

    public boolean addPharmacy(Pharmacy pharmacy) {
        if (ApplicationPOT.getInstance().getPlatform().getAddressDB().addAddress(pharmacy.getAddress())) {
            boolean bool = addPharmacy(pharmacy.getName(), pharmacy.getNif(), pharmacy.getIdAdmin(), pharmacy.getAddress().getCoordinateX(), pharmacy.getAddress().getCoordinateY(), pharmacy.getVoltageInput(), pharmacy.getCurrentInput(), pharmacy.getMaxScooters());
            pharmacy = getPharmacyByNif(pharmacy.getNif());
            int size = pharmacy.getMaxScooters() * 2;
            for (int i = 0; i < size; i++) {
                ParkingSlot parkingSlot = new ParkingSlot(pharmacy.getId());
                ApplicationPOT.getInstance().getPlatform().getParkingSlotDB().addParkingSlot(parkingSlot);
                parkingSlot = ApplicationPOT.getInstance().getPlatform().getParkingSlotDB().getParkingSlotFromPharmacy(pharmacy.getId());
                if (i < size / 2) {
                    ParkingSlotsScooters pss = new ParkingSlotsScooters(parkingSlot.getId(), 1, true);
                    ApplicationPOT.getInstance().getPlatform().getParkingSlotsScootersDB().addParkingSlotsScooters(pss);
                } else {
                    ParkingSlotDrones psd = new ParkingSlotDrones(parkingSlot.getId(), 1, true);
                    ApplicationPOT.getInstance().getPlatform().getParkingSlotDronesDB().addParkingSlotDrones(psd);
                }
            }
            ApplicationPOT.getInstance().getPlatform().getStockDB().addStock(pharmacy.getId());
            ArrayList<Product> arrayList = ApplicationPOT.getInstance().getPlatform().getProductDB().getAllProducts();
            for (Product p : arrayList) {
                ApplicationPOT.getInstance().getPlatform().getStockLineDB().addStockLine(new StockLine(pharmacy.getId(), p.getId(), 1000));
            }
            return bool;
        }
        return false;
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Adiciona a Pharmacy especificada à tabela "Pharmacy".
     *
     * @param name    Pharmacy's name.
     * @param idAdmin Pharmacy's admin's id
     * @param coordX  Pharmacy's coordinate X.
     * @param coordY  Pharmacy's coordinate Y.
     * @param nif     Pharmacy's nif.
     */

    private boolean addPharmacy(String name, int nif, int idAdmin, double coordX, double coordY, int voltageInput,
                                int currentInput, int maxScooters) {

        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call addPharmacy(?,?,?,?,?,?,?,?) }");

            callStmt.setString(1, name);
            callStmt.setDouble(2, nif);
            callStmt.setInt(3, idAdmin);
            callStmt.setDouble(4, coordX);
            callStmt.setDouble(5, coordY);
            callStmt.setInt(6, voltageInput);
            callStmt.setInt(7, currentInput);
            callStmt.setInt(8, maxScooters);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Exemplo de invocação de uma "stored procedure".
     * <p>
     * Remove a Pharmacy especificada da tabela "Pharmacy".
     *
     * @param pharmacyNIF o NIF Pharmacy.
     */
    public boolean removePharmacy(int pharmacyNIF) {

        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call removePharmacy(?) }");

            callStmt.setLong(1, pharmacyNIF);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException("No Pharmacy with given NIF " + pharmacyNIF);
        }

    }

    private List<Pharmacy> handleResultSet(CallableStatement callStmt, int parameter, boolean use) throws
            SQLException {
        List<Pharmacy> pharmacyList = new ArrayList<>();

        // Regista o tipo de dados SQL para interpretar o resultado obtido.
        callStmt.registerOutParameter(1, OracleTypes.CURSOR);

        if (use) {
            callStmt.setInt(2, parameter);
        }

        callStmt.execute();

        // Guarda o cursor retornado num objeto "ResultSet".
        ResultSet rSet = (ResultSet) callStmt.getObject(1);
            while (rSet.next()) {
                int phID = rSet.getInt(1);
                String phName = rSet.getString(2);
                int phNif = rSet.getInt(3);
                int adminID = rSet.getInt(4);
                double phCoordX = rSet.getDouble(5);
                double phCoordY = rSet.getDouble(6);
                int voltageInput = rSet.getInt(7);
                int currentInput = rSet.getInt(8);
                int maxScooters = rSet.getInt(9);


                Address addr = ApplicationPOT.getInstance().getPlatform().getAddressDB().getAddress(phCoordX, phCoordY);

                pharmacyList.add(new Pharmacy(phID, phName, phNif, adminID, addr, voltageInput, currentInput, maxScooters));
            }
        callStmt.close();
        return pharmacyList;
    }

    public List<Pharmacy> getAllPharmacies() {
        CallableStatement callStmt = null;
        try {
            callStmt = getConnection().prepareCall("{ ? = call getAllPharmacies() }");
            return handleResultSet(callStmt, 0, false);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No Pharmacies found");
    }

    public boolean updatePharmacy(Pharmacy ph) {
        return updatePharmacy(ph.getId(), ph.getName(), ph.getNif(), ph.getIdAdmin(), ph.getVoltageInput(), ph.getCurrentInput(), ph.getMaxScooters());
    }

    private boolean updatePharmacy(int id, String name, int nif, int idAdmin, int inputVoltage, int inputCurrent,
                                   int maxScoots) {
        try {
            getConnection();
            CallableStatement callStmt = getConnection().prepareCall("{ call updatePharmacy(?,?,?,?,?,?,?) }");

            callStmt.setInt(1, id);
            callStmt.setString(2, name);
            callStmt.setInt(3, nif);
            callStmt.setInt(4, idAdmin);
            callStmt.setInt(5, inputVoltage);
            callStmt.setInt(6, inputCurrent);
            callStmt.setInt(7, maxScoots);

            callStmt.execute();

            callStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
