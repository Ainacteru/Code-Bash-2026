import java.util.*;

public class uil
{
    public static void main( String args[] ) throws Exception
    {
        Scanner scan = new Scanner(System.in);

        int T = scan.nextInt();
        scan.nextLine();

        while(T -- > 0){
            char[] arr = scan.nextLine().toCharArray();
          
            String word = ""; //the final word to print out

            int rep = 0; //the number of times a letter repeats
            char letter = ""; //store the letter
          
            //time to create the word
            for(char ch : arr){
                if(ch >= 48 && ch <= 57){ //check if ch is a number character
                  rep = rep * 10 + (int)ch - 48;
                }

                else{ //ch is a letter character
                  for(int i = 0; i < rep; i++){
                    word += "" + letter;
                  }

                  //reset
                  rep = 0;
                  letter = ch;
                }
            }

            System.out.println(word);

        }
        
    }


}
