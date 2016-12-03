import java.util.*;
import java.io.*;
public class DFSAlgo {
    public static void main(String[] args) {
        File file = new File("../final_project/2.in");
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (Exception e) {
            System.out.println(e);
        }

        ArrayList<GraphNode> vertices = new ArrayList<GraphNode>();
        HashSet<GraphNode> visited = new HashSet<GraphNode>();
        HashSet<GraphNode> available = new HashSet<GraphNode>();
        
        int v = Integer.parseInt(input.nextLine());
        int[][] matrix = new int[v][v];
        for (int i = 0; i < v; i++) {
            String[] nextLine = input.nextLine().split(" ");
            for (int j = 0; j < v; j++) {
                matrix[i][j] = Integer.parseInt(nextLine[j]);
            }
        }

        for (int i = 0; i < v; i++) {
            GraphNode newVertex = new GraphNode(matrix[i][i], i);
            vertices.add(newVertex);
            available.add(newVertex);
        }

        for (int i = 0; i < v; i++) {
            int[] neighbors = matrix[i];
            for (int j = 0; j < neighbors.length; j++) {
                if (j != i && neighbors[j] == 1) {
                    vertices.get(i).addNeighbor(vertices.get(j));
                }
            }
        }
        
        ArrayList<Integer> vertexIndices = new ArrayList<Integer>();
        for (int i = 0; i < v; i++) {
            vertexIndices.add(i);
        }

        Collections.shuffle(vertexIndices);
        int minPaths = 10000;

        for (int i = 0; i < 250000; i++) {
            int firstIndex = 0;
            int numPaths = 0;

            
            while (!available.isEmpty()) {
                int indexToCheck = vertexIndices.get(firstIndex);
                
                GraphNode toSearch = vertices.get(indexToCheck);
                if (!visited.contains(toSearch)) {
                    GraphNode.DFS(toSearch, visited, available);
                    // System.out.println();
                    numPaths += 1;
                }

                firstIndex ++;
            }
            // System.out.println(numPaths);

            visited = new HashSet<GraphNode>();
            available = new HashSet<GraphNode>();
            for (GraphNode vi : vertices) {
                available.add(vi);
            }

            Collections.shuffle(vertexIndices);
            if (minPaths > numPaths)
                minPaths = numPaths;
        }

        System.out.println(minPaths);
        input.close();
    }
}
