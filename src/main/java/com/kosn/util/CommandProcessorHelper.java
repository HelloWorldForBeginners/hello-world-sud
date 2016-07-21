package com.kosn.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.kosn.application.Application;
import com.kosn.entity.Examinable;
import com.kosn.entity.Item;
import com.kosn.entity.Player;
import com.kosn.entity.Room;

public class CommandProcessorHelper {
	private static World world = World.getInstance(); 
	private static Player player = world.getPlayer();
	
	//singleton
	private static CommandProcessorHelper instance = null;
	protected CommandProcessorHelper() {
	}
	public static CommandProcessorHelper getInstance() {
		if(instance == null) {
			instance = new CommandProcessorHelper();
		}
		return instance;
	}
	
	protected Map<String, Item> getUsableItems(Room thisRoom, String target) {
		Map<String, Item> itemsFound = new HashMap<String, Item>();		
		Item targetItem = thisRoom.checkRoomForItem(target);
		if (targetItem != null) {
			itemsFound.put("room", targetItem);
		}
		targetItem = player.checkInventoryForItem(target);
		if (targetItem != null) {
			itemsFound.put("inventory", targetItem);
		}
		return itemsFound;
	}
	
	void executeEscapeRoll(Room nextRoom) {
		Random r = new Random();
		
		System.out.println("You're in combat!");
		System.out.println("Trying to escape...!");
		
		FlowManagement.wait(1);

		switch (r.nextInt(6)) {
			case 0:
				unableToEscape();
				break;
			//FALLTHROUGH
			case 1:
			case 2:
				escapeWithDamage(nextRoom);
				break;
			//FALLTHROUGH 
			case 3:
			case 4:
				if (player.calculateMoneyLost(0.05) > 0) {
					escapeWithMoneyLoss(nextRoom);
				} else {
					escape(nextRoom);
				}
				break;
			case 5:
				escape(nextRoom);
				break;
		}
	}

	private void escape(Room nextRoom) {
		System.out.println("You have escaped!");
		Application.setCombat(false);
		changeRooms(nextRoom);
	}

	private void escapeWithMoneyLoss(Room nextRoom) {
		System.out.printf("You have escaped but lost %d money.", player.calculateMoneyLost(0.05));
		System.out.println();
		player.adjustMoney(-player.calculateMoneyLost(0.05));
		Application.setCombat(false);
		changeRooms(nextRoom); 
	}

	private void escapeWithDamage(Room nextRoom) {
		if (Combat.processNonPlayerAttack(player, Application.getCurrentCombatTarget()).equals("respawned")){
			return;
		}
		System.out.println("Running away are you?");
		Application.setCombat(false);
		changeRooms(nextRoom);
	}

	private static void unableToEscape() {
		System.out.println("You were unable to escape!");
		Combat.processNonPlayerAttack(player, Application.getCurrentCombatTarget());
	}

	void changeRooms(Room nextRoom) {
		Application.setCurrentCombatTarget(null);
		World.getInstance().setCurrentRoom(nextRoom);
		nextRoom.printRoom();
	}

	void examine(String phrase, Examinable target) {
		if (target == null) { return; }
		System.out.format(phrase, target.getName());
		for (int i = 0; i < 5; i++) {
			FlowManagement.wait(.25);
			System.out.print(".");
		}
		System.out.println();
		target.printInfo();
	}
}
