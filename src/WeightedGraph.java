import java.util.ArrayList;

public interface WeightedGraph {

    void addEdge(Vertex src, Vertex dest, int weight);
    ArrayList<Vertex> getCheapestPath(Vertex source, Vertex destination);
    void getShortestPath(Vertex source, Vertex destination, adjList arrayVertex[]);
    String toString();

}
