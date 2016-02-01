import java.util.ArrayList;

public class Player extends GameObject {
    private ArrayList<Item> inventory = new ArrayList<>();
    private ArrayList<Item> equipment = new ArrayList<>();
    private String status;

    public Player(String name, String description, ArrayList<Item> inventory, ArrayList<Item> equipment, String status) {
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

    public void printEquipment(ArrayList<Item> equipment) {

        System.out.println("Equipment:");
        for (Item item : equipment) {
            System.out.println(item.getName());
        }
    }

    public static void getStatus(Player player) {

        System.out.println("Status: " + player.status);
    }

    public static void printPlayerInfo(Player player) {
        System.out.println("Name: " + player.getName());
        System.out.println("Status: " + player.status);
        player.printInventory(player.inventory);
        player.printEquipment(player.equipment);
    }
}
