import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player extends GameObject {
    private ArrayList<Item> inventory = new ArrayList<>();
    private HashMap equipment = new HashMap<String, Item>();
    private String status;

    public Player(String name, String description, ArrayList<Item> inventory, HashMap<String, Item> equipment, String status) {
        super(name, description);
        this.inventory = inventory;
        this.equipment = equipment;
        this.status = status;
    }

    public void printInventory(ArrayList<Item> inventory) {

        System.out.println("Inventory:");
        for (Item item : inventory) {
            System.out.println(item.getName());
        }
    }

    public void printEquipment(HashMap<String, Item> equipment) {
//fix this to read from map
        System.out.println("Equipment:");
        for(HashMap.Entry<String, Item> entry: equipment.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getName());
        }
    }

    public static void getStatus(Player player) {

        System.out.println("Status: " + player.status);
    }

    public static void printPlayerInfo(Player player) {
        System.out.println("Name: " + player.getName());
        System.out.println("Status: " + player.status);
        System.out.println();
        player.printInventory(player.inventory);
        System.out.println();
        player.printEquipment(player.equipment);
    }
}
