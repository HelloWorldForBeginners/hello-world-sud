package com.kosn.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.kosn.application.Application;
import com.kosn.data.dto.Equipment;
import com.kosn.data.dto.Inventory;
import com.kosn.entity.Character;
import com.kosn.entity.Item;
import com.kosn.entity.NonPlayer;
import com.kosn.entity.Player;
import com.kosn.entity.Room;
import com.kosn.entity.defaults.NonPlayerDefaults;

public class CommandProcessing {
	final static String noGo = "You can't go that way.";
	private static String previousCommand = "";
	private static String previousTarget = "";
	private static Room nextRoom;
	private static Room thisRoom;
	private static Player player;
	
	private static Map<String, Runnable> commands = new HashMap<String, Runnable>() {{
		put("north", () -> CommandProcessing.exitRoom("north", Input.target));
    	put("n", () -> CommandProcessing.exitRoom("north", Input.target));
    	put("south", () -> CommandProcessing.exitRoom("south", Input.target));
    	put("s", () -> CommandProcessing.exitRoom("south", Input.target));
    	put("east", () -> CommandProcessing.exitRoom("east", Input.target));
    	put("e", () -> CommandProcessing.exitRoom("east", Input.target));
    	put("west", () -> CommandProcessing.exitRoom("west", Input.target));
    	put("w", () -> CommandProcessing.exitRoom("west", Input.target));
    	
    	put("i", () -> CommandProcessing.showInventory());
    	put("inv", () -> CommandProcessing.showInventory());
    	put("inventory", () -> CommandProcessing.showInventory());
    	
    	put("equip", () -> CommandProcessing.showEquipment(Input.target));
    	put("equipment", () -> CommandProcessing.showEquipment(Input.target));
    	
    	put("unequip", () -> CommandProcessing.removeEquipment(Input.target));
    	
    	put("player", () -> CommandProcessing.playerStatus());
    	put("p", () -> CommandProcessing.playerStatus());
    	put("get", () -> CommandProcessing.addToInventory(Input.target));
    	put("put", () -> CommandProcessing.removeFromInventory(Input.target));
    	put("attack", () -> CommandProcessing.attack(Input.target));
    	put("look", () -> CommandProcessing.roomStatus());
    	put("check", () -> CommandProcessing.checkThing(Input.target));
    	put("quit", () -> CommandProcessing.quitGame());
    	put("commands", () -> CommandProcessing.printCommands());
	}};
	
	public static void processCommand(String command) {

		Runnable thingToRun = null;

		if (command.equals("prev") && !previousCommand.equals("")) {
			thingToRun = commands.get(previousCommand);
			Input.target = previousTarget;
		} else {
			thingToRun = commands.get(command);
			previousCommand = command;
			previousTarget = Input.target;

		}

		if (thingToRun != null) {
			thingToRun.run();
		} else {
    		System.out.println("You can't do that.");
    	}
	}
	
	protected static void printCommands() {
		Set<String> commandSet = new HashSet<String>();
		commandSet = commands.keySet();
		List<String> availableCommands = new ArrayList<String>(commandSet);
		Collections.sort(availableCommands); 
		
		System.out.println();
		for (String command : availableCommands) {
			System.out.println(command);
		}
		
	}

	public static void exitRoom(String direction, String target) {
		CommandProcessing.thisRoom = Application.getCurrentRoom();
		CommandProcessing.nextRoom = thisRoom.getExits().get(Directions.valueOf(direction));
		CommandProcessing.player = Application.getPlayer();
		
		if (nextRoom == null) {
			System.out.println(noGo);
			return;
		}
		
		if (Application.getCombat() == false) {
			changeRooms();
		} else {
			executeEscapeRoll();
		}
	}
	
	private static void executeEscapeRoll() {
		Random r = new Random();
		
		System.out.println("You're in combat!");
		System.out.println("Trying to escape...!");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		switch (r.nextInt(6)) {
			case 0:
				unableToEscape();
				break;
			//FALLTHROUGH
			case 1:
			case 2:
				escapeWithDamage();
				break;
			//FALLTHROUGH 
			case 3:
			case 4:
				if (Player.calculateMoneyLost(0.05) > 0) {
					escapeWithMoneyLoss();
				} else {
					escape();
				}
				break;
			case 5:
				escape();
				break;
		}
	}


	private static void escape() {
		System.out.println("You have escaped!");
		Application.setCombat(false);
		changeRooms();
	}

	private static void escapeWithMoneyLoss() {
		System.out.printf("You have escaped but lost %d money.", Player.calculateMoneyLost(0.05));
		System.out.println();
		player.adjustMoney(-Player.calculateMoneyLost(0.05));
		Application.setCombat(false);
		changeRooms(); 
	}

	private static void escapeWithDamage() {
		if (Combat.processNonPlayerAttack(Application.getPlayer(), Application.getCurrentCombatTarget()).equals("respawned")){
			return;
		}
		System.out.println("Running away are you?");
		Application.setCombat(false);
		changeRooms();
	}

	private static void unableToEscape() {
		System.out.println("You were unable to escape!");
		Combat.processNonPlayerAttack(Application.getPlayer(), Application.getCurrentCombatTarget());
	}

	private static void changeRooms() {
		
		Application.setCurrentCombatTarget(null);
		Application.setCurrentRoom(nextRoom);
		checkRoomMonsters();
		nextRoom.printRoom();
	}

	private static void checkRoomMonsters() {
		
		if (nextRoom.getCreatures().isEmpty()) {
			nextRoom.getCreatures().add(new NonPlayer(new NonPlayerDefaults()));
		}
//		else {
//			nextRoom.printCreatures();
//		}
	}

	public static void showInventory() {
		Inventory.print(Application.getInventory());
	}
	
	public static void showEquipment(String target) {
		if (target != "") {
			Equipment.equipItem(target, Application.getPlayer());
		} else {
			Equipment.print(Application.getEquipment());
		}
		
	}
	
	public static void removeEquipment(String target) {
		Equipment.unequipItem(target, Application.getPlayer());
		
	}
	
	public static void playerStatus() {
		System.out.println(Application.getPlayer().toString());
		Character.printInventory(Application.getPlayer().getInventory());
		Character.printEquipment(Application.getPlayer().getEquipment());
		
	}
	
	public static void addToInventory(String target) {
		Inventory.getItem(target, Application.getPlayer(), Application.getCurrentRoom());
		
	}
	
	public static void removeFromInventory(String target) {
		Inventory.putItem(target, Application.getPlayer(), Application.getCurrentRoom());
		
	}

	public static void attack(String target) {
		Combat.attackNonPlayer(target, Application.getCurrentRoom(), Application.getPlayer());
		
	}
	
	public static void roomStatus() {
		Application.getCurrentRoom().printRoom();
		
	}
	
	public static void checkThing(String target) {
		//tries to look at the NonPlayer first, then looks at the items in the room
		if (NonPlayer.printNonPlayerInfo(target, Application.getCurrentRoom()).equals("")) {
			Item.printItemInfo(target, Application.getCurrentRoom());
		}
		
	}
	
	public static void quitGame() {
		System.out.println("Goodbye!");
		Application.setPlaying(false);
		
	}
}
