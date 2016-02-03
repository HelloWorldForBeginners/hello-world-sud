import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// put this in main to call
// ItemList itemList = new ItemList("src/itemList.txt");
// itemList.printArray();

public class ItemList {

    File textFile;
    Scanner input;
    String itemName;

    private ArrayList<String> itemList = new ArrayList<>();

    public ItemList(String file){
        textFile = new File(file);
        readInFile(textFile);
    }

    private void readInFile(File textFile) {
        try {
            input = new Scanner(textFile);

            int i = 0;

            while(input.hasNext()){
                itemList.add(input.next());
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printArray() {
        for (String s: itemList){
            System.out.println(s);
        }
    }
}
