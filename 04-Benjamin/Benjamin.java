import java.util.*;
import java.io.*;
public class Benjamin{
    public static void main(String[] args) throws IOException{
        BufferedReader input = new BufferedReader(new FileReader("benjamin.dat"));
        int n = input.readLine().charAt(0)-48;
        for (int i = 0; i < n; i ++)
        {
            int m = input.readLine().charAt(0)-48;
            PriorityQueue<StoreItem> items = new PriorityQueue<StoreItem>();
            for (int j = 0; j < m; j ++)
            {
                items.add(new StoreItem(input.readLine()));
            }
            printItems(items);
            if (i != n-1)
                System.out.println();
        }
        input.close();
    }
    public static void printItems(PriorityQueue items){
        for (int i = 0; i < items.size(); i ++){
            System.out.print(items.poll() + ", ");
        }
        System.out.print(items.poll());
    }
}
class StoreItem implements Comparable<StoreItem>{
    private final String itemName;
    private final String storeName;
    private final double value;
    private final int type;
    public StoreItem(String input){
        StringTokenizer line = new StringTokenizer(input);
        String[] arr = {"Clothing", "Accessory", "Electronics", "Books", "Misc"};
        ArrayList<String> types = new ArrayList<String>(Arrays.asList(arr));
        itemName = line.nextToken();
        storeName = line.nextToken();
        type = types.indexOf(line.nextToken());
        value = Double.parseDouble(line.nextToken());
    }
    public double getValue(){
        return value;
    }
    public String getName(){
        return itemName;
    }
    public String getStoreName(){
        return storeName;
    }
    public int getType(){
        return type;
    }
    @Override
    public String toString(){
        return itemName;
    }
    @Override
    public int compareTo(StoreItem other){
        //first priority: store Name(alphabetically)
        int compareVal = storeName.compareTo(other.getStoreName());
        if (compareVal != 0)
            return compareVal;
        //second priority: item Type(first item in the array at line 35)
        compareVal = type - other.getType();
        if (compareVal != 0)
            return compareVal;
        //third priority : name(alphabetically)
        compareVal = itemName.compareTo(other.getName());
        if (compareVal != 0)
            return compareVal;
        //last priority : highest value is higher priority
        if (value > other.getValue())
            return -1;
        if (value < other.getValue())
            return 1;
        return 0;
    }
}