package com.kosn.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.text.WordUtils;

import com.kosn.application.Application;
import com.kosn.data.dto.Equipment;
import com.kosn.data.dto.Inventory;
import com.kosn.entity.Character;
import com.kosn.entity.Item;
import com.kosn.entity.NonPlayer;
import com.kosn.entity.Player;
import com.kosn.entity.Room;
import com.kosn.entity.defaults.NonPlayerDefaults;

public class CommandProcessor {
	final static String noGo = "You can't go that way. Available exits:";
	final static String noAttackTarget = "Attack what?";
	final static String noTargetCreatureFound = "Attack what?";
	final static String noCommand = "You can't do that. Type help to see available commands.";
	final static String noCreatures = "Nothing to attack.";
	private static String previousCommand = "";
	private static String previousTarget = "";
	private static Room nextRoom;
	private static Room thisRoom;
	private static Player player;
	private static ArrayList<NonPlayer> thisRoomCreatures = new ArrayList<NonPlayer>();
	private static ArrayList<NonPlayer> nextRoomCreatures = new ArrayList<NonPlayer>();
	
	//singleton
	private static CommandProcessor instance = null;
	protected CommandProcessor() {
	}
	public static CommandProcessor getInstance() {
		if(instance == null) {
			instance = new CommandProcessor();
		}
		return instance;
	}

	private Map<String, Runnable> commands = new HashMap<String, Runnable>() {
		private static final long serialVersionUID = 1L;
		{
			put("north", () -> exitRoom("north", Input.target));
	    	put("n", () -> exitRoom("north", Input.target));
	    	put("south", () -> exitRoom("south", Input.target));
	    	put("s", () -> exitRoom("south", Input.target));
	    	put("east", () -> exitRoom("east", Input.target));
	    	put("e", () -> exitRoom("east", Input.target));
	    	put("west", () -> exitRoom("west", Input.target));
	    	put("w", () -> exitRoom("west", Input.target));
	    	put("warp", () -> warp(Input.target));
	    	
	    	put("i", () -> showInventory());
	    	put("inv", () -> showInventory());
	    	put("inventory", () -> showInventory());
	    	
	    	put("equip", () -> showEquipment(Input.target));
	    	put("equipment", () -> showEquipment(Input.target));
	    	
	    	put("unequip", () -> removeEquipment(Input.target));
	    	
	    	put("player", () -> playerStatus());
	    	put("p", () -> playerStatus());
	    	put("get", () -> addToInventory(Input.target));
	    	put("put", () -> removeFromInventory(Input.target));
	    	put("attack", () -> attack(Input.target));
	    	put("look", () -> roomStatus());
	    	put("check", () -> checkThing(Input.target));
	    	put("quit", () -> quitGame());
	    	put("commands", () -> printCommands());
	    	put("help", () -> printCommands());
		}
	};
	
	public void processCommand(String command) {
		thisRoom = Application.getCurrentRoom();
		thisRoomCreatures = thisRoom.getCreatures();
		player = Application.getPlayer();

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
			System.out.println(noCommand);
    	}
	}
	
	protected void warp(String target) {
		target = WordUtils.capitalize(target);
		
		nextRoom = Application.getRooms().get(target);
		
		if (nextRoom == null) {
			System.out.println(noGo);
			thisRoom.printExits();
			return;
		}
		changeRooms();
	}

	protected void printCommands() {
		Set<String> commandSet = new HashSet<String>();
		commandSet = commands.keySet();
		List<String> availableCommands = new ArrayList<String>(commandSet);
		Collections.sort(availableCommands); 
		
		System.out.println();
		for (String command : availableCommands) {
			System.out.println(command);
		}
	}

	public void exitRoom(String direction, String target) {
		nextRoom = thisRoom.getExits().get(Directions.valueOf(direction));
		
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
	
	private void executeEscapeRoll() {
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

	private void escape() {
		System.out.println("You have escaped!");
		Application.setCombat(false);
		changeRooms();
	}

	private void escapeWithMoneyLoss() {
		System.out.printf("You have escaped but lost %d money.", Player.calculateMoneyLost(0.05));
		System.out.println();
		player.adjustMoney(-Player.calculateMoneyLost(0.05));
		Application.setCombat(false);
		changeRooms(); 
	}

	private void escapeWithDamage() {
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

	private void changeRooms() {
		Application.setCurrentCombatTarget(null);
		Application.setCurrentRoom(nextRoom);
		checkRoomMonsters();
		nextRoom.printRoom();
	}

	private void checkRoomMonsters() {
		nextRoomCreatures = nextRoom.getCreatures();
		if (nextRoomCreatures.isEmpty()) {
			nextRoomCreatures.add(new NonPlayer(new NonPlayerDefaults()));
		}
//		else {
//			nextRoom.printCreatures();
//		}
	}

	public void showInventory() {
		Inventory.print(Application.getInventory());
	}
	
	public void showEquipment(String target) {
		if (target != "") {
			Equipment.equipItem(target, Application.getPlayer());
		} else {
			Equipment.print(Application.getEquipment());
		}
	}
	
	public void removeEquipment(String target) {
		Equipment.unequipItem(target, Application.getPlayer());
	}
	
	public void playerStatus() {
		System.out.println(Application.getPlayer().toString());
		Character.printInventory(Application.getPlayer().getInventory());
		Character.printEquipment(Application.getPlayer().getEquipment());
	}
	
	public void addToInventory(String target) {
		Inventory.getItem(target, Application.getPlayer(), Application.getCurrentRoom());
	}
	
	public void removeFromInventory(String target) {
		Inventory.putItem(target, Application.getPlayer(), Application.getCurrentRoom());
	}

	public void attack(String target) {
		NonPlayer attackTarget = thisRoom.getNonPlayer(target); 
		if (thisRoomCreatures.isEmpty()) {
			System.out.println(noCreatures);
			return;
		} else if (attackTarget == null) {
        	System.out.println(noTargetCreatureFound);
    		thisRoom.printCreatures();
            return;
		} else if (target.equals("")) {
			System.out.println(noAttackTarget);
			thisRoom.printCreatures();
			return;
		}			
		Combat.attackNonPlayer(attackTarget, thisRoom, player);
	}
	
	public void roomStatus() {
		Application.getCurrentRoom().printRoom();
	}
	
	public void checkThing(String target) {
		NonPlayer targetNonPlayer = thisRoom.getNonPlayer(target);
		Item targetItem = thisRoom.getItem(target);
		if (targetNonPlayer != null) {
			targetNonPlayer.printInfo();
		}
		if (targetItem != null) {
			targetItem.printInfo();
		}
	}
	
	public void quitGame() {
		System.out.println("Goodbye!");
		Application.setPlaying(false);
	}

}
