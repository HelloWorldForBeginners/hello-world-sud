package com.kosn.util;

import java.util.HashMap;
import java.util.Random;

import com.kosn.application.Application;
import com.kosn.data.Equipment;
import com.kosn.data.Inventory;
import com.kosn.entity.Character;
import com.kosn.entity.Item;
import com.kosn.entity.NonPlayer;
import com.kosn.entity.Player;
import com.kosn.entity.Room;
import com.kosn.entity.defaults.NonPlayerDefaults;

public class Commands {
	final static String noGo = "You can't go that way.";
	private static String previousCommand = "";
	private static String previousTarget = "";
	private static Room nextRoom;
	private static Room thisRoom;
	private static Player player;
	
	private static HashMap<String, Runnable> commands = new HashMap<String, Runnable>() {{
		put("north", () -> Commands.exitRoom("north", Input.target));
    	put("n", () -> Commands.exitRoom("north", Input.target));
    	put("south", () -> Commands.exitRoom("south", Input.target));
    	put("s", () -> Commands.exitRoom("south", Input.target));
    	put("east", () -> Commands.exitRoom("east", Input.target));
    	put("e", () -> Commands.exitRoom("east", Input.target));
    	put("west", () -> Commands.exitRoom("west", Input.target));
    	put("w", () -> Commands.exitRoom("west", Input.target));
    	
    	put("i", () -> Commands.showInventory());
    	put("inv", () -> Commands.showInventory());
    	put("inventory", () -> Commands.showInventory());
    	
    	put("equip", () -> Commands.showEquipment(Input.target));
    	put("equipment", () -> Commands.showEquipment(Input.target));
    	
    	put("unequip", () -> Commands.removeEquipment(Input.target));
    	
    	put("player", () -> Commands.playerStatus());
    	put("p", () -> Commands.playerStatus());
    	put("get", () -> Commands.addToInventory(Input.target));
    	put("put", () -> Commands.removeFromInventory(Input.target));
    	put("attack", () -> Commands.attack(Input.target));
    	put("look", () -> Commands.roomStatus());
    	put("check", () -> Commands.checkThing(Input.target));
    	put("quit", () -> Commands.quitGame());
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
			new Thread(thingToRun).start();
		} else {
    		System.out.println("You can't do that.");
    	}
	}
	
	public static Runnable exitRoom(String direction, String target) {
		Commands.thisRoom = Application.getCurrentRoom();
		Commands.nextRoom = thisRoom.getExits().get(direction);
		Commands.player = Application.getPlayer();
		
		if (nextRoom == null) {
			System.out.println(noGo);
			return null;
		}
		
		if (Application.getCombat() == false) {
			changeRooms();
		} else {
			executeEscapeRoll();
		}

		return null;
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

	public static Runnable showInventory() {
		Inventory.print(Application.getInventory());
		return null;
	}
	
	public static Runnable showEquipment(String target) {
		if (target != "") {
			Equipment.equipItem(target, Application.getPlayer());
		} else {
			Equipment.print(Application.getEquipment());
		}
		return null;
	}
	
	public static Runnable removeEquipment(String target) {
		Equipment.unequipItem(target, Application.getPlayer());
		return null;
	}
	
	public static Runnable playerStatus() {
		System.out.println(Application.getPlayer().toString());
		Character.printInventory(Application.getPlayer().getInventory());
		Character.printEquipment(Application.getPlayer().getEquipment());
		return null;
	}
	
	public static Runnable addToInventory(String target) {
		Inventory.getItem(target, Application.getPlayer(), Application.getCurrentRoom());
		return null;
	}
	
	public static Runnable removeFromInventory(String target) {
		Inventory.putItem(target, Application.getPlayer(), Application.getCurrentRoom());
		return null;
	}

	public static Runnable attack(String target) {
		Combat.attackNonPlayer(target, Application.getCurrentRoom(), Application.getPlayer());
		return null;
	}
	
	public static Runnable roomStatus() {
		Application.getCurrentRoom().printRoom();
		return null;
	}
	
	public static Runnable checkThing(String target) {
		//tries to look at the NonPlayer first, then looks at the items in the room
		if (NonPlayer.printNonPlayerInfo(target, Application.getCurrentRoom()).equals("")) {
			Item.printItemInfo(target, Application.getCurrentRoom());
		}
		return null;
	}
	
	public static Runnable quitGame() {
		System.out.println("Goodbye!");
		Application.setPlaying(false);
		return null;
	}
}
