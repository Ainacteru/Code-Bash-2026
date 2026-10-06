
import java.io.*;
import java.util.*;

public class Ariel {
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(new File("./ariel.dat"));

        int lines = scanner.nextInt();
        scanner.nextLine();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines; i++) {
            sb.append(scanner.next().charAt(scanner.nextInt()));
           
            if (scanner.hasNextLine())
                scanner.nextLine();
        }

        System.out.println(sb.toString());

    }
}