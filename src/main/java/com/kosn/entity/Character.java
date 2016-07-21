package com.kosn.entity;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Character extends GameObject {

    protected int level;
    protected int money;
    protected int exp;
    protected int hitPoints;
    protected int maxHitPoints;
    protected int attack;
    protected int defense;
    protected ArrayList<Item> inventory = new ArrayList<>();
    protected HashMap<EquipSlot, Item> equipment = new HashMap<EquipSlot, Item>();
    protected ArrayList<Ability> deck = new ArrayList<>();
    protected ArrayList<Ability> hand = new ArrayList<>();

    public Character(String name, String description, int level, int money, int exp, int hitPoints, int maxHitPoints,
                     int attack, int defense) {
        super(name, description);
        this.level = level;
        this.money = money;
        this.exp = exp;
        this.hitPoints = hitPoints;
        this.maxHitPoints = maxHitPoints;
        this.attack = attack;
        this.defense = defense;
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public HashMap<EquipSlot, Item> getEquipment() {
        return this.equipment;
    }

    public void setEquipment(HashMap<EquipSlot, Item> equipment) {
        this.equipment = equipment;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHitPoints() {
        return this.hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getMaxHitPoints() {
        return this.maxHitPoints;
    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    public int getAttack() {
        return this.attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getExp() {
        return this.exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void printEquipment(HashMap<String, Item> equipment) {
        System.out.println("Equipment:");
        for (HashMap.Entry<String, Item> entry : equipment.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getName());
        }
    }

    public void printInventory(ArrayList<Item> inventory) {
        System.out.println("Inventory:");
        for (Item item : inventory) {
            System.out.println(item.getName());
        }
    }
    
	public void printHealth() {
    	System.out.format("%s HP: %d/%d\n", this.getName(), this.hitPoints, this.maxHitPoints);
    	System.out.println("\n");
	}

	@Override
	public String toString() {
		return this.name;
	}

	public void setInventory(String target, Room thisRoom) {
		// TODO Auto-generated method stub
		
	}
}
