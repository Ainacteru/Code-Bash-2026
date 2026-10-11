import java.util.*;
import java.io.*;
public class SC1{
    public static void main(String[] args) throws IOException{
        BufferedReader input = new BufferedReader(new FileReader("sc1.dat"));
        StringTokenizer line = new StringTokenizer(input.readLine());
        int dictionarySize = Integer.parseInt(line.nextToken());
        int wordSize = Integer.parseInt(line.nextToken());
        PriorityQueue<Word> dictionary = new PriorityQueue<Word>();
        line = new StringTokenizer(input.readLine());
        for (int i = 0; i < dictionarySize; i ++){

        }
    }
    public static String breakUpWord(PriorityQueue<Word> dict, String line){
        if (line.equals(""))
            return "";
        Word currentWord = dict.poll();
        if (line.indexOf(currentWord.))
    }
}
class Word implements Comparable<Word>{
    private final String word;
    private final int size;
    public Word(String s){
        word = s;
        size = s.length();
    }
    public String getWord(){
        return word;
    }
    public int getSize(){
        return size;
    }
    @Override
    public String toString(){
        return word;
    }
    @Override
    public int compareTo(Word other){
        return size - other.getSize();
    }
}