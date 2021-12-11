package lapr.project.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author DEI-ESINF
 */

public class Vertex {

    private int key ;
    private Pair<Double, Double> coordinates;
    private double altitude;
    private Map<Vertex, Edge> outVerts; //adjacent vertices
    private String name;
       
    public Vertex () { 
        key = -1; coordinates = null; outVerts = new LinkedHashMap<>(); altitude = -1;}
    
    public Vertex (int k, Pair<Double,Double> coordinates, double altitude) {
        key = k; this.coordinates = coordinates; this.altitude = altitude; outVerts = new LinkedHashMap<>(); }
    
    public Vertex (Vertex v) {
        key = v.getKey(); this.coordinates = v.getCoordinates(); this.altitude = v.getAltitude();
        outVerts = new LinkedHashMap<>(); 
        for (Vertex vert : v.outVerts.keySet()){
            Edge edge = v.outVerts.get(vert);
            outVerts.put(vert, edge);
        }
    }

    public Pair<Double, Double> getCoordinates() {
        return coordinates;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setCoordinates(Pair<Double, Double> coordinates) {
        this.coordinates = coordinates;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKey() { return key; }
    public void setKey(int k) { key = k; }

    public void addAdjVert(Vertex vAdj, Edge edge){ outVerts.put(vAdj, edge); }
    
    public Vertex getAdjVert(Edge edge){
        
        for (Vertex vert : outVerts.keySet())
            if (edge.equals(outVerts.get(vert)))
                return vert; 
        
        return null;
    }
    
    public void remAdjVert(Vertex vAdj){ outVerts.remove(vAdj); }
    
    public Edge getEdge(Vertex vAdj){ return outVerts.get(vAdj); }
    
    public int numAdjVerts() { return outVerts.size();}
    
    public Iterable<Vertex> getAllAdjVerts() {  return outVerts.keySet(); }
    
    public Iterable<Edge> getAllOutEdges() {  return outVerts.values(); }
     
    @Override
    public Vertex clone() {
        
        Vertex newVertex = new Vertex();
        
        newVertex.setKey(key); 
        newVertex.setCoordinates(coordinates);
        newVertex.setAltitude(altitude);
        
        for (Vertex vert : outVerts.keySet())
            newVertex.addAdjVert(vert, this.getEdge(vert));

        return newVertex;
    }

    @Override
    public boolean equals(Object o){
        // self check
        if (this == o)
            return true;
        // null check
        if(o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        Vertex v = (Vertex) o;
        // field comparison
        return getCoordinates().equals(v.getCoordinates());
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "key=" + key +
                ", coordinates=" + coordinates +
                ", altitude=" + altitude +
                '}';
    }
}
