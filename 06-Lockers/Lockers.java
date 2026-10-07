import java.io.*;
import java.util.*;

public class uil2
{
    public static void main( String args[] ) throws Exception
    {
        //this problem boils down into whether or not the query number is a square.
        //If it is a square number, the locker is open.
        //otherwise, the locker is closed.

        Scanner scan = new Scanner(new File("./lockers.dat"));

        int n = scan.nextInt();
        int m = scan.nextInt();

        int cnt = 0;
        while(m-- > 0){
            int q = scan.nextInt();

            int root = (int)Math.sqrt(q);

            if(root * root == q){
                cnt++;
            }
            
        }

        System.out.println(cnt);
        
    }
}


