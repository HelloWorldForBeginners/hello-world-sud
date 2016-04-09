package com.knightsofsomethingnotable;

import java.util.HashMap;

public class Commands {
	final static String noGo = "You can't go that way.";
	
	static HashMap<String, Runnable> commands = new HashMap<String, Runnable>() {{
		put("north", () -> Commands.exitRoom("north"));
    	put("n", () -> Commands.exitRoom("north"));
    	put("south", () -> Commands.exitRoom("south"));
    	put("s", () -> Commands.exitRoom("south"));
    	put("east", () -> Commands.exitRoom("east"));
    	put("e", () -> Commands.exitRoom("east"));
    	put("west", () -> Commands.exitRoom("west"));
    	put("w", () -> Commands.exitRoom("west"));
    	
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
		Runnable thingToRun = commands.get(command);
    	
    	if (thingToRun != null) {
    		new Thread(thingToRun).start();
    	}
    	else {
    		System.out.println("You can't do that.");
    	}

	}
	
	public static Runnable exitRoom(String direction) {
		Room thisRoom = Main.currentRoom;
		Room nextRoom = thisRoom.getExits().get(direction);
		
		if (nextRoom != null) {
			if (Main.combat == false) {
				Main.currentRoom = nextRoom;
				World.print(nextRoom);
			} else {
				System.out.println("You're in combat!");
			}
		} else {
			System.out.println(noGo);
		}
		return null;
	}
	
	public static Runnable showInventory() {
		Inventory.print(Main.inventory);
		return null;
	}
	
	public static Runnable equipment(String target) {
		if (target != "") {
			Equipment.equipItem(target, Main.player);
		} else {
			Equipment.print(Main.equipment);
		}
		return null;
	}
	
	public static Runnable removeEquipment(String target) {
		Equipment.unequipItem(target, Main.player);
		return null;
	}
	
	public static Runnable playerStatus() {
		Player.printPlayerInfo(Main.player);
		return null;
	}
	
	public static Runnable addToInventory(String target) {
		Inventory.getItem(target, Main.player, Main.currentRoom);
		return null;
	}
	
	public static Runnable removeFromInventory(String target) {
		Inventory.putItem(target, Main.player, Main.currentRoom);
		return null;
	}

	public static Runnable attack(String target) {
		NonPlayer.attackNonPlayer(target, Main.currentRoom, Main.player);
		return null;
	}
	
	public static Runnable roomStatus() {
		World.print(Main.currentRoom);
		return null;
	}
	
	public static Runnable checkThing(String target) {
		//tries to look at the NonPlayer first, then looks at the items in the room
		if (NonPlayer.printNonPlayerInfo(target, Main.currentRoom).equals("")) {
			Item.printItemInfo(target, Main.currentRoom);
		}
		return null;
	}
	
	public static Runnable quitGame() {
		System.out.println("Goodbye!");
		Main.playing = false;
		return null;
	}
}
