package com.knightsofsomethingnotable;

import java.util.HashMap;

public class Commands {
	final static String noGo = "You can't go that way.";
	
	static HashMap<String, Runnable> commands = new HashMap<String, Runnable>() {{
		put("north", () -> Commands.goNorth());
    	put("n", () -> Commands.goNorth());
    	put("south", () -> Commands.goSouth());
    	put("s", () -> Commands.goSouth());
    	put("east", () -> Commands.goEast());
    	put("e", () -> Commands.goEast());
    	put("west", () -> Commands.goWest());
    	put("w", () -> Commands.goWest());
    	
    	put("northwdest", () -> Commands.goNorthWest());

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
    	System.out.println("---------------------------------------------------");
	}
	
	
	public static Runnable goNorth() {
//		System.out.println("Entered goNorth");
		if (Main.y > 0 && Main.combat == false) {
			Main.y--;
			System.out.println("You move to the north");
			System.out.println();
			World.print(Main.room, Main.x, Main.y);
		} else {
			if (Main.combat == true) {
				System.out.println("You're in combat!");
				//TODO: process an attack round? Additional command FLEE, pick random available direction?
			} else {
				System.out.println(noGo);
			}
		}
		return null;
	}

	
	public static Runnable goNorthWest() {
		System.out.println("Entered goNorthWest");
		return null;
	}

	
	public static Runnable goSouth() {
//		System.out.println("Entered goSouth");
		if (Main.y < Main.HEIGHT - 1 && Main.combat == false) {
			Main.y++;
			System.out.println("You move to the south");
			System.out.println();
			World.print(Main.room, Main.x, Main.y);
		} else {
			if (Main.combat == true) {
				System.out.println("You're in combat!");
				//TODO: process an attack round? Additional command FLEE, pick random available direction?
			} else {
				System.out.println(noGo);
			}
		}
		return null;
	}
		
	public static Runnable goEast() {
//		System.out.println("Entered goEast");
		if (Main.x < Main.WIDTH - 1 && Main.combat == false) {
			Main.x++;
			System.out.println("You move to the east");
			System.out.println();
			World.print(Main.room, Main.x, Main.y);
		} else {
			if (Main.combat == true) {
				System.out.println("You're in combat!");
				//TODO: process an attack round? Additional command FLEE, pick random available direction?
			} else {
				System.out.println(noGo);
			}
		}
		return null;
	}

	public static Runnable goWest() {
//		System.out.println("Entered goWest");		
		if (Main.x > 0 && Main.combat == false) {
			Main.x--;
			System.out.println("You move to the west");
			System.out.println();
			World.print(Main.room, Main.x, Main.y);
		} else {
			if (Main.combat == true) {
				System.out.println("You're in combat!");
				//TODO: process an attack round? Additional command FLEE, pick random available direction?
			} else {
				System.out.println(noGo);
			}
		}
		return null;
	}
	
	public static Runnable showInventory() {
//		System.out.println("Entered showInventory");
		Inventory.print(Main.inventory);
		return null;
	}
	
	public static Runnable equipment(String target) {
//		System.out.println("Entered equipment");
		if (target != "") {
			Equipment.equipItem(Main.x, Main.y, target, Main.player);
		} else {
			Equipment.print(Main.equipment);
		}
		return null;
	}
	
	public static Runnable removeEquipment(String target) {
//		System.out.println("Entered removeEquipment");
		Equipment.unequipItem(Main.x, Main.y, target, Main.player);
		return null;
	}
	
	public static Runnable playerStatus() {
//		System.out.println("Entered playerStatus");
		Player.printPlayerInfo(Main.player);
		return null;
	}
	
	public static Runnable addToInventory(String target) {
//		System.out.println("Entered addToInventory");
		Inventory.getItem(Main.x, Main.y, target, Main.player, Main.room);
		return null;
	}
	
	public static Runnable removeFromInventory(String target) {
//		System.out.println("Entered removeFromInventory");
		Inventory.putItem(Main.x, Main.y, target, Main.player, Main.room);
		return null;
	}

	public static Runnable attack(String target) {
//		System.out.println("Entered attack");

		NonPlayer.attackNonPlayer(target, Main.room, Main.x, Main.y, Main.player);
		return null;
	}
	
	public static Runnable roomStatus() {
//		System.out.println("Entered roomStatus");
		World.print(Main.room, Main.x, Main.y);
		return null;
	}
	
	public static Runnable checkThing(String target) {
//		System.out.println("Entered checkThing");
		//tries to look at the NonPlayer first, then looks at the items in the room
		if (NonPlayer.printNonPlayerInfo(target, Main.room, Main.x, Main.y).equals("")) {
			Item.printItemInfo(target, Main.room, Main.x, Main.y);
		}
		return null;
	}
	
	public static Runnable quitGame() {
//		System.out.println("Entered quitGame");
		System.out.println("Goodbye!");
		Main.playing = false;
		return null;
	}
	
}
