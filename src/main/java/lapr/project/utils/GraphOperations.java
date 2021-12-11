package lapr.project.utils;

import lapr.project.utils.AdjacencyMatrixGraph;
import lapr.project.utils.Pair;

public class GraphOperations {

    public static Pair<double[][], String[][]> floydWarshall(AdjacencyMatrixGraph graph)
    {
        double[][] dist = new double[graph.getNumVertices()][graph.getNumVertices()];
        int i, j, k;
        String[][] followedPaths = new String[graph.getNumVertices()][graph.getNumVertices()];
        /* Initialize the solution matrix same as input graph matrix.
           Or we can say the initial values of shortest distances
           are based on shortest paths considering no intermediate
           vertex. */
        for (i = 0; i < graph.getNumVertices(); i++)
            for (j = 0; j < graph.getNumVertices(); j++)
                if(graph.getXY(i,j) != null){
                    dist[i][j] = graph.getXY(i,j).getWeight();
                    followedPaths[i][j] = j + ";";
                }else{
                    dist[i][j] = Double.MAX_VALUE;
                }
        /* Add all vertices one by one to the set of intermediate
           vertices.
          ---> Before start of an iteration, we have shortest
               distances between all pairs of vertices such that
               the shortest distances consider only the vertices in
               set {0, 1, 2, .. k-1} as intermediate vertices.
          ----> After the end of an iteration, vertex no. k is added
                to the set of intermediate vertices and the set
                becomes {0, 1, 2, .. k} */
        for (k = 0; k < graph.getNumVertices(); k++)
        {
            // Pick all vertices as source one by one
            for (i = 0; i < graph.getNumVertices(); i++)
            {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < graph.getNumVertices(); j++)
                {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        if(followedPaths[i][j] == null){
                            followedPaths[i][j] = followedPaths[i][k] + followedPaths[k][j];
                        }else{
                            if(followedPaths[i][j].compareTo(i + ";") == 0 || followedPaths[i][j].compareTo(j + ";") == 0 || followedPaths[i][j].compareTo(k + ";") == 0){
                                followedPaths[i][j] = "";
                            }
                            followedPaths[i][j] += followedPaths[i][k] + followedPaths[k][j];
                        }
                    }
                }
            }
        }
        followedPaths = treatPaths(followedPaths);
        Pair<double[][], String[][]> returnable = Pair.createPair(dist, followedPaths);
        return returnable;
    }

    private static String[][] treatPaths(String[][] followedPaths) {
        String finalVert;
        int index = 0;
        String path = "";
        for (int i = 0; i < followedPaths.length; i++) {
            for (int j = 0; j < followedPaths.length; j++) {
                if (followedPaths[i][j] != null) {
                    String[] split = followedPaths[i][j].split(";");
                    if (split.length > 1) {
                        finalVert = split[split.length - 1];
                        for (int k = 0; k < split.length - 1; k++) {
                            if (split[k].compareTo(finalVert) == 0) {
                                index = k;
                            }
                        }
                        if (index != 0) {
                            for (int k = index + 1; k < split.length; k++) {
                                path += split[k] + ";";
                                followedPaths[i][j] = path;
                            }
                        }
                    }
                }
                path = "";
            }
        }
        return followedPaths;
    }


    public static Pair<double[][], String[][]> floydWarshallEnergy(AdjacencyMatrixGraph graph)
    {
        double[][] dist = new double[graph.getNumVertices()][graph.getNumVertices()];
        int i, j, k;
        String[][] followedPaths = new String[graph.getNumVertices()][graph.getNumVertices()];
        /* Initialize the solution matrix same as input graph matrix.
           Or we can say the initial values of shortest distances
           are based on shortest paths considering no intermediate
           vertex. */
        for (i = 0; i < graph.getNumVertices(); i++)
            for (j = 0; j < graph.getNumVertices(); j++)
                if(graph.getXY(i,j) != null){
                    dist[i][j] = graph.getXY(i,j).getEnergyCost();
                    followedPaths[i][j] = j + ";";
                }else{
                    dist[i][j] = Double.MAX_VALUE;
                }
        /* Add all vertices one by one to the set of intermediate
           vertices.
          ---> Before start of an iteration, we have shortest
               distances between all pairs of vertices such that
               the shortest distances consider only the vertices in
               set {0, 1, 2, .. k-1} as intermediate vertices.
          ----> After the end of an iteration, vertex no. k is added
                to the set of intermediate vertices and the set
                becomes {0, 1, 2, .. k} */
        for (k = 0; k < graph.getNumVertices(); k++)
        {
            // Pick all vertices as source one by one
            for (i = 0; i < graph.getNumVertices(); i++)
            {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < graph.getNumVertices(); j++)
                {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        if(followedPaths[i][j] == null){
                            followedPaths[i][j] = followedPaths[i][k] + followedPaths[k][j];
                        }else{
                            if(followedPaths[i][j].compareTo(i + ";") == 0 || followedPaths[i][j].compareTo(j + ";") == 0 || followedPaths[i][j].compareTo(k + ";") == 0){
                                followedPaths[i][j] = "";
                            }
                            followedPaths[i][j] += followedPaths[i][k] + followedPaths[k][j];
                        }
                    }
                }
            }
        }
        followedPaths = treatPaths(followedPaths);
        Pair<double[][], String[][]> returnable = Pair.createPair(dist, followedPaths);
        return returnable;
    }

    public static double calculateDistance(Pair<Double, Double> p1, Pair<Double, Double> p2){
        double R = 6371e3; // metres
        double φ1 = p1.getElement0() * Math.PI/180; // φ, λ in radians
        double φ2 = p2.getElement0() * Math.PI/180;
        double Δφ = (p2.getElement0()-p1.getElement0()) * Math.PI/180;
        double Δλ = (p2.getElement1()-p1.getElement1()) * Math.PI/180;
        double a = Math.sin(Δφ/2) * Math.sin(Δφ/2) + Math.cos(φ1) * Math.cos(φ2) * Math.sin(Δλ/2) * Math.sin(Δλ/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c; // in metres
        return((double)Math.round((d/1000.0)*1000d)/1000d);
    }
}
