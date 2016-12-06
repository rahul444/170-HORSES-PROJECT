import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;
import java.lang.Comparable;

public class GraphNode implements Comparable<GraphNode> {
  public int value;
  public int index;
  ArrayList<GraphNode> neighbors;

  public GraphNode(int value, int index) {
    this.value = value;
    this.index = index;
    this.neighbors = new ArrayList<GraphNode>();
  }

  public int compareTo(GraphNode n) {
    return this.value - n.value;
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

    ArrayList<GraphNode> topNeighbors = new ArrayList<GraphNode>();
    Collections.sort(start.neighbors);

    int j = 0;
    for (int i = start.neighbors.size() - 1; i >= 0; i--) {
      GraphNode p = start.neighbors.get(i);
      if (!visited.contains(p) && j < 5) {
        topNeighbors.add(p);
        j++;
      }

      if (j >= 5)
        break;
    }

    Collections.shuffle(topNeighbors);

    for (GraphNode v : topNeighbors) {
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