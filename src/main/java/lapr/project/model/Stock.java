package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.PharmacyDB;
import lapr.project.data.StockLineDB;
import lapr.project.utils.GraphOperations;
import lapr.project.utils.Pair;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class Stock {
    int pharmacyID;
    List<StockLine> stock;

    public Stock(int pharmacyID) {
        this.pharmacyID = pharmacyID;
    }

    public List<StockLine> getStock() {
        return stock;
    }

    public int getPharmacyID() {
        return pharmacyID;
    }

    public void setStock(List<StockLine> stock) {
        this.stock = stock;
    }

    /**
     * Updates the Stock of a pharmacy
     *
     * @param order - Order that changes the stock
     * @return boolean - True when it updates everything successfully
     */
    public boolean updateStock(Order order) throws IOException{
        Map<Integer, List<String>> backOrderMap = new LinkedHashMap<>();
        for(OrderLine ol : order.getCart()){
            for(StockLine sl : getStock()){
                if(sl.getProductID() == ol.getIdProduct()) {
                    updateStock(ol, sl, backOrderMap);
                    break;
                }
            }
        }
        sendDeliveryNotes(backOrderMap);
        return true;
    }

    //TO DO
    private void sendDeliveryNotes(Map<Integer, List<String>> backOrderMap) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmssZ");
        Date date = new Date();
        for(int i : backOrderMap.keySet()){
            String fileName = "DeliveryNotes\\NoteTo" + ApplicationPOT.getInstance().getPlatform().getPharmacyDB().getPharmacyByID(i).getName()+"From"+ApplicationPOT.getInstance().getPlatform().getPharmacyDB().getPharmacyByID(pharmacyID).getName()+ " " + formatter.format(date)+".txt";
            Path file = Paths.get(fileName);
            List<String> lines = new ArrayList<>();
            lines.add("Products requested:");
            Files.write(file,backOrderMap.get(i), StandardCharsets.UTF_8);
        }
        for(int i : backOrderMap.keySet()){
            String fileName = "DeliveryNotes\\NoteTo" + ApplicationPOT.getInstance().getPlatform().getPharmacyDB().getPharmacyByID(pharmacyID).getName()+"From"+ApplicationPOT.getInstance().getPlatform().getPharmacyDB().getPharmacyByID(i).getName()+ " " + formatter.format(date)+".txt";
            Path file = Paths.get(fileName);
            List<String> lines = new ArrayList<>();
            lines.add("Products delivered:");
            Files.write(file,backOrderMap.get(i), StandardCharsets.UTF_8);
        }
    }

    /**
     * Updates the Stock of a pharmacy
     *
     * @param ol - OrderLine regarding the quantity of a certain product ordered
     * @param sl - StockLine corresponding to the same product
     * @param backOrderMap
     * @return boolean - True when it updates everything successfully
     */
    public boolean updateStock(OrderLine ol, StockLine sl, Map<Integer, List<String>> backOrderMap){
        if(sl.getQuantity() >= ol.getQuantity()){
            return sl.update(ol.getQuantity());
        }else{
            int remain = ol.getQuantity() - sl.getQuantity() + 1;
            sl.update(sl.getQuantity() - 1);
            return backOrder(sl.getProductID(), remain, backOrderMap);
        }


    }

    /**
     * Method responsable to back order products from other pharmacies when the existing stock
     * isn't enough to satisfy an order
     *
     * @param productID - product id number needed
     * @param remain - remaining stock
     * @param backOrderMap
     * @retur boolean - True when nothing goes wrong
     */
    private boolean backOrder(int productID, int remain, Map<Integer, List<String>> backOrderMap){
        PharmacyDB phDB = ApplicationPOT.getInstance().getPlatform().getPharmacyDB();
        Pharmacy current = phDB.getPharmacyByID(getPharmacyID());
        TreeMap<Double, Integer> distances = new TreeMap<>();
        for (Pharmacy ph : ApplicationPOT.getInstance().getPlatform().getPharmacyDB().getAllPharmacies()) {
            if(ph.getId() != current.getId()) {
                double distance = GraphOperations.calculateDistance(Pair.createPair(ph.getAddress().getCoordinateX(), ph.getAddress().getCoordinateY()), Pair.createPair(current.getAddress().getCoordinateX(), current.getAddress().getCoordinateY()));
                distances.put(distance, ph.getId());
            }
        }

        StockLineDB slDB = ApplicationPOT.getInstance().getPlatform().getStockLineDB();
        for(Map.Entry<Double, Integer> e : distances.entrySet()){
            if(slDB.getQuantity(e.getValue(), productID) >= remain){
                if(!backOrderMap.containsKey(e.getValue()))
                    backOrderMap.put(e.getValue(), new ArrayList<>());
                    backOrderMap.get(e.getValue()).add(ApplicationPOT.getInstance().getPlatform().getProductDB().getProduct(productID).getName() +" - "+remain);
                return slDB.getStockLine(e.getValue(), productID).update(remain);
            }
        }
        return false;
    }
}
