public class Edge {

	public Vertex source;
	public Vertex destination;
	int weight;
	Edge next;
	
	public Edge(Vertex source, Vertex destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

}
