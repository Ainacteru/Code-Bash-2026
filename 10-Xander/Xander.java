import java.io.*;
import java.util.*;

public class Xander
{
     public static void main( String args[] ) throws Exception
    {
        //should be able to solve with regex
        Scanner scan = new Scanner(new File("./xander.dat"));

        int T = scan.nextInt();
        scan.nextLine();

        while(T -- > 0){
            String word = scan.next();
            String pattern = scan.next();

            String reg = "";
            for(int i = 0; i < pattern.length(); i++){
                if(pattern.charAt(i) == '*'){
                    reg += ".*";
                }

                else if(pattern.charAt(i) == '?'){
                    reg += ".";
                }

                else{
                    reg += "" + pattern.charAt(i);
                }
            }

            if(word.matches(reg)){
                System.out.println("YES");
            }

            else
             System.out.println("NO");

        }
        
    }



}


