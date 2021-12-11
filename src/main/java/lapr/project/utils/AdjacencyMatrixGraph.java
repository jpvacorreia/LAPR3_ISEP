
package lapr.project.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author DEI-ESINF
 */

public class AdjacencyMatrixGraph implements Cloneable{

    public static final int INITIAL_CAPACITY = 10;
    public static final float RESIZE_FACTOR = 1.5F;

    int numVertices;
    int numEdges;
    ArrayList<Vertex> vertices;
    Edge[][] edgeMatrix;

    public Edge getXY(int x, int y){
        return edgeMatrix[x][y];
    }


    /**
     * Returns the index associated with a vertex
     *
     * @param vertex
     * @return vertex index, -1 if vertex does not exist in the graph
     */

    int toIndex(Vertex vertex) {
        return vertices.indexOf(vertex);
    }

    /**
     * Resizes the matrix when a new vertex increases the length of ArrayList
     */
    private void resizeMatrix() {
        if (edgeMatrix.length < numVertices) {
            int newSize = (int) (edgeMatrix.length * RESIZE_FACTOR);

            @SuppressWarnings("unchecked")
            Edge[][] temp = new Edge[newSize][newSize];
            for (int i = 0; i < edgeMatrix.length; i++)
                temp[i] = Arrays.copyOf(edgeMatrix[i], newSize);

            edgeMatrix = temp;
        }
    }

    /**
     * Constructs an empty graph.
     */
    public AdjacencyMatrixGraph() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Constructs a graph with an initial capacity.
     */
    @SuppressWarnings("unchecked")
    public AdjacencyMatrixGraph(int initialSize) {
        vertices = new ArrayList<Vertex>(initialSize);

        edgeMatrix = new Edge[initialSize][initialSize];
    }

    public int getNumVertices() {
        return numVertices;
    }

    /**
     * Returns the number of vertices in the graph
     *
     * @return number of vertices of the graph
     */
    public int numVertices() {
        return numVertices;
    }

    /**
     * Returns the number of edges in the graph
     *
     * @return number of edges of the graph
     */
    public int numEdges() {
        return numEdges;
    }

    /**
     * Checks if a vertex exist
     *
     * @param vertex
     * @return true if exists
     */
    public boolean checkVertex(Vertex vertex) {
        return (vertices.indexOf(vertex) != -1);
    }

    /**
     * Returns the actual vertices of the graph
     *
     * @return an iterable collection of vertices
     */
    public List<Vertex> vertices() {
        return vertices;
    }

    /**
     * Returns the actual edges of the graph
     *
     * @return an iterable collection of all edges
     */
    public List<Edge> edges() {
        List<Edge> edges = new ArrayList<Edge>();

        // graph is undirected, so only return a single copy of edge
        // graph could actually only keep one copy of the edge but algorithms
        // would then need to consider that case.

        for (int i = 0; i < numVertices - 1; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (edgeMatrix[i][j] != null)
                    edges.add(edgeMatrix[i][j]);
                if (edgeMatrix[j][i] != null)
                    edges.add(edgeMatrix[j][i]);
            }
        }
        return edges;
    }


    /**
     * Returns the edge between two vertices
     *
     * @param vertexA
     * @param vertexB
     * @return the edge or null if source and dest are not adjacent or do not
     * exist in the graph.
     */
    public Edge getEdge(Vertex vertexA, Vertex vertexB) {
        int indexA = toIndex(vertexA);
        if (indexA == -1)
            return null;

        int indexB = toIndex(vertexB);
        if (indexB == -1)
            return null;

        return edgeMatrix[indexA][indexB];
    }


    /**
     * Inserts a new vertex with the given element.
     * fails if vertex already exists
     *
     * @param newVertex (vertex contents)
     * @return false if vertex exists in the graph
     */
    public boolean insertVertex(Vertex newVertex) {
        int index = toIndex(newVertex);
        if (index != -1)
            return false;
        vertices.add(newVertex);
        numVertices++;
        resizeMatrix();
        return true;
    }

    /**
     * Inserts a new edge between two vertices.
     * Package level method is for use of algorithms class PRECISO MUDAR
     *
     * @param indexA
     * @param indexB
     * @param newEdge
     * @return false if vertices are not in the graph or are the same vertex
     * or an edge already exists between the two.
     */
    void insertEdge(int indexA, int indexB, Edge newEdge) {
        if (edgeMatrix[indexA][indexB] == null) {
            edgeMatrix[indexA][indexB] = newEdge;
            numEdges++;
        }
    }

    public boolean insertEdge(Vertex vertexA, Vertex vertexB, Edge newEdge) {

        if (vertexA.equals(vertexB))
            return false;

        int indexA = toIndex(vertexA);
        if (indexA == -1)
            return false;

        int indexB = toIndex(vertexB);
        if (indexB == -1)
            return false;

        if (edgeMatrix[indexA][indexB] != null)
            return false;

        insertEdge(indexA, indexB, newEdge);

        return true;
    }

    /**
     * Removes a vertex and all its incoming/outgoing edges from the graph.
     *
     * @param vertex vertex
     * @return false if vertex does not exist in the graph
     */
    public boolean removeVertex(Vertex vertex) {
        int index = toIndex(vertex);
        if (index == -1)
            return false;

        // first let's remove edges to/from the vertex

        for (int i = 0; i < numVertices; i++)
            if (edgeMatrix[index][i] != null) {
                removeEdge(index, i);
            } else
            if (edgeMatrix[i][index] != null) {
                removeEdge(i, index);
            }


        vertices.remove(index);
        numVertices--;

        // remove shifts left all vertices after the one removed
        // It is necessary to collapse the edge matrix

        //first the lines after line vertex removed
        for (int i = index; i < numVertices; i++)
            for (int j = 0; j < edgeMatrix.length; j++)
                edgeMatrix[i][j] = edgeMatrix[i + 1][j];

        for (int j = 0; j < edgeMatrix.length; j++)
            edgeMatrix[numVertices][j] = null;

        //second the columns after column vertex removed
        for (int i = index; i < numVertices; i++)
            for (int j = 0; j < edgeMatrix.length; j++)
                edgeMatrix[j][i] = edgeMatrix[j][i + 1];

        for (int j = 0; j < edgeMatrix.length; j++)
            edgeMatrix[j][numVertices] = null;

        return true;
    }

    /**
     * Removes the edge between two vertices
     * Package level method is for use of algorithms class
     *
     * @param indexA
     * @param indexB
     * @return the edge or null if vertices are not in the graph or not
     * connected
     */
    Edge removeEdge(int indexA, int indexB) {
        Edge edge = edgeMatrix[indexA][indexB];
        edgeMatrix[indexA][indexB] = null;
        numEdges--;
        return edge;
    }

    public Edge removeEdge(Vertex vertexA, Vertex vertexB) {
        int indexA = toIndex(vertexA);
        if (indexA == -1)
            return null;

        int indexB = toIndex(vertexB);
        if (indexB == -1)
            return null;

        return removeEdge(indexA, indexB);
    }
}