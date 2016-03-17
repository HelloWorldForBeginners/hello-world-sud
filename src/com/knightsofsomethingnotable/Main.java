package com.knightsofsomethingnotable;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	
    static int x = 0;
    static int y = 0;
    // start game
    static boolean playing = true;
    static boolean combat = true;
    // define room array sizes
    static final int WIDTH = 2;
    static final int HEIGHT = 2;
    static Room[][] room = new Room[WIDTH][HEIGHT];

    // inventory and equipment 
    static ArrayList<Item> inventory = new ArrayList<>();
    static HashMap<String, Item> equipment = new HashMap<String, Item>();
    
    //this seems messy. Change to default values in constructor
    static Player player = new Player("Matt", "Awesome", 1, 0, 0, 10, 10, 10, 1, 1, inventory, equipment);

    
    public static void main(String args[]) {

        // Build rooms
        World.build(room, WIDTH, HEIGHT);
        World.print(room, x, y);
        
        // Start game
        while (playing) {

        	ArrayList<String> parsedInput = Input.getCommand();
        	
        	try {
            	// test the string and return an enum with Commands.Command.valueOf(input)
            	// create a new object Commands.CommandTest and pass in the returned enum
            	// call the TakeAction() method of the Commands.CommandTest object value
            	Commands.CommandTest value = new Commands.CommandTest(Commands.Command.valueOf(parsedInput.get(0)), parsedInput.get(1));
            	value.TakeAction(HEIGHT, WIDTH, x, y, room, inventory, equipment, player, playing);
            	
        	} catch (IllegalArgumentException e) {
        		System.out.println("You can't do that.");
        	}

        }
        System.exit(0);
    }
}
