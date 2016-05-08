package com.knightsofsomethingnotable.entities;
import java.util.ArrayList;
import java.util.HashMap;

import com.knightsofsomethingnotable.main.Main;
import com.knightsofsomethingnotable.management.World;

public class Player extends Character {

    private int expToNextLevel;

    public Player(String name, String description, int level, int money, int exp, int expToNextLevel, int hitPoints, int maxHitPoints,
                  int attack, int defense, ArrayList<Item> inventory, HashMap<String, Item> equipment) {
        super(name, description, level, money, exp, hitPoints, maxHitPoints, attack, defense, inventory, equipment);
        this.expToNextLevel = expToNextLevel;
    }

    
    public static void printPlayerInfo(Player player) {
        System.out.println("Name: " + player.getName() + "(Lv." + player.getLevel() + ")");
        System.out.println("HP: " + player.getHitPoints() + "/" + player.getMaxHitPoints());
        System.out.println("Attack: " + player.getAttack());
        System.out.println("Defense: " + player.getDefense());
        System.out.println("Exp: " + player.getExp() + "/" + player.expToNextLevel);
        System.out.println("Money: " + player.getMoney());
        System.out.println();
        Character.printInventory(player.getInventory());
        System.out.println();
        Character.printEquipment(player.getEquipment());
    }

    
    public int getExpToNextLevel() {
        return expToNextLevel;
    }

    
    public void setExpToNextLevel(int expToNextLevel) {
        this.expToNextLevel = expToNextLevel;
    }

    
	public static void levelUpPlayer(Player _player) {
		_player.setLevel(_player.getLevel() + 1);
		_player.setExpToNextLevel((int) Math.round(_player.getExpToNextLevel() * 1.5));
		_player.setMaxHitPoints((int) Math.round(_player.getMaxHitPoints() + 1.05));
		_player.setHitPoints((int) Math.round(_player.getMaxHitPoints()));
		_player.setAttack(_player.getAttack() + 1);
		_player.setDefense(_player.getDefense() + 1);
        System.out.println(_player.getName() + " has attained level " + _player.getLevel() + "!");
	}
	
	
	public static void killPlayer(Player _player) {
		_player.setHitPoints(_player.getMaxHitPoints());
		_player.setMoney((int) Math.round(_player.getMoney() * 0.9));
        
		if (_player.getMoney() > 0) {
			System.out.println(_player.getName() + " has been knocked unconscious! " +
                (_player.getMoney() - _player.getMoney()) + " money has been lost!");
		} else {
			System.out.println(_player.getName() + " has been knocked unconscious!");
		}

        Main.toggleCombatOff();
        
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        Main.setCurrentRoom(Main.getDefaultRoom());
        World.print(Main.getCurrentRoom());
        // load cell
        return;
	}

}
