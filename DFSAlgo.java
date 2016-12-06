import java.util.*;
import java.io.*;
public class DFSAlgo {
    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<String>();
        for (int z = 1; z <= 1; z++) {
            File file = new File("./in/" + "600" + ".in");
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
            ArrayList<ArrayList<GraphNode>> bestSoFar = null;
            int bestSoFarValue = -1;

            for (int i = 0; i < 1000; i++) {
                int firstIndex = 0;
                int numPaths = 0;
                ArrayList<ArrayList<GraphNode>> output = new ArrayList<ArrayList<GraphNode>>();
                

                
                while (!available.isEmpty()) {
                    int indexToCheck = vertexIndices.get(firstIndex);
                    
                    GraphNode toSearch = vertices.get(indexToCheck);
                    if (!visited.contains(toSearch) && available.size() != 0) {
                        ArrayList<GraphNode> team = new ArrayList<GraphNode>();
                        GraphNode.DFS(toSearch, visited, available, team);
                        output.add(team);
                        // System.out.println(team.toString() + ": " + GraphNode.teamValue(team));
                        numPaths += 1;
                    }
                    firstIndex ++;
                }

                int solutionValue = GraphNode.valueOfSolution(output);
                if (bestSoFar == null || bestSoFarValue < solutionValue) {
                    bestSoFar = output;
                    bestSoFarValue = solutionValue;
                }

                visited = new HashSet<GraphNode>();
                available = new HashSet<GraphNode>();
                for (GraphNode vi : vertices) {
                    available.add(vi);
                }

                Collections.shuffle(vertexIndices);
            }

            String output = "";
            for (ArrayList<GraphNode> list : bestSoFar) {
                output += createListString(list) + "; ";
            }

            output = output.substring(0, output.length() - 2);
            lines.add(output);
            System.out.println("Done with graph " + z + " with value " + bestSoFarValue);
            input.close();
        }



        try {
            PrintWriter writer = new PrintWriter("./output.out", "UTF-8");
            for (String line : lines) {
                writer.println(line);
            }
            writer.close();
        } catch (IOException e) {
                // do something
        }
    }

    public static String createListString(ArrayList<GraphNode> team) {
            String output = "";

            for (GraphNode horse : team) {
                output += (horse.index + " ");
            }

            return output.substring(0, output.length() - 1); 
        }
}
