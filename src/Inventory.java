import java.util.ArrayList;

class Inventory {

    public static void getItem(int x, int y, String item,
                                 ArrayList<String> inventory, Room[][] room) {

        // Check if item is a valid room item
        boolean validRoomItem = false;
        for (String roomItems : room[x][y].items ) {
            if (roomItems.equals(item)) {
                validRoomItem = true;
                break;
            }
        }

        // Check if item is already in inventory
        boolean inInventory = false;
        for (String itemInInv: inventory) {
            if (itemInInv.equals(item)) {
                inInventory = true;
                break;
            }
        }

        // Text output
        if (!inInventory && validRoomItem) {
            System.out.println("You pick up the " + item + ".");
            inventory.add(item);
            Rooms.removeItem(room, x, y, item);
        }
        else if (inInventory) {
            System.out.println("You already have the " + item + ".");
        }
        else if (!validRoomItem) {
            System.out.println("You don't see that here.");
        }
        else {
            System.out.println("I don't understand.");
        }
    }

    public static void putItem(int x, int y, String item,
                                 ArrayList<String> inventory, Room[][] room) {


        // Check if item is a valid inventory item
        boolean inInventory = false;
        for (String itemInInv: inventory) {
            if (itemInInv.equals(item)) {
                inInventory = true;
                break;
            }
        }

        // Check if item is already in room
        boolean inRoom = false;
        for (String roomItems : room[x][y].items ) {
            if (roomItems.equals(item)) {
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

    public static void print(ArrayList<String> inventory) {

        System.out.println("Inventory:");
        for (String item : inventory) {
            System.out.println(item);
        }
    }


}
