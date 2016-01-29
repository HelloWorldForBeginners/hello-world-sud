import java.util.ArrayList;

class Inventory {

    public static void getItem(int x, int y, String itemName,
                                 ArrayList<Item> inventory, Room[][] room) {

        // Check if item is a valid room item
        boolean inRoom = false;
        Item item = null;
        for (Item roomItems : room[x][y].items ) {
            if (roomItems.getName().equals(itemName)) {
                inRoom = true;
                item = roomItems;
                break;
            }
        }

        // Check if item is already in inventory
        boolean inInventory = false;
        for (Item itemInInv: inventory) {
            if (itemInInv.getName().equals(itemName)) {
                inInventory = true;
                break;
            }
        }

        // Text output
        if (!inInventory && inRoom) {
            System.out.println("You pick up the " + item + ".");
            inventory.add(item);
            Rooms.removeItem(room, x, y, item);
        }
        else if (inInventory) {
            System.out.println("You already have the " + item + ".");
        }
        else if (!inRoom) {
            System.out.println("You don't see that here.");
        }
        else {
            System.out.println("I don't understand.");
        }
    }

    public static void putItem(int x, int y, String itemName,
                                 ArrayList<Item> inventory, Room[][] room) {


        // Check if item is a valid inventory item
        boolean inInventory = false;
        Item item = null;
        for (Item itemInInv: inventory) {
            if (itemInInv.getName().equals(itemName)) {
                inInventory = true;
                item = itemInInv;
                break;
            }
        }

        // Check if item is already in room
        boolean inRoom = false;
        for (Item roomItems : room[x][y].items ) {
            if (roomItems.getName().equals(itemName)) {
                inRoom = true;
                break;
            }
        }

        // Text output
        if (inInventory && !inRoom) {
            System.out.println("You put down the " + item + ".");
            inventory.remove(item);
            Rooms.addItem(room, x, y, item);
        }
        else if (inRoom) {
            System.out.println("There is already a " + item + " here.");
        }
        else if (!inInventory) {
            System.out.println("You don't have one of those.");
        }
        else {
            System.out.println("There's a problem here.");
        }
    }

    public static void print(ArrayList<Item> inventory) {

        System.out.println("Inventory:");
        for (Item item : inventory) {
            System.out.println(item.getName());
        }
    }

}
