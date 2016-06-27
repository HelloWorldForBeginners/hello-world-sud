package com.kosn.data.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.kosn.application.Application;
import com.kosn.dto.defaults.PlayerDefaults;

public class Player extends Character {

    private int expToNextLevel;

	public Player(PlayerDefaults pd) {
		this(pd.name, 
				pd.description, 
				pd.level, 
				pd.money, 
				pd.exp, 
				pd.expToNextLevel, 
				pd.hitPoints, 
				pd.maxHitPoints, 
				pd.attack, 
				pd.defense
				);
	}
    
    public Player(String name, 
    		String description, 
    		int level, 
    		int money, 
    		int exp, 
    		int expToNextLevel, 
    		int hitPoints, 
    		int maxHitPoints,
    		int attack, 
    		int defense
    		) {
        super(name, description, level, money, exp, hitPoints, maxHitPoints, attack, defense);
        this.expToNextLevel = expToNextLevel;
    }
    
    public int getExpToNextLevel() {
        return expToNextLevel;
    }

    public void setExpToNextLevel(int expToNextLevel) {
        this.expToNextLevel = expToNextLevel;
    }
    
	public void levelUpPlayer(Player _player) {
		_player.setLevel(_player.getLevel() + 1);
		_player.setExpToNextLevel((int) Math.round(_player.getExpToNextLevel() * 1.5));
		_player.setMaxHitPoints((int) Math.round(_player.getMaxHitPoints() + 1.05));
		_player.setHitPoints(Math.round(_player.getMaxHitPoints()));
		_player.setAttack(_player.getAttack() + 1);
		_player.setDefense(_player.getDefense() + 1);
        System.out.println(_player.getName() + " has attained level " + _player.getLevel() + "!");
	}
	
	public String killPlayer() {
		Room defaultRoom = Application.getDefaultRoom();
		
		hitPoints = maxHitPoints;
		int moneyLost = ((int) Math.round(money * 0.9));
		money = money - moneyLost;
		
		if (money > 0) {
			System.out.println("You have been knocked unconscious! " +
                moneyLost + " money has been lost!");
		} else {
			System.out.println("You have been knocked unconscious!");
		}

        Application.toggleCombatOff();
        
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        Application.setCurrentRoom(defaultRoom);
        defaultRoom.toString();
        return "respawn";
	}

	public void addToExp(int _exp) {
		this.exp += _exp;
	}

	public void adjustMoney(int _money) {
		this.money += _money;
	}

	public int calculateMoneyLost(double _moneyLost) {
		return (int)Math.round(Application.getPlayer().getMoney() * _moneyLost);
	}
	
	@Override
    public String toString() {
        return 
        		"Name: " + this.getName() + 
        		"(Lv." + this.getLevel() + ")" + 
        		"\n" +
        		"HP: " + this.getHitPoints() + "/" + this.getMaxHitPoints() +
        		")\n" +
        		"Attack: " + this.getAttack() + 
        		")\n" + 
        		"Defense: " + this.getDefense() + 
        		")\n" +
        		"Exp: " + this.getExp() + "/" + this.expToNextLevel + 
        		")\n" +
        		"Money: " + this.getMoney() + 
        		"\n";
    }

	public void putItemInInventory(String targetItemName, Room thisRoom) {
    	
		List<Item> roomItems = new ArrayList<Item>();
		roomItems = thisRoom.getItems();
        boolean inRoom = false;
        Item item = null;
        
        // Check if item is a valid room item
        for (Item roomItem : roomItems) {
            if (roomItem.getName().equals(targetItemName)) {
                inRoom = true;
                item = roomItem;
                break;
            }
        }

        if (!inRoom) {
            System.out.println("You don't see that here.");
            return;
        }
        System.out.println("You pick up the " + targetItemName + ".");
        inventory.add(item);
        Collections.sort(inventory);
        Room.removeItem(thisRoom, item);
	}
	
	public void removeItemFromInventory(String targetItemName, Room thisRoom) {
		Item item = checkInventory(targetItemName);
        if (item == null) {
            System.out.println("You don't have that.");
            return;
        } 
        System.out.println("You put down the " + targetItemName + ".");
        inventory.remove(item);
        Room.addItem(thisRoom, item);
	}

	public void printStatus() {
		System.out.println(Application.getPlayer().toString());
		printInventory();
		printEquipment();
	}

	public void printEquipment() {
		System.out.println("\nEquipment:");
        for(HashMap.Entry<String, Item> entry: equipment.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getName());
        }
	}

	public void printInventory() {
        System.out.println("\nInventory:");
        for (Item item : inventory) {
            System.out.println(item.getName());
        }
	}
	
	public Item checkInventory(String target) {
		for (Item checkItem: inventory) {
            if (checkItem.getName().equals(target)) {
                return checkItem;
            }
            if (checkItem.getName().startsWith(target)) {
                return checkItem;
            }
        }
		return null;
	}
	
	public void levelUp() {
		level++;
		expToNextLevel = (int) Math.round(expToNextLevel * 1.5);
		maxHitPoints = ((int) Math.round(maxHitPoints + 1.05));
		hitPoints = maxHitPoints;
		attack++;
		defense++;
        System.out.println(name + " has attained level " + level + "!");
	}
}
