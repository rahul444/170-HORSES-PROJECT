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
                    HashSet<GraphNode> available,
                    ArrayList<Integer> team) {
    
    // System.out.println(start.value);
    team.add(start.index);
    visited.add(start);
    available.remove(start);
    
    for (GraphNode v : start.neighbors) {
      if (!visited.contains(v)) {
        visited.add(v);
        available.remove(v);
        DFS(v, visited, available, team);
      }
    }
  }

  public static int teamValue(ArrayList<Integer> team) {
    int sum = 0;
    for (int i : team) {
      sum += i;
    }
    return sum * team.size();
  }

  public static int valueOfSolution(ArrayList<ArrayList<Integer>> sol) {
    int sum = 0;
    for (ArrayList<Integer> j : sol) {
      sum += GraphNode.teamValue(j);
    }

    return sum;
  }
}