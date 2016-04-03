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


    public static void main(String args[]) {

    	// Build rooms
    	System.out.println("Welcome to The Knights of Something Notable!\n");
        World.build(room, WIDTH, HEIGHT);
        World.print(room, x, y);
        
        // Start game
        while (playing) {
        	Commands.processCommand(Input.getCommand());
        }
        System.exit(0);
    }
}
