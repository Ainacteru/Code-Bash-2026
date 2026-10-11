import java.util.*;
import java.io.*;
public class SC1{
    /*
    ok basically my method here is to take the largest word in my dictionary, and then find every instance of the word
    if there are none i go to the next one no big deal
    if i cant find an instance of any word in the dictionary here i return null
    for every instance, i split up the word into 1-3 chunks: the word ive identified, the section after, and the section before
    for the 1st and 3rd chunks, I run the method again with those chunks and my original dictionary(words can be used multiple times)
    and i return null for any chunks I get null back for (basically throwing away those chunks)
    any chunks I see that dont return null, i return the 1st chunk, a space, my word, and the 3rd chunk
    */
    public static void main(String[] args) throws IOException{
        BufferedReader input = new BufferedReader(new FileReader("sc1.dat"));
        StringTokenizer line = new StringTokenizer(input.readLine());
        int dictionarySize = Integer.parseInt(line.nextToken());
        int wordSize = Integer.parseInt(line.nextToken());
        PriorityQueue<Word> dictionary = new PriorityQueue<Word>();
        line = new StringTokenizer(input.readLine());
        for (int i = 0; i < dictionarySize; i ++){
            dictionary.add(new Word(line.nextToken()));
        }
        String phrase = input.readLine();
        System.out.println(breakUpWord(dictionary, phrase));
    }
    public static String breakUpWord(PriorityQueue<Word> dict, String line){
        PriorityQueue<Word> newDict = new PriorityQueue<>(dict);
        if (line.equals(""))
            return "";
        Word currentWord = newDict.poll();
        int index = line.indexOf(currentWord.getWord());
        while (index == -1){
            if (newDict.size() > 0)
                currentWord = newDict.poll();
            else return null;
            index = line.indexOf(currentWord.getWord());
        }
        while (index != -1){
            //System.out.print(line.substring(0, index) + " ");
            //System.out.print(currentWord + " ");
            //System.out.println(line.substring(index + currentWord.getSize()));
            String firstBlock = breakUpWord(dict, line.substring(0, index));
            String lastBlock = breakUpWord(dict, line.substring(index + currentWord.getSize()));
            if (firstBlock != null && lastBlock != null)
            {
                return firstBlock + (index==0?"":" ") + currentWord.getWord() + (index==line.length()-currentWord.getSize()?"":" ") + lastBlock;
            }
            line = line.substring(index + currentWord.getSize());
            index = line.indexOf(currentWord.getWord());
        }
        return "ERROR";
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
        return other.getSize() - size;
    }
}