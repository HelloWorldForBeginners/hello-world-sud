package com.kosn.entity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.kosn.util.Directions;

public class Room {

	private String classType;
	private String name;
	private String description;
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<NonPlayer> creatures = new ArrayList<>();
    private HashMap<Directions, Room> exits = new HashMap<Directions, Room>();
    
    public Room() {}
	
	public Room(String name, String description, ArrayList<Item> items, ArrayList<NonPlayer> creatures,
			HashMap<Directions, Room> exits) {
		this.name = name;
		this.description = description;
		this.items = items;
		this.creatures = creatures;
		this.exits = exits;
	}

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setCreatures(ArrayList<NonPlayer> creatures) {
        this.creatures = creatures;
    }

    public HashMap<Directions, Room> getExits() {
        return this.exits;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public ArrayList<NonPlayer> getCreatures() {
        return this.creatures;
    }

    public void deleteItem(Item item) {
        this.items.remove(item);
    }

    public void addItem(Item item) {
        this.items.add(item);
        Collections.sort(this.items);
    }
    
	public String getName() {
        return this.name;
    }

    public static void removeItem(Room room, Item item) {
        room.deleteItem(item);
    }

    public static void addItem(Room room, Item item) {
        room.addItem(item);
    }

	public void printRoom() {
		System.out.println();
		System.out.println(this.toString());
		System.out.println("Exits:");
		printExits();
		printItems();
		printCreatures();
	}

	public void printExits() {
		for (HashMap.Entry<Directions, Room> entry: this.exits.entrySet()) {
			System.out.println(String.format("%s: %s",entry.getKey(), entry.getValue().getName()));
		}
	}
	
	private void printItems() {
		if (this.items.size() > 0) {
			System.out.println();
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
			System.out.println();
			System.out.println(String.format("%s%s", messageStart, creatures));
		}		
	}
	
	public void printSpawnedCreature() {
		System.out.println(String.format("A %s: has spawned!", this.creatures));
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setExits(HashMap<Directions, Room> exits) {
		this.exits = exits;
	}
	
	@Override
	public String toString() {
		String toString = 
				"<<<<<======= " + this.name + " =======>>>>>" +
						"\n" +  
						this.description +
						"\n";
		return toString;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creatures == null) ? 0 : creatures.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((exits == null) ? 0 : exits.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (creatures == null) {
			if (other.creatures != null)
				return false;
		} else if (!creatures.equals(other.creatures))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (exits == null) {
			if (other.exits != null)
				return false;
		} else if (!exits.equals(other.exits))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

    public NonPlayer getNonPlayer(String target) {

		for (NonPlayer creature : this.creatures) {
			if (creature.getName().equals(target)) {
                return creature;
            }
			if (creature.getName().startsWith(target)) {
				return creature;
			}
        }
		return null;
	}

	public Item getItemIfExists(String target) {
		for (Item checkItem: this.items) {
            if (checkItem.getName().equals(target)) {
                return checkItem;
            }
            if (checkItem.getName().startsWith(target)) {
                return checkItem;
            }
        }
		return null;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}
}
