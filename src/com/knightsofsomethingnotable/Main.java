package com.knightsofsomethingnotable;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	
    static int x = 0;
    static int y = 0;
    // start game
    static boolean playing = true;
    static boolean combat = false;
    // define room array sizes
    static final int WIDTH = 2;
    static final int HEIGHT = 2;
    static Room[][] room = new Room[WIDTH][HEIGHT];

    // inventory and equipment 
    static ArrayList<Item> inventory = new ArrayList<>();
    static HashMap<String, Item> equipment = new HashMap<String, Item>();
    
    //TODO: this seems messy. Change to default values in constructor
    static Player player = new Player("Matt", "Awesome", 1, 0, 0, 10, 10, 10, 1, 1, inventory, equipment);

	static HashMap<String, Runnable> commands = new HashMap<String, Runnable>();
	
    public static void main(String args[]) {
    	
    	commands.put("north", () -> Commands.goNorth());
    	commands.put("n", () -> Commands.goNorth());
    	commands.put("south", () -> Commands.goSouth());
    	commands.put("s", () -> Commands.goSouth());
    	commands.put("east", () -> Commands.goEast());
    	commands.put("e", () -> Commands.goEast());
    	commands.put("west", () -> Commands.goWest());
    	commands.put("w", () -> Commands.goWest());

    	commands.put("i", () -> Commands.showInventory());
    	commands.put("inv", () -> Commands.showInventory());
    	commands.put("inventory", () -> Commands.showInventory());
    	
    	commands.put("equip", () -> Commands.equipment(Input.target));
    	commands.put("equipment", () -> Commands.equipment(Input.target));
    	
    	commands.put("unequip", () -> Commands.removeEquipment(Input.target));
    	
    	commands.put("player", () -> Commands.playerStatus());
    	commands.put("p", () -> Commands.playerStatus());
    	commands.put("get", () -> Commands.addToInventory(Input.target));
    	commands.put("put", () -> Commands.removeFromInventory(Input.target));
    	commands.put("attack", () -> Commands.attack(Input.target));
    	commands.put("look", () -> Commands.roomStatus());
    	commands.put("check", () -> Commands.checkThing(Input.target));
    	commands.put("quit", () -> Commands.quitGame());

    	
        // Build rooms
    	System.out.println("Welcome to The Knights of Something Notable!\n");
        World.build(room, WIDTH, HEIGHT);
        World.print(room, x, y);
        
        // Start game
        while (playing) {
        	System.out.println("---------------------------------------------------");
        	
        	Runnable thingToRun = commands.get(Input.getCommand());
        	
        	if (thingToRun != null) {
        		new Thread(thingToRun).start();
        	}
        	else {
        		System.out.println("You can't do that.");
        	}
        }
        System.exit(0);
    }
}
