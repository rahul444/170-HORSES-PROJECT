import java.util.*;
import java.io.*;
public class DFSAlgo {
    public static void main(String[] args) {
        File file = new File("./in/13.in");
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (Exception e) {
            System.out.println(e);
        }
        ArrayList<GraphNode> vertices = new ArrayList<GraphNode>();
        int v = Integer.parseInt(input.nextLine());
        int[][] matrix = new int[v][v];
        for (int i = 0; i < v; i++) {
            String[] nextLine = input.nextLine().split(" ");
            for (int j = 0; j < v; j++) {
                matrix[i][j] = Integer.parseInt(nextLine[j]);
            }
        }

        for (int i = 0; i < v; i++) {
            vertices.add(new GraphNode(matrix[i][i]));
        }

        for (int i = 0; i < v; i++) {
            int[] neighbors = matrix[i];
            for (int j = 0; j < neighbors.length; j++) {
                if (j != i && neighbors[j] == 1) {
                    vertices.get(i).addNeighbor(vertices.get(j));
                }
            }
        }

        GraphNode.DFS(vertices.get(3), new HashSet<GraphNode>());

        input.close();
    }
}
