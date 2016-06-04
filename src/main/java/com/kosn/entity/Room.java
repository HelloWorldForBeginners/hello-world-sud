package com.kosn.entity;
import java.util.ArrayList;
import java.util.HashMap;

import com.kosn.application.Application;

public class Room extends GameObject {

    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<NonPlayer> creatures = new ArrayList<>();
    private HashMap<String, Room> exits = new HashMap<String, Room>();

    public Room(String name, String description) {
    	super(name, description);
    }
    
    public Room(String[] params) {
    	super(params[0], params[1]);
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
    
	@Override
	public String toString() {
		String toString = getName();
		return toString;
	}
	
	public static void print(Room room) {
    	
    	System.out.println("<<<<<======= " + room.getName() + " =======>>>>>\n");
        System.out.println(room.getDescription());
        System.out.println();
        System.out.println("Exits: " + room.getExits());
        System.out.println();
        if (room.getItems().size() > 0) {
            System.out.println("You see: " + room.getItems());
        }
        System.out.println();
        if (room.getCreatures().size() > 0) {
            System.out.println("There are creatures here: " + room.getCreatures());
        }
        System.out.println();
    }

    public static String getRoomName(Room room) {

        return room.getName();
    }

    public static void removeItem(Room room, Item item) {

        room.deleteItem(item);
    }

    public static void addItem(Room room, Item item) {

        room.addItem(item);
    }

	public void setExits(String[] params) {

		this.exits.put(params[0], Application.rooms.get(params[1]));
	}

}
