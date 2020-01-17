/*
  @author: Kenneth Englisch, Timo Schmidt, Tony Schultze
 * @version: 2019-01-09
 * Getting from A to B
 */
import java.util.*;

public class Graph implements WeightedGraph {

	private Random rand = new Random();
	private adjList[] arrayVertex;
	private List<Integer> allVertices = new ArrayList<>();
	int v;

	public Graph(int vertices, int edges, boolean random) {

		// Create Array of type adjList with the same size as the amount of vertices
		arrayVertex = new adjList[edges];
		v = vertices;

		// Create another list inside of the Array
		// Set head to null
		for (int i = 0; i < edges; i++) {
			arrayVertex[i] = new adjList();
			arrayVertex[i].head = null;
		}
		generateRandomGraph(vertices, edges);
	}

	public static void main(String[] args) {
		Graph graph = new Graph(9, 13, false);
		graph.printGraph();
		graph.selectRandomStartEnd(graph);

		//graph.getCheapestPath(A, I, graph.arrayVertex);
		//graph.getShortestPath(A, C, graph.arrayVertex);

	}

	public void generateRandomGraph(int vertices, int edges) {
			// Create new List from 0 to 25 (for A to Z), shuffle it and cut it to the desired size
	        // (so we have random vertices)
	        allVertices = new ArrayList<>();
	        for (int i = 0; i < edges; i++)
	            allVertices.add(i);
	        Collections.shuffle(allVertices);
	        allVertices.subList(vertices, allVertices.size()).clear();
	        
	        // Add all vertices
	        for (Integer a : allVertices)
	        	addVertex(a);
	        
	        int counter = 0;
	        while (counter < edges) {
	            // Random number between 1 and vertices
	            int randSrc = rand.nextInt(allVertices.size());
	            int randDst = rand.nextInt(allVertices.size());
	            while(randSrc == randDst)
	            	randDst = rand.nextInt(allVertices.size());
	            int weight = rand.nextInt(10 - 1) + 1;
	            
	            Vertex src = new Vertex(0,0,false);
	            src.data = allVertices.get(randSrc);
	            Vertex dst = new Vertex(0,0,false);
	            dst.data = allVertices.get(randDst);
	            	
	            addEdge(src,dst,weight);
	            counter++;
	        }
	}
	
	public void addVertex(int v) {
		Vertex vert = new Vertex(v,0, false);
	}
	
	public void addEdge(Vertex src, Vertex dest, int weight) {
		// Create a new edge with source/destination/weight
		Edge edge = new Edge(src, dest, weight);

		// add this node to the adjList
		edge.next = arrayVertex[src.data].head;
		arrayVertex[src.data].head = edge;
	}
	
	public void selectRandomStartEnd(Graph graph) 
    {
    	int size = graph.allVertices.size();
    	
    	// random between 1 and size
    	int start = rand.nextInt(size);
    	int end = rand.nextInt(size);
    	
    	while(end == start) 
    		end = rand.nextInt(size);
    	
    	Vertex start_vertex = new Vertex(0,0,false);
    	start_vertex.data = graph.allVertices.get(start);
    	Vertex end_vertex = new Vertex(0,0,false);
    	end_vertex.data = graph.allVertices.get(end);
    	
    	System.out.println("Random Vertices");
    	System.out.println("--> Start Vertex: " + numberToChar(start_vertex.data));
    	System.out.println("--> End Vertex: " + numberToChar(end_vertex.data) + "\n");
    	
    	
    	System.out.println("Path between start and end vertex");
    	if(getCheapestPath(start_vertex, end_vertex) == null)
    		System.out.println("--> There is no path between '" + numberToChar(start_vertex.data) + "' and '" + numberToChar(end_vertex.data) + "'.");
    	else
    		System.out.println("--> The shortest path from '" + numberToChar(start_vertex.data) + "' to '" +numberToChar(end_vertex.data) + "' is: " + getCheapestPath(start_vertex, end_vertex));
    }

	public ArrayList<Vertex> getCheapestPath(Vertex source, Vertex destination) {
		Dijkstra d = new Dijkstra(source, destination, arrayVertex);
		return d.cheapestPath(source, destination);
	}

	public void getShortestPath(Vertex source, Vertex destination, adjList arrayVertex[]) {
		Dijkstra d = new Dijkstra(source, destination, arrayVertex);
	}

	public void printGraph() {
		int vertex = v;
		Edge edge;
		for (int i = 0; i < vertex; i++) {
			edge = arrayVertex[i].head;
			if (edge != null) {
				System.out.println("Vertex " + numberToChar(edge.source.data) + " is connected to:");
				while (edge != null) {
					System.out.print("   " + (numberToChar(edge.destination.data) + " (Weight:" + edge.weight + ")"));
					edge = edge.next;
				}
				System.out.println();
			}
		}
	}

	// This methods returns a 0 for 'A' and 25 for 'Z'
	public int charToNumber(char c) {
		return (int) c - 65;
	}

	// and vice versa
	public char numberToChar(int n) {
		return (char) (n + 65);
	}
}
