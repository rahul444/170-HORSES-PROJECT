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

        int v = Integer.parseInt(input.nextLine());
        int[][] matrix = new int[v][v];
        for (int i = 0; i < v; i++) {
            String[] nextLine = input.nextLine().split(" ");
            for (int j = 0; j < v; j++) {
                matrix[i][j] = Integer.parseInt(nextLine[j]);
            }
        }

        for (int i = 0; i < v; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }

        input.close();
    }
}
