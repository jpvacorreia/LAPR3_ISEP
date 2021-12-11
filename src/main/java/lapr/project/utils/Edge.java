package lapr.project.utils;

import lapr.project.model.RegisterDrone;
import lapr.project.model.RegisterScooter;

/**
 * 
 * @author DEI-ESINF
 */

public class Edge{
    
    private String streetName;           // Edge information
    private double weight;       // Edge weight
    private Vertex vOrig;  // vertex origin
    private Vertex vDest;  // vertex destination
    private double energyCost;
    boolean isDrone;
    private int windDirection;
    
    public Edge() {
        streetName = null; weight = 0.0; vOrig=null; vDest=null; }
    
    public Edge(String eInf, double ew, Vertex vo, Vertex vd, boolean isDrone, int windDirection) {
        streetName = eInf; this.weight= ew; vOrig=vo; vDest=vd;
        this.isDrone = isDrone;
        this.windDirection = windDirection;
    }
    public Edge(String eInf, double ew, Vertex vo, Vertex vd) {
        streetName = eInf; weight= ew; vOrig=vo; vDest=vd;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public double getWeight() { return weight; }
    public void setWeight(double ew) { weight= ew; }
    
    public Vertex getVOrig() {
        if (this.vOrig != null) 
            return vOrig;
        return null;
    }	 
    public void setVOrig(Vertex vo) { vOrig= vo; }
    
    public Vertex getVDest() {
        if (this.vDest != null) 
            return vDest;
        return null; 
    }
    public void setVDest(Vertex vd) { vDest= vd; }

    public void setEnergyCost(double weight) {
        if(!isDrone){
            this.energyCost = RegisterScooter.energyWastedBetweenTwoPoints(this.vOrig, this.vDest, this, weight, this.windDirection);
        }else{
            this.energyCost = RegisterDrone.energyWastedBetweenTwoPointsD(this.weight, weight, this.windDirection);
        }
    }

    public double getEnergyCost() {
        return energyCost;
    }

    @Override
    public Edge clone() {
        
        Edge newEdge = new Edge();
        
        newEdge.streetName = streetName;
        newEdge.weight = weight;
        newEdge.vOrig = vOrig;
        newEdge.vDest = vDest;
        
        return newEdge;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "streetName='" + streetName + '\'' +
                ", weight=" + weight +
                ", vOrig=" + vOrig +
                ", vDest=" + vDest +
                '}';
    }

    public int getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }
}
