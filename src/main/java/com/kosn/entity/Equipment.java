package com.kosn.entity;
import java.util.ArrayList;
import java.util.HashMap;

public class Equipment {

	ArrayList<Item> playerEquipment = new ArrayList<>();

    public static void equipItem(String itemName, Player player) {

        boolean inInventory = false;
        boolean isEquipment = false;
        Item item = null;
        String unequipThisItem;

        // Check if item is a valid inventory item
        for (Item i : player.getInventory() ) {
            if (i.getType().equals("equipment")) {
                isEquipment = true;
            }
            if (i.getName().equals(itemName)) {
                inInventory = true;
                item = i;
                break;
            }
        }

        // Check if item is already equipped
        boolean equipped = false;
        for(HashMap.Entry<String, Item> entry: player.getEquipment().entrySet()) {
            if (entry.getValue().getName().equals(itemName)) {
                equipped = true;
                item = entry.getValue();
                break;
            }
        }

        // Check if target slot of item to be equipped is already filled; unequip if so
        if (item != null) {
            for(HashMap.Entry<String, Item> entry: player.getEquipment().entrySet()) {
                if (entry.getKey().equals(item.getSlot()) && !item.equals(entry.getValue())) {
                    unequipThisItem = entry.getValue().getName();
                    Equipment.unequipItem(unequipThisItem, player);
                }
            }
        }

        // Text output
        if (equipped) {
            System.out.println("You already have a " + itemName + " equipped.");
        }
        else if (!inInventory) {
            System.out.println("You don't have that in your inventory.");
        }
        else if (!isEquipment) {
            System.out.println("You can't equip that, even though it would probably be hilarious.");
        }
        else if (!equipped && inInventory && isEquipment) {
            System.out.println("You equip the " + itemName + ".");
            //check if slot exists in map
            player.getEquipment().put(item.getSlot(),item);
            player.getInventory().remove(item);
            player.setDefense(player.getDefense() + item.getDefense());
        }
        else {
            System.out.println("I don't understand.");
        }
    }

    public static void unequipItem(String itemName, Player player) {


        // Check if item is valid equipment
        boolean equipped = false;
        Item item = null;

        for(HashMap.Entry<String, Item> entry: player.getEquipment().entrySet()) {
            if (entry.getValue().getName().equals(itemName)) {
                equipped = true;
                item = entry.getValue();
                break;
            }
        }
        // Text output
        if (equipped) {
            System.out.println("You unequip the " + itemName + ".");
            player.getEquipment().remove(item);
            player.getInventory().add(item);
            player.setDefense(player.getDefense() - item.getDefense());
        }
        else if (!equipped) {
            System.out.println("You don't have one of those equipped.");
        }

        else {
            System.out.println("I don't understand.");
        }
    }

    public static void print(HashMap<String, Item> equipment) {

        System.out.println("Equipment:");

        for(HashMap.Entry<String, Item> entry: equipment.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getName());
        }
    }
}
