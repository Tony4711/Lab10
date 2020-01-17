package Fixed;

public interface WeightedGraph {

    void addEdge(char src, char dest, int weight);

    void findShortestPath(char src, char dest);
    void findCheapestPath(char src, char dest);

}
