package com.knightsofsomethingnotable.entities;
import java.util.ArrayList;
import java.util.HashMap;

import com.knightsofsomethingnotable.GameObject;

public abstract class Character extends GameObject {

    private int level;
    protected int money;
    protected int exp;
    protected int hitPoints;
    private int maxHitPoints;
    private int attack;
    private int defense;
    private ArrayList<Item> inventory = new ArrayList<>();
    private HashMap<String, Item> equipment = new HashMap<String, Item>();

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

    public static void printEquipment(HashMap<String, Item> equipment) {

        System.out.println("Equipment:");
        for (HashMap.Entry<String, Item> entry : equipment.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getName());
        }
    }

    public static void printInventory(ArrayList<Item> inventory) {

        System.out.println("Inventory:");
        for (Item item : inventory) {
            System.out.println(item.getName());
        }
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public HashMap<String, Item> getEquipment() {
        return this.equipment;
    }

    public void setEquipment(HashMap<String, Item> equipment) {
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
    
	public void printHealth() {
		
    	System.out.println(this.getName() + " HP: " + this.hitPoints + "/" + this.maxHitPoints + "\n");
    	System.out.println();
	}
}
