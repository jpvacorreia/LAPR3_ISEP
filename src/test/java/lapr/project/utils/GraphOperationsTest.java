package lapr.project.utils;

import lapr.project.utils.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphOperationsTest {

    AdjacencyMatrixGraph testGraph;

    @BeforeEach
    void setUp(){
        testGraph = new AdjacencyMatrixGraph();
        Pair<Double, Double> p1 = Pair.createPair(10.0,10.0);
        Pair<Double, Double> p2 = Pair.createPair(15.0,15.0);
        Pair<Double, Double> p3 = Pair.createPair(20.0,20.0);
        Pair<Double, Double> p4 = Pair.createPair(25.0,25.0);
        Vertex v1 = new Vertex(0, p1, 85.0);
        Vertex v2 = new Vertex(1, p2, 85.0);
        Vertex v3 = new Vertex(2, p3, 85.0);
        Vertex v4 = new Vertex(3, p4, 85.0);
        Edge e1 = new Edge("N/A", 2.0, v1,v2);
        Edge e2 = new Edge("N/A", 3.0, v2, v3);
        Edge e3 = new Edge("N/A", 4.0, v3, v4);
        Edge e4 = new Edge("N/A", 10.0, v1, v4);
        testGraph.insertVertex(v1);
        testGraph.insertVertex(v2);
        testGraph.insertVertex(v3);
        testGraph.insertVertex(v4);
        testGraph.insertEdge(v1, v2, e1);
        testGraph.insertEdge(v2, v3, e2);
        testGraph.insertEdge(v3, v4, e3);
        testGraph.insertEdge(v1,v4, e4);
    }

    @Test
    void floydWarshallTest(){
        Pair<double[][], String[][]> result = GraphOperations.floydWarshall(testGraph);
        double expectedResultAt03 = 9.0;
        String expectedResultAt03String = "1;2;3;";
        assertEquals(expectedResultAt03,result.getElement0()[0][3]);
        assertEquals(expectedResultAt03String,result.getElement1()[0][3]);
    }

    @Test
    void floydWarshallEnergyTest() {
        for(Edge e : testGraph.edges()){
            e.setEnergyCost(80);
        }
        Pair<double[][], String[][]> result = GraphOperations.floydWarshallEnergy(testGraph);
        double expectedResultAt03 = 1453.5240;
        String expectedResultAt03String = "1;2;3;";
        assertEquals(expectedResultAt03,result.getElement0()[0][3], 0.1);
        assertEquals(expectedResultAt03String,result.getElement1()[0][3]);
    }

    @Test
    void calculateDistanceTest() {
        Pair<Double, Double> firstPair = Pair.createPair(-34.6131500, -58.3772300);
        Pair<Double, Double> secondPair = Pair.createPair(-16.5000000, -68.1500000);
        double expResult = 2237.0;
        double result = GraphOperations.calculateDistance(firstPair, secondPair);
        assertEquals(expResult, result, 1);
    }
}