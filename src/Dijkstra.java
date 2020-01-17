import java.util.*;

public class Dijkstra {

	private Vertex source;
	private Vertex destination;
	private adjList[] v;
	private ArrayList<Vertex> path;

	public Dijkstra(Vertex source, Vertex destination, adjList[] vertices) {
		this.source = source;
		this.destination = destination;
		this.v = vertices;
	}

	public void setAmount(Vertex vert) {
		Edge current = null;
		current = v[vert.data].head;
		while (current != null) {
			if (!current.destination.visited) {
				current.destination.amount = current.source.amount + current.weight;
				current.destination.visited = true;
				current = current.next;
			} else {
				if (current.destination.amount < current.source.amount + current.weight) {
					path.remove(path.size() - 1);
				} else {
					current.destination.amount = current.source.amount + current.weight;
				}
				current.destination.visited = true;
				current = current.next;
			}
		}
	}

	public Vertex getNeighbour(Vertex source) {
		Edge previous = null;
		Edge current = null;
		Edge smallest = null;
		Edge finalEdge = null;
		// select the edges from the wanted vertex out of the array
		current = v[source.data].head;
		setAmount(source);

		while (current != null) {
			// previous is null at the first run or when we only have one edge so there is
			// nothing to compare
			if (previous != null) {
				if (Double.compare(previous.destination.amount, current.destination.amount) < 0) {
					smallest = previous;
				} else {
					smallest = current;
					previous = current;
				}
				// if we had no previous edge the first edge becomes the previous edge
			} else {
				previous = current;
			}
			// get the next edge from the adjList
			current = current.next;
		}
		/*
		 * smallest could be null when we only had one edge since then nothing got
		 * compared if this happens we return the only selected edge which is stored in
		 * previous
		 */
		if (smallest != null) {
			finalEdge = smallest;
		} else if (previous != null) {
			finalEdge = previous;
		}
		if (finalEdge != null) {
			return finalEdge.destination;
		} else
			return null;
	}

	public ArrayList<Vertex> cheapestPath(Vertex source, Vertex destination) {
		path = new ArrayList<Vertex>();
		path.add(source);
		/*System.out.println("Find path from " + ((char) ('A' + ((int) source.data))) + " to "
				+ ((char) ('A' + ((int) destination.data))));*/
		this.destination = destination;
		while (source != destination) {
			try {
				source = getNeighbour(source);
				path.add(source);
			} catch (NullPointerException e) {
				//System.out.println("No path found");
				return null;
			}
		}
		return path;
	}

	public void shortestPath(Vertex source, Vertex destination, adjList vertices[]) {
	}

	public char numberToChar(int n) {
		return (char) (n + 65);
	}

}
