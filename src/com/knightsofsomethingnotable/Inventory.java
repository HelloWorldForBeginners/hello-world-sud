package com.knightsofsomethingnotable;
import java.util.ArrayList;

class Inventory {

    public static void getItem(int x, int y, String itemName,
                                 Player player, Room[][] room) {

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
        for (Item itemInInv: player.getInventory()) {
            if (itemInInv.getName().equals(itemName)) {
                inInventory = true;
                break;
            }
        }

        // Text output
        if (!inInventory && inRoom) {
            System.out.println("You pick up the " + itemName + ".");
            player.getInventory().add(item);
            World.removeItem(room, x, y, item);
        }
        else if (inInventory) {
            System.out.println("You already have the " + itemName + ".");
        }
        else if (!inRoom) {
            System.out.println("You don't see that here.");
        }
        else {
            System.out.println("I don't understand.");
        }
    }

    public static void putItem(int x, int y, String itemName,
                               Player player, Room[][] room) {


        // Check if item is a valid inventory item
        boolean inInventory = false;
        Item item = null;
        for (Item itemInInv: player.getInventory()) {
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
            System.out.println("You put down the " + itemName + ".");
            player.getInventory().remove(item);
            World.addItem(room, x, y, item);
        }
        else if (inRoom) {
            System.out.println("There is already a " + itemName + " here.");
        }
        else if (!inInventory) {
            System.out.println("You don't have one of those in your inventory.");
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
