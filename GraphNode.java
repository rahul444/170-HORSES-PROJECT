import java.util.ArrayList;
import java.util.HashSet;

public class GraphNode {
  public int value;
  ArrayList<GraphNode> neighbors;

  public GraphNode(int value) {
    this.value = value;
    this.neighbors = new ArrayList<GraphNode>();
  }

  public void addNeighbor(GraphNode v) {
    this.neighbors.add(v);
  }

  public String toString() {
    String neighbors = "";
    for (GraphNode i : this.neighbors) {
      neighbors += (i.value + " "); 
    }
    return "Value: " + this.value + ", Neighbors:" +  neighbors;
  }

  public static void DFS(GraphNode start, HashSet<GraphNode> visited) {
    System.out.println(start.value);
    visited.add(start);
    for (GraphNode v : start.neighbors) {
      if (!visited.contains(v)) {
        visited.add(v);
        DFS(v, visited);
      }
    }


  }
}