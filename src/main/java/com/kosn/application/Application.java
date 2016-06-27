package com.kosn.application;
import java.util.HashMap;
import java.util.Map;

import com.kosn.data.dto.NonPlayer;
import com.kosn.data.dto.Player;
import com.kosn.data.dto.Room;
import com.kosn.dto.defaults.PlayerDefaults;
import com.kosn.util.CommandProcessor;
import com.kosn.util.Input;
import com.kosn.util.World;

public class Application {
    private static boolean playing = true;
    private static boolean combat = false;
    
    private static Player player = new Player(new PlayerDefaults());
    private static NonPlayer currentCombatTarget = null;
    
    private static World world = World.getInstance();
    private static CommandProcessor cp = CommandProcessor.getInstance();
    
    private static Map<String, Room> rooms = new HashMap<String, Room>();
    private static Room currentRoom = null;
    private static Room defaultRoom = null;
    
    public static void main(String args[]) {
		System.out.println("Welcome to The Knights of Something Notable!\n");
		
		// Build rooms and set default
		rooms = world.buildNewWorld(); 
	    currentRoom = defaultRoom = rooms.entrySet().iterator().next().getValue();
	    currentRoom.printRoom();
	
	    // Start game
	    while (playing) {
	    	cp.processCommand(Input.getCommand());
	    }
	    System.exit(0);
	}

	public static boolean getPlaying() {
		return playing;
	}

	public static void setPlaying(boolean playing) {
		Application.playing = playing;
	}

	public static Player getPlayer() {
		return player;
	}

	public static boolean getCombat() {
		return combat;
	}

	public static void setCombat(boolean combat) {
		Application.combat = combat;
	}

	public static void toggleCombatOff() {
	    setCombat(false);
	    System.out.println();
	    System.out.println("Combat ended");
	    System.out.println();
	}

	public static void toggleCombatOn(NonPlayer nonPlayer) {
		if (!Application.getCombat()) {
			System.out.println("Combat started...");
			System.out.println();
			Application.setCombat(true);
			Application.setCurrentCombatTarget(nonPlayer);
	    }
	}

	public static void setPlayer(Player player) {
		Application.player = player;
	}

	public static NonPlayer getCurrentCombatTarget() {
		return currentCombatTarget;
	}

	public static void setCurrentCombatTarget(NonPlayer nonPlayer) {
		Application.currentCombatTarget = nonPlayer;
	}

	public static Map<String, Room> getRooms() {
		return rooms;
	}

	public static void setRooms(HashMap<String, Room> rooms) {
		Application.rooms = rooms;
	}

	public static Room getCurrentRoom() {
		return currentRoom;
	}

	public static void setCurrentRoom(Room currentRoom) {
		Application.currentRoom = currentRoom;
	}

	public static Room getDefaultRoom() {
		return defaultRoom;
	}

	public static void setDefaultRoom(Room defaultRoom) {
		Application.defaultRoom = defaultRoom;
	}
}
