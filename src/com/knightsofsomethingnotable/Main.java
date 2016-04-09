package com.knightsofsomethingnotable;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	
    static boolean playing = true;
    static boolean combat = false;
    
    static Room currentRoom = null;
    static Room defaultRoom = null;
    
    static final int numRooms = 4;
    static HashMap<String, Room> rooms = new HashMap<String, Room>();

    // inventory and equipment 
    static ArrayList<Item> inventory = new ArrayList<>();
    static HashMap<String, Item> equipment = new HashMap<String, Item>();
    
    //TODO: this seems messy. Change to default values in constructor
    static Player player = new Player("Matt", "Awesome", 1, 0, 0, 10, 10, 10, 1, 1, inventory, equipment);


    public static void main(String args[]) {

    	// Build rooms
    	System.out.println("Welcome to The Knights of Something Notable!\n");
        World.build(rooms);
        defaultRoom = rooms.entrySet().iterator().next().getValue();
        currentRoom = defaultRoom;
        World.print(currentRoom);
        
        // Start game
        while (playing) {
        	Commands.processCommand(Input.getCommand());
        }
        System.exit(0);
    }
}
