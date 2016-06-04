package com.kosn.application;
import java.util.ArrayList;
import java.util.HashMap;

import com.kosn.data.World;
import com.kosn.entity.Item;
import com.kosn.entity.NonPlayer;
import com.kosn.entity.Player;
import com.kosn.entity.Room;
import com.kosn.util.Commands;
import com.kosn.util.Input;

public class Application {
	
    private static boolean playing = true;
    private static boolean combat = false;
    
    private static Room currentRoom = null;
    private static Room defaultRoom = null;
    
    private static NonPlayer currentCombatTarget = null;
    
    static final int numRooms = 4;
    public static HashMap<String, Room> rooms = new HashMap<String, Room>();

    // inventory and equipment 
    private static ArrayList<Item> inventory = new ArrayList<>();
    private static HashMap<String, Item> equipment = new HashMap<String, Item>();
    
    //TODO: this seems messy. Change to default values in constructor
    private static Player player = new Player("Matt", "Awesome", 1, 0, 0, 10, 10, 10, 1, 1);


    public static void main(String args[]) {
    	
    	// Build rooms
    	System.out.println("Welcome to The Knights of Something Notable!\n");
        World.build();
        setDefaultRoom(rooms.entrySet().iterator().next().getValue());
        setCurrentRoom(getDefaultRoom());
        Room.print(getCurrentRoom());
        
        // Start game
        while (playing) {
        	Commands.processCommand(Input.getCommand());
        }
        System.exit(0);
    }


	public static Room getCurrentRoom() {
		return currentRoom;
	}


	public static void setCurrentRoom(Room currentRoom) {
		Application.currentRoom = currentRoom;
	}


	public static boolean getCombat() {
		return combat;
	}


	public static void setCombat(boolean combat) {
		Application.combat = combat;
	}


	public static ArrayList<Item> getInventory() {
		return inventory;
	}


	public static void setInventory(ArrayList<Item> inventory) {
		Application.inventory = inventory;
	}


	public static Player getPlayer() {
		return player;
	}


	public static void setPlayer(Player player) {
		Application.player = player;
	}


	public static HashMap<String, Item> getEquipment() {
		return equipment;
	}


	public static void setEquipment(HashMap<String, Item> equipment) {
		Application.equipment = equipment;
	}


	public static boolean getPlaying() {
		return playing;
	}


	public static void setPlaying(boolean playing) {
		Application.playing = playing;
	}


	public static Room getDefaultRoom() {
		return defaultRoom;
	}


	public static void setDefaultRoom(Room defaultRoom) {
		Application.defaultRoom = defaultRoom;
	}

	
	public static NonPlayer getCurrentCombatTarget() {
		return currentCombatTarget;
	}

	
	public static void setCurrentCombatTarget(NonPlayer nonPlayer) {
		Application.currentCombatTarget = nonPlayer;
	}


	public static void toggleCombatOn(NonPlayer nonPlayer) {

		if (!Application.getCombat()) {
    		System.out.println("Combat started...");
    		System.out.println();
    		Application.setCombat(true);
    		Application.setCurrentCombatTarget(nonPlayer);
        }
	}
	

	public static void toggleCombatOff() {
        
        Application.setCombat(false);
        System.out.println();
        System.out.println("Combat ended");
        System.out.println();
	}


}
