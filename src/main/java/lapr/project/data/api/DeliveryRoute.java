package lapr.project.data.api;

import lapr.project.controller.ApplicationPOT;
import lapr.project.model.*;
import lapr.project.utils.*;

import java.util.*;

public class DeliveryRoute {
    private AdjacencyMatrixGraph deliveryMap;
    private double distance;
    private List<Vertex> route;
    private List<Address> addresses;
    private List<Vertex> addressesV;
    private final boolean isDrone;
    private double energyCost;
    private double totalWeight;
    private boolean isDistance;
    private int pharmacyID;
    private int courierID;
    private ProximityMap prox;

    public DeliveryRoute(ProximityMap prox, List<Address> addresses, double totalWeight, boolean isDistance, int pharmacyID, int courierID) {
        if (!isDistance) {
            prox.setEnergyCost(totalWeight);
        }
        this.deliveryMap = prox.getMap();
        this.isDistance = isDistance;
        this.addresses = addresses;
        this.distance = 0;
        this.route = new ArrayList<>();
        this.addressesV = new ArrayList<>();
        this.energyCost = 0;
        this.totalWeight = totalWeight;
        this.pharmacyID = pharmacyID;
        this.courierID = courierID;
        this.prox = prox;
        this.isDrone = prox.isDrone();
    }

    private double calculateEnergyCostOfRoute() {
        double energyCost = 0;
        if (isDistance) {
            for (int i = 1; i < route.size(); i++) {
                Edge temp = deliveryMap.getEdge(route.get(i - 1), route.get(i));
                energyCost += RegisterScooter.energyWastedBetweenTwoPoints(route.get(i - 1), route.get(i), temp, totalWeight, temp.getWindDirection());
            }
        } else {
            for (int i = 1; i < route.size(); i++) {
                Edge e = deliveryMap.getEdge(route.get(i - 1), route.get(i));
                energyCost += e.getEnergyCost();
            }
        }
        this.setEnergyCost((double) Math.round((energyCost) * 1000d) / 1000d);
        return ((double) Math.round((energyCost) * 1000d) / 1000d);
    }

    public double getDistance() {
        if (distance == 0) {
            return calculatePathSize();
        } else {
            return this.distance;
        }

    }

