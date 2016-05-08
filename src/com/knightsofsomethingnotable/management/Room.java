package com.knightsofsomethingnotable.management;
import java.util.ArrayList;
import java.util.HashMap;

import com.knightsofsomethingnotable.GameObject;
import com.knightsofsomethingnotable.entities.Item;
import com.knightsofsomethingnotable.entities.NonPlayer;

public class Room extends GameObject {

    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<NonPlayer> creatures = new ArrayList<>();
    private HashMap<String, Room> exits = new HashMap<String, Room>();

    public Room(String name, String description, ArrayList<Item> items, ArrayList<NonPlayer> creatures) { //, HashMap<String, String> exits) {
    	super(name, description);
    	this.items = items;
    	this.creatures = creatures;
    	// this.exits = exits;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setCreatures(ArrayList<NonPlayer> creatures) {
        this.creatures = creatures;
    }

    public void setExits(String direction, Room room) {
        this.exits.put(direction, room);
    }

    public HashMap<String, Room> getExits() {
        return this.exits;
    }

    public void setItems(Item item) {
        this.items.add(item);
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public void setCreatures(NonPlayer creature) {
        this.creatures.add(creature);
    }

    public ArrayList<NonPlayer> getCreatures() {
        return this.creatures;
    }

    public void deleteItem(Item item) {
        this.items.remove(item);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }
    
	public String toString() {
		String toString = getName();
		return toString;
	}
}
