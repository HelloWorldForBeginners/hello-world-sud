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
import com.kosn.data.dto.ConsumableType;
import com.kosn.data.dto.Equipment;
import com.kosn.data.dto.Item;
import com.kosn.data.dto.NonPlayer;
import com.kosn.data.dto.Player;
import com.kosn.data.dto.Room;

public class CommandProcessor {
	final String noGo = "You can't go that way. Available exits:";
	final String noAttackTarget = "Attack what?";
	final String noTargetCreatureFound = "Attack what?";
	final String noCommand = "You can't do that. Type help to see available commands.";
	final String noCreatures = "Nothing to attack.";
	private String previousCommand = "";
	private String previousTarget = "";
	private Room nextRoom;
	private Room thisRoom;
	private Player player;
	private ArrayList<NonPlayer> thisRoomCreatures = new ArrayList<NonPlayer>();
	private ArrayList<NonPlayer> nextRoomCreatures = new ArrayList<NonPlayer>();
	
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
	    	put("use", () -> useItem(Input.target));
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
	
	protected Map<String, Item> getValidItems(String target) {
		Map<String, Item> itemsFound = new HashMap<String, Item>();		
		Item targetItem = thisRoom.getItemIfExists(target);
		if (targetItem != null) {
			itemsFound.put("room", targetItem);
		}
		targetItem = player.checkInventory(target);
		if (targetItem != null) {
			itemsFound.put("inventory", targetItem);
		}
		return itemsFound;
	}
	
	protected void useItem(String target) {
		//check inventory for item
		
		Map<String, Item> availableItems = getValidItems(target);
		
		Item itemToConsume = availableItems.get("inventory");
		
		if (itemToConsume == null) {
			System.out.println(String.format("You don't have any %s", target));
		}
		
		if (ConsumableType.valueOf(itemToConsume.getType()) == null) {
			System.out.println("You can't use that item.");
		}
		
		switch (itemToConsume.getEffectType()) {
		case health:
			int currentHitPoints = player.getHitPoints();
			int maxHitPoints = player.getMaxHitPoints();
			if (currentHitPoints < maxHitPoints) {
				int healthAfterRecover = currentHitPoints + itemToConsume.getEffectValue();
				int finalHealth = healthAfterRecover <= maxHitPoints ? healthAfterRecover : maxHitPoints;
				player.setHitPoints(finalHealth);
				//remove item from inventory
				System.out.format("You have recovered %d health!", finalHealth - currentHitPoints);
				break;
			}
			System.out.println("You're already at full health.");
			break;
		case attack:
			player.setAttack(player.getAttack() + itemToConsume.getEffectValue());
			break;
		case defense:
			player.setDefense(player.getDefense() + itemToConsume.getEffectValue());
			break;
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
				if (player.calculateMoneyLost(0.05) > 0) {
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
		System.out.printf("You have escaped but lost %d money.", player.calculateMoneyLost(0.05));
		System.out.println();
		player.adjustMoney(-player.calculateMoneyLost(0.05));
		Application.setCombat(false);
		changeRooms(); 
	}

	private void escapeWithDamage() {
		if (Combat.processNonPlayerAttack(player, Application.getCurrentCombatTarget()).equals("respawned")){
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
//		if (nextRoomCreatures.isEmpty()) {
//			nextRoomCreatures.add(new NonPlayer(new NonPlayerDefaults()));
//		}
//		else {
//			nextRoom.printCreatures();
//		}
	}

	public void showInventory() {
		player.printInventory();
	}
	
	public void showEquipment(String target) {
		if (target != "") {
			Equipment.equipItem(target, Application.getPlayer());
		} else {
			player.printEquipment();
		}
	}
	
	public void removeEquipment(String target) {
		Equipment.unequipItem(target, Application.getPlayer());
	}
	
	public void playerStatus() {
		player.printStatus();
	}
	
	public void addToInventory(String target) {
		player.putItemInInventory(target, thisRoom);
	}
	
	public void removeFromInventory(String target) {
		player.removeItemFromInventory(target, thisRoom);
		//Inventory.putItem(target, Application.getPlayer(), Application.getCurrentRoom());
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
		thisRoom.printRoom();
	}
	
	public void checkThing(String target) {
		NonPlayer targetNonPlayer = thisRoom.getNonPlayer(target);
		Item targetItem = thisRoom.getItemIfExists(target);
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
