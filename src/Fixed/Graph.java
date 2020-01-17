package Fixed;

import java.util.*;

public class Graph implements WeightedGraph {

    // LinkedList Array
    LinkedList<Edge> adjList[];

    // Number of vertices & edges
    int V;

    // Random
    Random random = new Random();


    /**
     * Constructor
     * @param vertices - number of vertices
     */
    public Graph(int vertices) {

        V = vertices;

        // Create Array of LL
        adjList = new LinkedList[vertices];

        // Create (empty) LL for each vertex
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public Graph(int vertices, int edges) {

        V = vertices;

        // Create Array of LL
        adjList = new LinkedList[vertices];

        // Create (empty) LL for each vertex
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }

        // Create Random Vertices
        List<Character> VERTICES = new ArrayList<>();

        // Loop for creating the right number of vertices
        while (VERTICES.size() != vertices) {

            String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            char randomLetter = alphabet.charAt(random.nextInt(alphabet.length()));

            if (!VERTICES.contains(randomLetter))
                VERTICES.add(randomLetter);
        }

        // Add all used letters to new String
        String used = "";
        for (char c : VERTICES)
            used += c;

        System.out.println(VERTICES);

        for (int i = 0; i < vertices; i++) {

            char randomLetter_1 = used.charAt(random.nextInt(used.length()));
            char randomLetter_2 = used.charAt(random.nextInt(used.length()));
            int randomWeight = random.nextInt(10-1)+1;

            addEdge(i, randomLetter_1, randomLetter_2, randomWeight);

        }
    }

    public void addEdge(char src, char dest, int weight) {
        // Transform char in int using the method below
        int source = charToNumber(src);

        // Add edge to beginning of list
        adjList[source].addFirst(new Edge(src, dest, weight));
    }

    public void addEdge(int num, char src, char dest, int weight) {
        // Transform char in int using the method below
        int source = charToNumber(src);

        // Add edge to beginning of list
        adjList[num].addFirst(new Edge(src, dest, weight));
    }

    // This methods returns a 1 for 'A' and 26 for 'Z'
    public int charToNumber(char c) {
        return (int) c - 64;
    }

    // and vice versa
    public char numberToChar(int n) {
        return (char) (n + 64);
    }


    public void findShortestPath(char src, char dest) {

/*        int infinite = Integer.MAX_VALUE;
        boolean[] SPT = new boolean[V];

        // Create HeapNodes, set Vertex to the respective value and distance to infinity
        HeapNode[] heapNodes = new HeapNode[V];
        for (int i = 0; i < V; i++) {
            heapNodes[i] = new HeapNode();
            heapNodes[i].vertex = i;
            heapNodes[i].distance = infinite;
        }

        // Decrease the distance for the first index
        heapNodes[charToNumber(src)].distance = 0;

        // add all vertices to MinHeap
*/
    }

    public void findCheapestPath(char src, char dest) {

    }

    // Method to print the graph
    public void print() {

        // iterate through all vertices
        for (int i = 0; i < V; i++) {
            if(adjList[i].size() > 0) {
                System.out.print("Vertex " + numberToChar(i) + " is connected to: ");
                for (int j = 0; j < adjList[i].size(); j++) {
                    System.out.print(adjList[i].get(j).destination + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addEdge('A', 'B', 2);
        graph.addEdge('A', 'F', 9);
        graph.addEdge('A', 'D', 4);
        graph.addEdge('B', 'C', 2);
        graph.addEdge('B', 'D', 5);
        graph.addEdge('C', 'E', 4);
        graph.addEdge('D', 'E', 6);
        graph.addEdge('D', 'G', 1);
        graph.addEdge('F', 'G', 6);
        graph.addEdge('G', 'H', 2);
        graph.print();

        System.out.println();

        Graph gr = new Graph(5, 10);
        gr.print();

    }

}
