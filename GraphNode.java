import java.util.ArrayList;
import java.util.HashSet;

public class GraphNode {
  public int value;
  public int index;
  ArrayList<GraphNode> neighbors;

  public GraphNode(int value, int index) {
    this.value = value;
    this.index = index;
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

  public static void DFS(GraphNode start, 
                    HashSet<GraphNode> visited, 
                    HashSet<GraphNode> available) {
    
    System.out.println(start.value);
    visited.add(start);
    available.remove(start);
    
    for (GraphNode v : start.neighbors) {
      if (!visited.contains(v)) {
        visited.add(v);
        available.remove(v);
        DFS(v, visited, available);
      }
    }
  }
}