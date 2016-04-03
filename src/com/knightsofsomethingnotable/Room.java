package com.knightsofsomethingnotable;
import java.util.ArrayList;
import java.util.HashMap;

class Room extends GameObject {

    private int number;
//    private String name;
//    private String description;
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<NonPlayer> creatures = new ArrayList<>();
    private HashMap<String, String> exits = new HashMap<String, String>();

    public Room(int number, String name, String description, ArrayList<Item> items, ArrayList<NonPlayer> creatures, HashMap<String, String> exits) {
    	super(name, description);
    	this.items = items;
    	this.creatures = creatures;
    	this.exits = exits;
    	this.number = number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setCreatures(ArrayList<NonPlayer> creatures) {
        this.creatures = creatures;
    }

    public int getNumber() {
        return this.number;
    }

    public void setExits(String key, String value) {
        this.exits.put(key, value);
    }

    public HashMap<String, String> getExits() {
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
}
