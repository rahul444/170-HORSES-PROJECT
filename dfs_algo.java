import java.util.*;

public static void main(String[] args) {
    File file = new File("./in/13.in");
    Scanner input = new Scanner(file);

    while (input.hasNext()) {
        String[] nextLine = input.nextLine().split(" ");
        System.out.println(nextLine);
    }

    input.close();
}
