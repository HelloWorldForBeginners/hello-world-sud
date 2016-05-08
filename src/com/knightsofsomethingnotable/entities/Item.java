package com.knightsofsomethingnotable.entities;

import com.knightsofsomethingnotable.GameObject;
import com.knightsofsomethingnotable.management.Room;

public class Item extends GameObject {
    private int amount;
    private String type;
    private String slot;
    private int defense;

    public Item(String name, String description, int amount, String type, String slot, int defense) {
        super(name, description);
        this.amount = amount;
        this.type = type;
        this.slot = slot;
        this.defense = defense;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getType() {
        return this.type;
    }

    public String getSlot() {
        return this.slot;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
    
    public static void printItemInfo(String target, Room room) {

        Item item = getitem(target, room);
        if (item != null) {
        	printInfo(item);
        }
    }

    private static void printInfo(Item item) {

    	System.out.println("Name: " + item.getName() + "(" + item.getAmount() + ")");
        System.out.println(item.getDescription());
        System.out.println("Type: " + item.getType());
        System.out.println("Slot: " + item.getSlot());
        System.out.println("Def: " + item.getDefense());
        System.out.println();
	}

	private static Item getitem(String target, Room room) {
    	for (Item checkItem: room.getItems() ) {
            if (checkItem.getName().equals(target)) {
                return checkItem;
            }
            if (checkItem.getName().startsWith(target)) {
                return checkItem;
            }
        }
		return null;
	}

	@Override //means this method exists somewhere; it comes from the Object type
	public String toString() {
		String toString = getName();
		return toString;
	}
    
}
