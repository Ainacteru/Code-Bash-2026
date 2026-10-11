import java.util.*;
import java.io.*;
public class Coffee{
    public static void main(String[] args) throws Exception{
        Scanner input = new Scanner(new File("coffee.dat"));
        double cupSize = input.nextInt();
        double kegSize = input.nextInt();
        input.nextLine();
        int row = input.nextInt() ;
        int col = input.nextInt() ;
        double solution = Math.min(cupSize, pourCoffee(row, col, 1, 1, 1, new double[]{kegSize}, cupSize));
        System.out.printf("%.5f",solution);
    }
    public static double pourCoffee(int targetRow, int targetCol, int currentRow, int startCol, int endCol, double[] arr, double cupSize)
    {
        if (currentRow == targetRow)
            return arr[0];
        int newStartCol = startCol;
        int newEndCol = endCol;
        if (targetCol - startCol == targetRow - currentRow)
            newStartCol ++;
        if (endCol < targetCol)
            newEndCol ++;
        int size = newEndCol - newStartCol + 1;
        double[] newArr = new double[size];
        if (size == arr.length){
            for (int i = 0; i < arr.length; i ++)
            {
                arr[i] -= cupSize;
                arr[i] /= 2;
                arr[i] = Math.max(0, arr[i]);
                newArr[i] += arr[i];
                if (i > 0)
                    newArr[i-1] += arr[i];
            }
        }
        if (size > arr.length){
            for (int i = 0; i < arr.length; i ++)
            {
                arr[i] -= cupSize;
                arr[i] /= 2;
                arr[i] = Math.max(0, arr[i]);
                newArr[i] += arr[i];
                newArr[i+1] += arr[i];
            }
        }
        if (size < arr.length){
            for (int i = 0; i < arr.length; i ++)
            {
                arr[i] -= cupSize;
                arr[i] /= 2;
                arr[i] = Math.max(0, arr[i]);
                if (i > 0)
                    newArr[i-1] += arr[i];
                if (i < arr.length - 1)
                    newArr[i] += arr[i];
            }
        }
        return pourCoffee(targetRow, targetCol, currentRow + 1, startCol, endCol, newArr, cupSize);
    }
}