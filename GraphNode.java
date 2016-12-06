import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;

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
                    HashSet<GraphNode> available,
                    ArrayList<GraphNode> team) {
    
    team.add(start);
    visited.add(start);
    available.remove(start);

    Collections.shuffle(start.neighbors);
    for (GraphNode v : start.neighbors) {
      if (!visited.contains(v)) {
        visited.add(v);
        available.remove(v);
        DFS(v, visited, available, team);
        break;
      }
    }
  }

  public static int teamValue(ArrayList<GraphNode> team) {
    int sum = 0;
    for (GraphNode i : team) {
      sum += i.value;
    }
    return sum * team.size();
  }

  public static int valueOfSolution(ArrayList<ArrayList<GraphNode>> sol) {
    int sum = 0;
    for (ArrayList<GraphNode> j : sol) {
      sum += GraphNode.teamValue(j);
    }

    return sum ;
  }
}