    public List<Vertex> getRoute() {
        return route;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setRoute(List<Vertex> route) {
        this.route = route;
    }

    public void setEnergyCost(double energyCost) {
        this.energyCost = energyCost;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
    /*
    public void calculateShortestPathVisitingAddresses() {
        Pair<double[][], String[][]> returned;
        if (isDistance) {
            returned = GraphOperations.floydWarshall(deliveryMap);
        } else {
            returned = GraphOperations.floydWarshallEnergy(deliveryMap);
        }
        double[][] floydWarshall = returned.getElement0();
        String[][] paths = returned.getElement1();
        Vertex first = null;
        double intermediateDistance = Double.MAX_VALUE;
        route.add(deliveryMap.vertices().get(0));
        for (Vertex v : addressesV) {
            int index = deliveryMap.vertices().indexOf(v);
            if (floydWarshall[0][index] < intermediateDistance) {
                first = v;
                intermediateDistance = floydWarshall[0][index];
            }
        }
        int indexFirst = deliveryMap.vertices().indexOf(first);
        for (Vertex vertex : stringTreatment(paths[0][indexFirst])) {
            route.add(vertex);
        }
        getPathWithAllAdresses(paths, indexFirst, floydWarshall);
        Vertex lastAddress = route.get(route.size() - 1);
        int indexLastAddress = deliveryMap.vertices().indexOf(lastAddress);
        List<Vertex> pathAddressToPharmacy = stringTreatment(paths[indexLastAddress][0]);
        for (Vertex vertex : pathAddressToPharmacy) {
            route.add(vertex);
        }
    }
     */

    private void getPathWithAllAdresses(String[][] paths, int indexFirst, double[][] floydWarshall) {
        List<Vertex> copy = addressesV;
        copy.remove(deliveryMap.vertices().get(indexFirst));
        Vertex closestNext = null;
        double distance = Double.MAX_VALUE;
        int indexA = indexFirst;
        int indexB = 0;
        while (!copy.isEmpty()) {
            for (Vertex v : copy) {
                if (floydWarshall[indexA][deliveryMap.vertices().indexOf(v)] < distance) {
                    closestNext = v;
                    indexB = deliveryMap.vertices().indexOf(closestNext);
                    distance = floydWarshall[indexFirst][deliveryMap.vertices().indexOf(v)];
                }
            }
            route.addAll(stringTreatment(paths[indexA][indexB]));
            copy.remove(closestNext);
            distance = Double.MAX_VALUE;
            indexA = indexB;
        }
    }
    /*
    public void insertAddressesOnMap() {
        Map<String, Vertex> visited = new HashMap<>();
        Pair<Double, Double> coordinates;
        Vertex address;
        for (Address a : addresses) {
            for (Edge e : deliveryMap.edges()) {
                if (a.getStreet().compareTo(e.getStreetName()) == 0) {
                    Vertex orig = e.getVOrig();
                    Vertex dest = e.getVDest();
                    if (!visited.containsKey(e.getStreetName())) {
                        coordinates = Pair.createPair(a.getCoordinateX(), a.getCoordinateY());
                        address = new Vertex(deliveryMap.getNumVertices() + 1, coordinates, a.getElevation());
                        visited.put(e.getStreetName(), address);
                    } else {
                        address = visited.get(e.getStreetName());
                        coordinates = address.getCoordinates();
                    }
                    if (!(deliveryMap.vertices().contains(address))) {
                        deliveryMap.insertVertex(address);
                        addressesV.add(address);
                    }
                    Pair<Double, Double> coordinatesOrig = orig.getCoordinates();
                    Pair<Double, Double> coordinatesDest = dest.getCoordinates();
                    Edge e1 = new Edge(a.getStreet(), GraphOperations.calculateDistance(coordinates, coordinatesOrig), orig, address, isDrone, e.getWindDirection());
                    Edge e2 = new Edge(a.getStreet(), GraphOperations.calculateDistance(coordinates, coordinatesDest), address, dest, isDrone, e.getWindDirection());
                    if (!isDistance) {
                        e1.setEnergyCost(totalWeight);
                        e2.setEnergyCost(totalWeight);
                    }
                    deliveryMap.insertEdge(orig, address, e1);
                    deliveryMap.insertEdge(address, dest, e2);
                }
            }
        }
    }

     */

    public void convertAddressToVertex() {
        for (Address a : addresses) {
            for (Vertex v : deliveryMap.vertices()) {
                if (v.getCoordinates().getElement0() == a.getCoordinateX() && v.getCoordinates().getElement1() == a.getCoordinateY()) {
                    if(!addressesV.contains(v)) {
                        addressesV.add(v);
                    }
                }
            }
        }
    }

    public void getShortestPathVisitingAllAddresses() throws RuntimeException{
        Pair<double[][], String[][]> returned;
        Courier deliverer = ApplicationPOT.getInstance().getPlatform().getCourierDB().getCourierById(courierID);
        Scooter scooterDeliverer = ApplicationPOT.getInstance().getPlatform().getScooterDB().getScooterByID(deliverer.getScooterId());
        double remainingPercentage = scooterDeliverer.getStateOfCharge();
        double remainingBattery = remainingPercentage * scooterDeliverer.getMaxBatteryCapacity() / 100.0;

        int i = 0;
        boolean found = false;
        Vertex startingPointV = null;
        if (isDistance) {
            returned = GraphOperations.floydWarshall(deliveryMap);
        } else {
            returned = GraphOperations.floydWarshallEnergy(deliveryMap);
        }
        double[][] floydWarshallDistance = returned.getElement0();
        String[][] floydWarshallPath = returned.getElement1();
        Pharmacy startingPoint = ApplicationPOT.getInstance().getPlatform().getPharmacyDB().getPharmacyByID(pharmacyID);
        Pair<Double, Double> startingPointCoordinates = Pair.createPair(startingPoint.getAddress().getCoordinateX(), startingPoint.getAddress().getCoordinateY());
        while (!found) {
            if (startingPointCoordinates.getElement0().equals(deliveryMap.vertices().get(i).getCoordinates().getElement0()) && startingPointCoordinates.getElement1().equals(deliveryMap.vertices().get(i).getCoordinates().getElement1())) {
                startingPointV = deliveryMap.vertices().get(i);
                found = true;
            }
            i++;
        }
        route.add(startingPointV);
        List<Vertex> copy = addressesV;
        Vertex previous = startingPointV;
        Vertex previous2 = null;
        while (!copy.isEmpty()) {
            previous2 = previous;
            previous = getShortestNext(previous, copy, floydWarshallDistance, floydWarshallPath);
            if(calculateEnergyCostBetweenTwoPoints(previous2, previous, floydWarshallPath, floydWarshallDistance) > remainingBattery/2){
                throw new RuntimeException("Delivery cannot be made, not enough battery capacity");
            }
            if (remainingBattery <= calculateEnergyCostBetweenTwoPoints(previous2, previous, floydWarshallPath, floydWarshallDistance) || remainingPercentage < Constants.MINIMUM_ACCEPTABLE_BATTERY) {
                previous = previous2;
                previous = getNearestPharmacy(previous, floydWarshallDistance, floydWarshallPath);
                remainingBattery = scooterDeliverer.getMaxBatteryCapacity();
                remainingPercentage = Constants.SCOOTER_FULLY_CHARGED;
            }else {
                remainingBattery -= calculateEnergyCostBetweenTwoPoints(previous2, previous, floydWarshallPath, floydWarshallDistance);
                remainingPercentage = (remainingBattery * 100) / scooterDeliverer.getMaxBatteryCapacity();
            }
            route.addAll(stringTreatment(floydWarshallPath[deliveryMap.vertices().indexOf(previous2)][deliveryMap.vertices().indexOf(previous)]));
            copy.remove(previous);
        }
        int indexPrevious = deliveryMap.vertices().indexOf(previous);
        int indexStartingPoint = deliveryMap.vertices().indexOf(startingPointV);
        route.addAll(stringTreatment(floydWarshallPath[indexPrevious][indexStartingPoint]));
        remainingBattery -= calculateEnergyCostBetweenTwoPoints(previous, startingPointV, floydWarshallPath, floydWarshallDistance);
        remainingPercentage = (remainingBattery * 100) / scooterDeliverer.getMaxBatteryCapacity();
        scooterDeliverer.setStateOfCharge(remainingPercentage);
        ApplicationPOT.getInstance().getPlatform().getScooterDB().updateScooter(scooterDeliverer);
    }

    private Vertex getNearestPharmacy(Vertex previous, double[][] floydWarshallDistance, String[][] floydWarshallPath) {
        int indexPrevious = deliveryMap.vertices().indexOf(previous);
        double minDist = Double.MAX_VALUE;
        Vertex toReturn = null;
        int i = 0;
        while (i <= prox.getNumPharmacies()) {
            Vertex v = deliveryMap.vertices().get(i);
            if (floydWarshallDistance[indexPrevious][i] < minDist) {
                minDist = floydWarshallDistance[indexPrevious][i];
                toReturn = v;
            }
            i++;
        }
        return toReturn;
    }

    private Vertex getShortestNext(Vertex previous, List<Vertex> copy, double[][] floydWarshallDistance, String[][] floydWarshallPath) {
        int indexPrevious = deliveryMap.vertices().indexOf(previous);
        double minDist = Double.MAX_VALUE;
        Vertex toReturn = null;
        for (Vertex v : copy) {
            int indexV = deliveryMap.vertices().indexOf(v);
            if (floydWarshallDistance[indexPrevious][indexV] < minDist) {
                minDist = floydWarshallDistance[indexPrevious][indexV];
                toReturn = v;
            }
        }
        return toReturn;
    }

    private double calculateEnergyCostBetweenTwoPoints(Vertex a, Vertex b, String[][] floydWarshallPath, double[][] floydWarshallDistance) {
        double energyCost = 0;
        int indexA = deliveryMap.vertices().indexOf(a);
        int indexB = deliveryMap.vertices().indexOf(b);
        List<Vertex> returned = stringTreatment(floydWarshallPath[indexA][indexB]);
        if (isDistance) {
            if (returned.size() == 1) {
                Edge temp = deliveryMap.getEdge(a, b);
                energyCost += RegisterScooter.energyWastedBetweenTwoPoints(a, b, temp, totalWeight, temp.getWindDirection());
            } else {
                for (int i = 1; i < returned.size(); i++) {
                    Edge temp = deliveryMap.getEdge(returned.get(i - 1), returned.get(i));
                    energyCost += RegisterScooter.energyWastedBetweenTwoPoints(returned.get(i - 1), returned.get(i), temp, totalWeight, temp.getWindDirection());
                }
            }
        } else {
            if (returned.size() == 1) {
                Edge temp = deliveryMap.getEdge(a, b);
                energyCost += temp.getEnergyCost();
            } else {
                for (int i = 1; i < returned.size(); i++) {
                    Edge temp = deliveryMap.getEdge(returned.get(i - 1), returned.get(i));
                    energyCost += temp.getEnergyCost();
                }
            }
        }
        return ((double) Math.round((energyCost) * 1000d) / 1000d);
    }

    protected List<Vertex> stringTreatment(String path) {
        List<Vertex> toDeliver = new ArrayList<>();
        if (path != null && path != "Direct path") {
            String[] split = path.split(";");
            for (int i = 0; i < split.length; i++) {
                toDeliver.add(deliveryMap.vertices().get(Integer.parseInt(split[i])));
            }
            return toDeliver;
        }
        return null;
    }

    /*
    protected List<List<Vertex>> getPathsWithAllAdresses (String[][] paths, int startingPoint){
        List<List<Vertex>> toReturn = new ArrayList<>();
        int addressCounter = 0;
        for(int j = 0; j < deliveryMap.getNumVertices(); j++){
            List<Vertex> atIndex = stringTreatment(paths[startingPoint][j]);
            if(atIndex != null) {
                for (Vertex a : addressesV) {
                    if (atIndex.contains(a)) {
                        addressCounter++;
                    }
                }
                if (addressCounter == addressesV.size()) {
                    toReturn.add(atIndex);
                }
            }
            addressCounter = 0;
        }
        return toReturn;
    }



    protected List<Vertex> getSmallestPath(List<List<Vertex>> pathsContainingAddresses) {
        List<Pair<List<Vertex>, Double>> tempList = new ArrayList<>();
        double tempDistance = 0;
        for(List<Vertex> lV : pathsContainingAddresses){
            tempDistance = calculatePathSize(lV);
            tempList.add(Pair.createPair(lV, tempDistance));
        }
        Collections.sort(tempList, (p1, p2) -> p1.getElement1().compareTo(p2.getElement1()));
        return tempList.get(0).getElement0();
    }

     */

    private double calculatePathSize() {
        double distance = 0;
        for (int i = 1; i < route.size(); i++) {
            Vertex orig = route.get(i - 1);
            Vertex dest = route.get(i);
            Edge temp = deliveryMap.getEdge(orig, dest);
            distance += temp.getWeight();
        }
        setDistance((double) Math.round((distance) * 1000d) / 1000d);
        return ((double) Math.round((distance) * 1000d) / 1000d);
    }

    public double getEnergyCost() {
        if (energyCost == 0.0) {
            return calculateEnergyCostOfRoute();
        } else {
            return energyCost;
        }
    }

    public AdjacencyMatrixGraph getDeliveryMap() {
        return deliveryMap;
    }

}
