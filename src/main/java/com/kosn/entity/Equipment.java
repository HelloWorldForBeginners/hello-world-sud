package com.kosn.entity;
import java.util.ArrayList;
import java.util.HashMap;

import com.kosn.application.Application;

public class Equipment {

	ArrayList<Item> playerEquipment = new ArrayList<>();
	static Player player = Application.getPlayer();

    public static void equipItem(String itemName) {
        Item item = player.checkInventory(itemName);

        if (item == null) {
        	System.out.println("You don't have that in your inventory.");
        	return;
        }
        
        if (item.getSlot() == null) {
        	System.out.println("You can't equip that.");
        	return;
        }
        
        Item addBackIntoInventory = player.getEquipment().put(item.getSlot(),item);
        if (addBackIntoInventory != null) {
        	player.getInventory().add(addBackIntoInventory);
        	System.out.println("You unequip the " + addBackIntoInventory + ".");
        }
        
        System.out.println("You equip the " + itemName + ".");
        player.getInventory().remove(item);
        player.setDefense(player.getDefense() + item.getDefense());
        player.setAttack(player.getAttack() + item.getAttack());
    }

    public static void unequipItem(String itemName) {
    	Item equippedItem = player.checkEquipment(itemName);
    	if (equippedItem != null) {
    		unequipItem(equippedItem.getSlot());
    		return;
    	}
    	System.out.println("You don't have a " + itemName + " equipped.");
    }
    
    public static void unequipItem(EquipSlot slot) {
        Item item = player.getEquipment().get(slot);
        if (item != null) {
            System.out.println("You unequip the " + item.getName() + ".");
            player.getEquipment().remove(item.getSlot());
            player.getInventory().add(item);
            player.setDefense(player.getDefense() - item.getDefense());
            player.setAttack(player.getAttack() - item.getAttack());
            return;
        }
    }

    public static void print(HashMap<String, Item> equipment) {

        System.out.println("Equipment:");

        for(HashMap.Entry<String, Item> entry: equipment.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getName());
        }
    }
}
