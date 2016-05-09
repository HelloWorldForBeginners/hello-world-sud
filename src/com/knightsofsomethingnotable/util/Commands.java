package com.knightsofsomethingnotable.util;

import java.util.HashMap;
import java.util.Random;

import com.knightsofsomethingnotable.entities.Item;
import com.knightsofsomethingnotable.entities.NonPlayer;
import com.knightsofsomethingnotable.entities.Player;
import com.knightsofsomethingnotable.main.Main;
import com.knightsofsomethingnotable.management.Equipment;
import com.knightsofsomethingnotable.management.Inventory;
import com.knightsofsomethingnotable.management.Room;
import com.knightsofsomethingnotable.management.World;

public class Commands {
	final static String noGo = "You can't go that way.";
	private static String previousCommand = "";
	private static String previousTarget = "";
	private static Room nextRoom;
	private static Room thisRoom;
	private static Player player;
	
	static HashMap<String, Runnable> commands = new HashMap<String, Runnable>() {{
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
    	
    	put("equip", () -> Commands.equipment(Input.target));
    	put("equipment", () -> Commands.equipment(Input.target));
    	
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
		Commands.thisRoom = Main.getCurrentRoom();
		Commands.nextRoom = thisRoom.getExits().get(direction);
		Commands.player = Main.getPlayer();
		
		if (nextRoom == null) {
			System.out.println(noGo);
			return null;
		}
		
		if (Main.getCombat() == false) {
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
		Main.setCombat(false);
		changeRooms();
	}

	private static void escapeWithMoneyLoss() {
		System.out.printf("You have escaped but lost %d money.", Player.calculateMoneyLost(0.05));
		System.out.println();
		player.adjustMoney(-Player.calculateMoneyLost(0.05));
		Main.setCombat(false);
		changeRooms(); 
	}

	private static void escapeWithDamage() {
		if (Combat.processNonPlayerAttack(Main.getPlayer(), Main.getCurrentCombatTarget()).equals("respawned")){
			return;
		}
		System.out.println("You have escaped! Coward.");
		Main.setCombat(false);
		changeRooms();
	}

	private static void unableToEscape() {
		System.out.println("You were unable to escape!");
		Combat.processNonPlayerAttack(Main.getPlayer(), Main.getCurrentCombatTarget());
	}

	private static void changeRooms() {
		Main.setCurrentCombatTarget(null);
		Main.setCurrentRoom(nextRoom);
		World.print(nextRoom);
	}

	public static Runnable showInventory() {
		Inventory.print(Main.getInventory());
		return null;
	}
	
	public static Runnable equipment(String target) {
		if (target != "") {
			Equipment.equipItem(target, Main.getPlayer());
		} else {
			Equipment.print(Main.getEquipment());
		}
		return null;
	}
	
	public static Runnable removeEquipment(String target) {
		Equipment.unequipItem(target, Main.getPlayer());
		return null;
	}
	
	public static Runnable playerStatus() {
		Player.printPlayerInfo(Main.getPlayer());
		return null;
	}
	
	public static Runnable addToInventory(String target) {
		Inventory.getItem(target, Main.getPlayer(), Main.getCurrentRoom());
		return null;
	}
	
	public static Runnable removeFromInventory(String target) {
		Inventory.putItem(target, Main.getPlayer(), Main.getCurrentRoom());
		return null;
	}

	public static Runnable attack(String target) {
		Combat.attackNonPlayer(target, Main.getCurrentRoom(), Main.getPlayer());
		return null;
	}
	
	public static Runnable roomStatus() {
		World.print(Main.getCurrentRoom());
		return null;
	}
	
	public static Runnable checkThing(String target) {
		//tries to look at the NonPlayer first, then looks at the items in the room
		if (NonPlayer.printNonPlayerInfo(target, Main.getCurrentRoom()).equals("")) {
			Item.printItemInfo(target, Main.getCurrentRoom());
		}
		return null;
	}
	
	public static Runnable quitGame() {
		System.out.println("Goodbye!");
		Main.setPlaying(false);
		return null;
	}
}
