import java.util.*;
import java.io.*;
public class DFSAlgo {
    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<String>();
        for (int z = 1; z <= 600; z++) {
            File file = new File("./in/" + z + ".in");
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
            ArrayList<ArrayList<Integer>> bestSoFar = null;
            ArrayList<ArrayList<Integer>> minSoFar = null;
            int bestSoFarValue = -1;

            for (int i = 0; i < 100000; i++) {
                int firstIndex = 0;
                int numPaths = 0;
                ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();
                

                
                while (!available.isEmpty()) {
                    int indexToCheck = vertexIndices.get(firstIndex);
                    
                    GraphNode toSearch = vertices.get(indexToCheck);
                    if (!visited.contains(toSearch) && available.size() != 0) {
                        ArrayList<Integer> team = new ArrayList<Integer>();
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


                if (minSoFar == null) {
                    minSoFar = output;
                }

                visited = new HashSet<GraphNode>();
                available = new HashSet<GraphNode>();
                for (GraphNode vi : vertices) {
                    available.add(vi);
                }

                Collections.shuffle(vertexIndices);
                if (minPaths > numPaths) {
                    minPaths = numPaths;
                    minSoFar = output;
                }
            }

            String output = "";
            for (ArrayList<Integer> list : bestSoFar) {
                output += createListString(list) + "; ";
            }

            output = output.substring(0, output.length() - 2);
            lines.add(output);
            int minSolValue = GraphNode.valueOfSolution(minSoFar);
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

    public static String createListString(ArrayList<Integer> team) {
            String output = "";

            for (int horse : team) {
                output += (horse + " ");
            }

            return output.substring(0, output.length() - 1); 
        }
}
