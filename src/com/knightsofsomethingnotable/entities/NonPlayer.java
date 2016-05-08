package com.knightsofsomethingnotable.entities;
import java.util.ArrayList;
import java.util.HashMap;

import com.knightsofsomethingnotable.main.Main;
import com.knightsofsomethingnotable.management.Room;
import com.knightsofsomethingnotable.management.World;

public class NonPlayer extends Character {

    public NonPlayer(String name, String description, int level, int money, int exp, int hitPoints, int maxHitPoints,
                     int attack, int defense, ArrayList<Item> inventory, HashMap<String, Item> equipment) {
        super(name, description, level, money, exp, hitPoints, maxHitPoints, attack, defense, inventory, equipment);
    }

    
    public static String printNonPlayerInfo(String target, Room room) {

    	NonPlayer nonPlayer = getNonPlayer(target, room);
    	if (nonPlayer != null) {
    		printInfo(nonPlayer, room);
    		return "printed";
    	} else {
    		return "";
    	}
    }

    
    private static void printInfo(NonPlayer nonPlayer, Room room) {

    	System.out.println("Name: " + nonPlayer.getName() + "(Lv." + nonPlayer.getLevel() + ")");
        System.out.println(nonPlayer.getDescription());
        System.out.println("HP: " + nonPlayer.getHitPoints() + "/" + nonPlayer.getMaxHitPoints());
        System.out.println("Attack: " + nonPlayer.getAttack());
        System.out.println("Defense: " + nonPlayer.getDefense());
        System.out.println();
	}

    
    public static NonPlayer getNonPlayer(String target, Room room) {

		for (NonPlayer creature : room.getCreatures() ) {
			if (creature.getName().equals(target)) {
                return creature;
            }
			if (creature.getName().startsWith(target)) {
				return creature;
			}
        }
		return null;
	}

    
	public static void spawnAnotherNonPlayer(NonPlayer _nonPlayer) {

		System.out.println("Another " + _nonPlayer.getName() + " has appeared!");
		_nonPlayer.setHitPoints(_nonPlayer.getMaxHitPoints());
	}

	
	public static void killNonPlayer(Player _player, NonPlayer _nonPlayer) {

		_player.setExp(_player.getExp() + _nonPlayer.getExp());
		_player.setMoney(_player.getMoney() + _nonPlayer.getMoney());
		_player.setHitPoints(_player.getMaxHitPoints());
        System.out.println(_player.getName() + " has defeated the " + _nonPlayer.getName() + "!");
        System.out.println(_player.getName() + "'s health has fully recovered!\n");
	}

	
	public String toString() {
		String toString = getName();
		return toString;
	}
}
