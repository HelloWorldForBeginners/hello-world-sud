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

		this.exits.put(params[0], Application.getRooms().get(params[1]));
	}

	public void printRoom() {
		System.out.println(this.toString());
		printExits();
		printItems();
		printCreatures();
	}

	public void printExits() {
		for (HashMap.Entry<String, Room> entry: this.exits.entrySet()) {
			System.out.println(String.format("%s: %s",entry.getKey(), entry.getValue().getName()));
		}
	}
	
	private void printItems() {
		if (this.items.size() > 0) {
			System.out.println("You see: " + this.items);
		}
	}

	public void printCreatures() {
		String messageStart = "There are creatures here: ";
		String creatures = "";
		for (NonPlayer creature : this.creatures) {
			creatures = creatures + creature.getName();
		}
		
		if (creatures.length() > 0) {
			System.out.println(String.format("%s%s", messageStart, creatures));
		}		
	}
	
	public void printSpawnedCreature() {
		System.out.println(String.format("A %s: has spawned!", this.creatures));
	}

	@Override
	public String toString() {
		String toString = 
				"<<<<<======= " + this.getName() + " =======>>>>>" +
						"\n" +  
						this.getDescription() +
						"\n";
		return toString;
	}
	
}
