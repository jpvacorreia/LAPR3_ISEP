package lapr.project.model;

import lapr.project.controller.ApplicationPOT;
import lapr.project.data.api.DeliveryRoute;
import lapr.project.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class DeliveryRunRegistry {

    /**
     * Generates a delivery run for the courier
     * @param courierEmail courier email
     * @param toDeliver list of orders to deliver
     * @param pharmacyID respective pharmacy id
     * @return created delivery run
     */
    public DeliveryRun createDeliveryRun(String courierEmail, List<Order> toDeliver, int pharmacyID) {
        double orderPayload = 0;
        Pharmacy startingPoint = ApplicationPOT.getInstance().getPlatform().getPharmacyDB().getPharmacyByID(pharmacyID);
        int courierNIF = ApplicationPOT.getInstance().getPlatform().getRegisterCourier().getCourierNIFByEmail(courierEmail);
        Courier courier = ApplicationPOT.getInstance().getPlatform().getCourierDB().getCourier(courierNIF);
        double maxPayload = getScooterMaxPayload(courierNIF);
        for (Order o : toDeliver) {
            for (OrderLine ol : o.getCart()) {
                Product p = Product.getProductByID(ol.getIdProduct());
                double productWeight = p.getWeight() * ol.getQuantity();
                orderPayload += productWeight;
            }
        }
        if (orderPayload < maxPayload) {
            return new DeliveryRun(courier.getId(), toDeliver, startingPoint);
        }
        throw new IllegalArgumentException("Given payload is too heavy! Choose less items to carry");
    }

    /**
     * Generate delivery run for drones
     * @param droneID drone id
     * @param toDeliver list of orders to deliver
     * @param pharmacyID pharmacy id
     * @return created delivery run
     */
    public DeliveryRun createDeliveryRun(int droneID, List<Order> toDeliver, int pharmacyID){
        double orderPayload = 0;
        for (Order o : toDeliver) {
            for (OrderLine ol : o.getCart()) {
                Product p = Product.getProductByID(ol.getIdProduct());
                double productWeight = p.getWeight() * ol.getQuantity();
                orderPayload += productWeight;
            }
        }
        Drone deliverer = ApplicationPOT.getInstance().getPlatform().getDroneDB().getDroneById(droneID);
        Pharmacy startingPoint = ApplicationPOT.getInstance().getPlatform().getPharmacyDB().getPharmacyByID(pharmacyID);
        double maxPayload = deliverer.getMaxPayload();
        if (orderPayload < maxPayload) {
            return new DeliveryRun(deliverer.getID(), toDeliver, startingPoint);
        }
        throw new IllegalArgumentException("Given payload is too heavy! Choose less items to carry");
    }

    /**
     * Creates a delivery run for the simulation, depending on the admin's choice
     * @param choice user input
     * @param toDeliver list of orders to deliver
     * @return created delivery run
     */
    public DeliveryRun createDeliveryRun(int choice, List<Order> toDeliver){
        if(choice == 1){
            int idDeliverer = Constants.DEFAULT_COURIER_ID;
            Courier cour = ApplicationPOT.getInstance().getPlatform().getCourierDB().getCourierById(idDeliverer);
            return createDeliveryRun(cour.getEmail(), toDeliver, Constants.DEFAULT_PHARMACY_ID);
        }else{
            int idDeliverer = Constants.DEFAULT_DRONE_ID;
            return createDeliveryRun(idDeliverer, toDeliver, Constants.DEFAULT_PHARMACY_ID);
        }
    }

    /**
     * Saves a delivery run on the DB
     * @param deliveryRun delivery run to save
     * @return true if saved
     */
    public boolean confirmDeliveryRun(DeliveryRun deliveryRun) {
        return ApplicationPOT.getInstance().getPlatform().getDeliveryRunDB().addDeliveryRun(deliveryRun);
    }

    /**
     * Gets the max payload of the scooter
     * @param courierNIF courier nif
     * @return scooter max payload
     */
    private double getScooterMaxPayload(int courierNIF){
        return RegisterScooter.getScooterByID(RegisterCourier.getCourier(courierNIF).getScooterId()).getMaxPayload();
    }

    /**
     * Calculates the delivery weight of a run
     * @param dR deliver run
     * @return calculated weight
     */
    public double calculateDeliveryWeight(DeliveryRun dR){
        double totalWeight = 0;
        Courier temp = ApplicationPOT.getInstance().getPlatform().getCourierDB().getCourierById(dR.getiDDeliverer());
        totalWeight += temp.getWeight();
        for (Order o : dR.getToDeliver()) {
            for (OrderLine ol : o.getCart()) {
                Product p = Product.getProductByID(ol.getIdProduct());
                double productWeight = p.getWeight() * ol.getQuantity();
                totalWeight += productWeight;
            }
        }
        Scooter tempS = ApplicationPOT.getInstance().getPlatform().getScooterDB().getScooterByID(temp.getScooterId());
        totalWeight += tempS.getWeight();
        return totalWeight;
    }

    /**
     * generates the delivery route
     * @param choice user input
     * @param dR delivery run
     * @return updated delivery run
     */
    public DeliveryRun generateDeliveryRoute(int choice, DeliveryRun dR) throws RuntimeException {
        boolean isDistance;
        if(choice == 1){
            isDistance = true;
        }else{
            isDistance = false;
        }
        List<Address> addresses = getAddressesToDeliver(dR);
        Courier deliverer = ApplicationPOT.getInstance().getPlatform().getCourierDB().getCourierById(dR.getiDDeliverer());
        Scooter usedScooter = ApplicationPOT.getInstance().getPlatform().getScooterDB().getScooterByID(deliverer.getScooterId());
        double totalWeight = calculateDeliveryWeight(dR) + usedScooter.getWeight() + deliverer.getWeight();
        DeliveryRoute deliveryRoute = new DeliveryRoute(Constants.PROXIMITY_MAP_SCOOTER, addresses, totalWeight, isDistance, deliverer.pharmacyId, dR.getiDDeliverer());
        deliveryRoute.convertAddressToVertex();
        deliveryRoute.getShortestPathVisitingAllAddresses();
        dR.setDeliveryRoute(deliveryRoute);
        return dR;
    }

    /**
     * gets all the addresses to deliver
     * @param dR delivery run
     * @return list of adresses to deliver
     */
    private List<Address> getAddressesToDeliver(DeliveryRun dR){
        List<Order> toDeliver = dR.getToDeliver();
        List<Address> allAddresses = new ArrayList<>();
        for(Order o : toDeliver){
            int clientID = o.getClientID();
            Client client = ApplicationPOT.getInstance().getPlatform().getClientDB().getClientByID(clientID);
            Address a = client.getAddress();
            allAddresses.add(a);
        }
        return allAddresses;
    }

    /**
     * generates the delivery run for the drone simulation
     * @param choice user input
     * @param dr delivery run
     * @return created delivery run
     */
    public DeliveryRun generateDeliveryRouteDrone(int choice, DeliveryRun dr) throws RuntimeException{
        boolean isDistance;
        if(choice == 1){
            isDistance = true;
        }else{
            isDistance = false;
        }
        List<Address> addresses = getAddressesToDeliver(dr);
        Drone deliverer = ApplicationPOT.getInstance().getPlatform().getDroneDB().getDroneById(dr.getiDDeliverer());
        double totalWeight = calculateDeliveryWeight(dr) + deliverer.getWeight();
        DeliveryRoute deliveryRouteDrone = new DeliveryRoute(Constants.PROXIMITY_MAP_DRONE, addresses, totalWeight, isDistance, deliverer.getPharmacyID(), dr.getiDDeliverer());
        deliveryRouteDrone.convertAddressToVertex();
        deliveryRouteDrone.getShortestPathVisitingAllAddresses();
        dr.setDeliveryRoute(deliveryRouteDrone);
        return dr;
    }

}
