import java.util.*;
import java.io.*;

public class Samuel {
    public static void main(String[] args) throws IOException{
        BufferedReader input = new BufferedReader(new FileReader("samuel.dat"));
        int num = Integer.parseInt(input.readLine());

        for (int i = 0; i < num; i++) {
            int[] vals = Arrays.stream(input.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(countOccurences(vals[0], vals[1], vals[2]));
        }
    }

    //n is tower height
    //m is max tile size
    //k is max times a size can appear
    public static int countOccurences(int n, int m, int k) {
        //each index of the array is the amount of times 
        //the combination appears so you return arr[n]
        int[] arr = new int[n + 1];
        arr[0] = 1;

        for (int size = 1; size <= m; size++) {
            int[] temp = new int[n + 1];

            for (int height = 0; height <= n; height++) {
                if (arr[height] == 0) {
                    continue;
                }
                for (int occ = 0; occ <= k; occ++) {
                    if (height + occ <= n)
                        temp[height + occ] += arr[height];
                }
            }

            arr = temp;
        }
        return arr[n];
    } 
}