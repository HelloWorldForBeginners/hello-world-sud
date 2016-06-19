package com.kosn.application;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.kosn.entity.Item;
import com.kosn.entity.NonPlayer;
import com.kosn.entity.Player;
import com.kosn.entity.Room;
import com.kosn.entity.defaults.PlayerDefaults;
import com.kosn.util.CommandProcessor;
import com.kosn.util.Input;
import com.kosn.util.World;

public class Application {
	
    private static boolean playing = true;
    private static boolean combat = false;
    
    private static Room currentRoom = null;
    private static Room defaultRoom = null;
    
    private static NonPlayer currentCombatTarget = null;
    
    public static Map<String, Room> rooms = new HashMap<String, Room>();
    
	// inventory and equipment 
    private static ArrayList<Item> inventory = new ArrayList<>();
    private static HashMap<String, Item> equipment = new HashMap<String, Item>();
    
    private static Player player = new Player(new PlayerDefaults());
    
    private static CommandProcessor cp = CommandProcessor.getInstance();

    public static void main(String args[]) {
    	System.out.println("Welcome to The Knights of Something Notable!\n");
    	
    	// Build rooms
    	
    	rooms = World.build(); 
        currentRoom = defaultRoom = rooms.entrySet().iterator().next().getValue();
        currentRoom.printRoom();

        // Start game
        while (playing) {
        	cp.processCommand(Input.getCommand());
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

	public static Player getPlayer() {
		return player;
	}

	public static void setPlayer(Player player) {
		Application.player = player;
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

	public static Map<String, Room> getRooms() {
		return rooms;
	}

	public static void setRooms(HashMap<String, Room> rooms) {
		Application.rooms = rooms;
	}
}
