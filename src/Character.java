import com.sun.javafx.collections.ArrayListenerHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Character extends GameObject {

    private int level;
    private int exp;
    private int hitPoints;
    private int maxHitPoints;
    private int attack;
    private int defense;
    private ArrayList<Item> inventory = new ArrayList<>();
    private HashMap equipment = new HashMap<String, Item>();

    public Character(String name, String description, int level, int exp, int hitPoints, int maxHitPoints,
                     int attack, int defense, ArrayList<Item> inventory, HashMap<String, Item> equipment) {
        super(name, description);
        this.level = level;
        this.exp = exp;
        this.hitPoints = hitPoints;
        this.maxHitPoints = maxHitPoints;
        this.inventory = inventory;
        this.equipment = equipment;
        this.attack = attack;
        this.defense = defense;
    }

    public static void printEquipment(HashMap<String, Item> equipment) {

        System.out.println("Equipment:");
        for(HashMap.Entry<String, Item> entry: equipment.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getName());
        }
    }

    public static void printInventory(ArrayList<Item> inventory) {

        System.out.println("Inventory:");
        for (Item item : inventory) {
            System.out.println(item.getName());
        }
    }

    public ArrayList<Item> getInventory() { return this.inventory; }

    public HashMap<String, Item> getEquipment() { return this.equipment; }

    public void setLevel(int level) { this.level = level; }

    public int getLevel() {
        return this.level;
    }

    public void setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }

    public int getHitPoints() {
        return this.hitPoints;
    }

    public void setMaxHitPoints(int maxHitPoints) { this.maxHitPoints = maxHitPoints; }

    public int getMaxHitPoints() {
        return this.maxHitPoints;
    }

    public int getAttack() { return this.attack; }

    public int getDefense() { return this.attack; }

    public int getExp() { return this.attack; }

    public static void printNonPlayerInfo(Character nonPlayer) {
        System.out.println("Name: " + nonPlayer.getName() + "(" + nonPlayer.level + ")");
        System.out.println("HP: " + nonPlayer.hitPoints + "/" + nonPlayer.maxHitPoints);
        System.out.println("Attack: " + nonPlayer.attack);
        System.out.println("Defense: " + nonPlayer.defense);
        System.out.println();
    }
}
