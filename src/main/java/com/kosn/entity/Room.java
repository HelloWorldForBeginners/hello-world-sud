package com.kosn.entity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.kosn.util.Direction;

public class Room implements Examinable {

	private String classType;
	private String name;
	private String description;
    private List<Item> items = new ArrayList<>();
    private List<NonPlayer> creatures = new ArrayList<>();
    private HashMap<Direction, Room> exits = new HashMap<Direction, Room>();
    
    public Room() {}
	
	public Room(String name, String description, ArrayList<Item> items, ArrayList<NonPlayer> creatures,
			HashMap<Direction, Room> exits) {
		this.name = name;
		this.description = description;
		this.items = items;
		this.creatures = creatures;
		this.exits = exits;
	}

    public void setItems(List<Item> roomItems) {
        this.items = roomItems;
    }

    public void setCreatures(List<NonPlayer> roomCreatures) {
        this.creatures = roomCreatures;
    }

    public HashMap<Direction, Room> getExits() {
        return this.exits;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public List<NonPlayer> getCreatures() {
        return this.creatures;
    }

    public void deleteItem(Item item) {
        this.items.remove(item);
    }

    public void addItem(Item item) {
        this.items.add(item);
        Collections.sort(this.items);
    }
    
    public void addCreature(NonPlayer creature) {
        this.creatures.add(creature);
    }
    
	@Override
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
		for (HashMap.Entry<Direction, Room> entry: this.exits.entrySet()) {
			System.out.println(String.format("%s: %s",entry.getKey(), entry.getValue().getName()));
		}
	}
	
	private void printItems() {
		if (this.items.size() > 0) {
			System.out.println();
			System.out.println("Random junk litters the floor: " + this.items + "\n");
		}
	}

	public void printCreatures() {
		if (this.creatures.size() > 0) {
			System.out.println("\nThere are creatures here:");
			System.out.println(this.creatures);
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

	public void setExits(HashMap<Direction, Room> exits) {
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

    public NonPlayer checkRoomForNonPlayer(String target) {
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

	public Item checkRoomForItem(String target) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public void printInfo() {
		// TODO Auto-generated method stub
		
	}

	
	
}
