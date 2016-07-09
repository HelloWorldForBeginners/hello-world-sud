package com.kosn.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.text.WordUtils;

import com.kosn.application.Application;
import com.kosn.entity.Equipment;
import com.kosn.entity.Item;
import com.kosn.entity.ItemType;
import com.kosn.entity.NonPlayer;
import com.kosn.entity.Player;
import com.kosn.entity.Room;

public class CommandProcessor {
	private static final String noGo = "You can't go that way. Available exits:";
	private static final String noAttackTarget = "Attack what?";
	private static final String noTargetCreatureFound = "Attack what?";
	private static final String noCommand = "You can't do that. Type help to see available commands.";
	private static final String noCreatures = "Nothing to attack.";
	private String previousCommand = "";
	private String previousTarget = "";
	private Room nextRoom;
	private Room thisRoom;
	private Player player = Application.getPlayer();
	private List<NonPlayer> thisRoomCreatures = new ArrayList<NonPlayer>();
	private World world = World.getInstance();
	private CommandProcessorHelper cph = CommandProcessorHelper.getInstance();
	private String target;
	private static final String INVENTORY_PHRASE = "in your inventory.";
	private static final String EQUIPMENT_PHRASE = "that you have equipped.";
	private static final String ROOM_PHRASE = "in the room.";
	
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
			put("north", () -> exitRoom("north"));
	    	put("n", () -> exitRoom("north"));
	    	put("south", () -> exitRoom("south"));
	    	put("s", () -> exitRoom("south"));
	    	put("east", () -> exitRoom("east"));
	    	put("e", () -> exitRoom("east"));
	    	put("west", () -> exitRoom("west"));
	    	put("w", () -> exitRoom("west"));
	    	put("warp", () -> warp());
	    	
	    	put("i", () -> showInventory());
	    	put("inv", () -> showInventory());
	    	put("inventory", () -> showInventory());
	    	
	    	put("equip", () -> showEquipment());
	    	put("equipment", () -> showEquipment());
	    	
	    	put("unequip", () -> removeEquipment());
	    	
	    	put("player", () -> playerStatus());
	    	put("p", () -> playerStatus());
	    	put("get", () -> addToInventory());
	    	put("put", () -> removeFromInventory());
	    	put("attack", () -> attack());
	    	put("combat", () -> showCombatInfo());
	    	put("look", () -> roomStatus());
	    	put("check", () -> checkThing());
	    	put("quit", () -> quitGame());
	    	put("commands", () -> printCommands());
	    	put("help", () -> printCommands());
	    	put("use", () -> useItem());
		}
	};
	
	public void processCommand(String command) {
		thisRoom = world.getCurrentRoom();
		thisRoomCreatures = thisRoom.getCreatures();
		target = Input.target;

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
	
	protected void useItem() {
		Map<String, Item> availableItems = cph.getUsableItems(thisRoom, target);
		Item itemToConsume = availableItems.get("inventory");
		
		if (itemToConsume == null) {
			System.out.println(String.format("You don't have any %s", target));
			return;
		}
		
		if (!itemToConsume.getType().equals(ItemType.consumable)) {
			System.out.println("You can't use that item.");
			return;
		}
		
		switch (itemToConsume.getEffectType()) {
		case health:
			int currentHitPoints = player.getHitPoints();
			int maxHitPoints = player.getMaxHitPoints();
			if (currentHitPoints < maxHitPoints) {
				int healthAfterRecover = currentHitPoints + itemToConsume.getEffectValue();
				int finalHealth = healthAfterRecover <= maxHitPoints ? healthAfterRecover : maxHitPoints;
				player.setHitPoints(finalHealth);
				player.getInventory().remove(itemToConsume);
				System.out.format("You use the %s!\n", itemToConsume.getName());
				System.out.format("You have recovered %d health!\n", finalHealth - currentHitPoints);
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
		case other:
		default:
			System.out.println("No effect");
			break;
		}
	}
	
	protected void warp() {
		target = WordUtils.capitalize(target);
		
		nextRoom = world.getRooms().get(target);
		
		if (nextRoom == null) {
			System.out.println(noGo);
			thisRoom.printExits();
			return;
		}
		cph.changeRooms(nextRoom);
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

	public void exitRoom(String direction) {
		nextRoom = thisRoom.getExits().get(Direction.valueOf(direction));
		
		if (nextRoom == null) {
			System.out.println(noGo);
			return;
		}
		
		if (Application.getCombat() == false) {
			cph.changeRooms(nextRoom);
		} else {
			cph.executeEscapeRoll(nextRoom);
		}
	}

	public void showInventory() {
		player.printInventory();
	}
	
	public void showEquipment() {
		if (target != "") {
			Equipment.equipItem(target);
		} else {
			player.printEquipment();
		}
	}
	
	public void removeEquipment() {
		Equipment.unequipItem(target);
	}
	
	public void playerStatus() {
		player.printStatus();
	}
	
	public void addToInventory() {
		player.putItemInInventory(target, thisRoom);
	}
	
	public void removeFromInventory() {
		player.removeItemFromInventory(target, thisRoom);
	}

	public void attack() {
		NonPlayer attackTarget = thisRoom.checkRoomForNonPlayer(target); 
		if (thisRoomCreatures.isEmpty()) {
			System.out.println(noCreatures);
			return;
		}
		if (attackTarget == null) {
        	System.out.println(noTargetCreatureFound);
    		thisRoom.printCreatures();
            return;
		}
		if (target.equals("")) {
			System.out.println(noAttackTarget);
			thisRoom.printCreatures();
			return;
		}			
		Combat.attackNonPlayer(attackTarget, thisRoom, player);
	}
	
	private void showCombatInfo() {
		NonPlayer target = Application.getCurrentCombatTarget();
		if (Application.getCombat()) {
			if (target != null) {
				System.out.format("Target: %s\n", target.toString());
			}
		} else {
			System.out.println("You are not in combat.");
		}
	}
	
	public void roomStatus() {
		thisRoom.printRoom();
	}
	
	public void checkThing() {
		cph.examine(player.checkEquipmentForItem(target), EQUIPMENT_PHRASE);
		cph.examine(player.checkInventoryForItem(target), INVENTORY_PHRASE);
		cph.examine(thisRoom.checkRoomForNonPlayer(target), ROOM_PHRASE);
		cph.examine(thisRoom.checkRoomForItem(target), ROOM_PHRASE);
	}
	
	public void quitGame() {
		System.out.println("Goodbye!");
		Application.setPlaying(false);
	}
}
