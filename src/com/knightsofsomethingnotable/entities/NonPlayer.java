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

    private static NonPlayer getNonPlayer(String target, Room room) {

		for (NonPlayer creature : room.getCreatures() ) {
            if (creature.getName().equals(target)) {
                return creature;
            }
        }
		return null;
	}

	public static void attackNonPlayer(String target, Room room, Player player) {

        NonPlayer nonPlayer = getNonPlayer(target, room); 

        if (nonPlayer == null) {
            System.out.println("There is no " + target + " here.");
            return;
        }

        Main.toggleCombatOn();
        processPlayerRound(player, nonPlayer);
        processNonPlayerRound(player, nonPlayer);
    }
    
    private static void processNonPlayerRound(Player _player, NonPlayer _nonPlayer) {
    	
    	if (_nonPlayer.getHitPoints() - _player.getAttack() <= 0) {
    		killNonPlayer(_player, _nonPlayer);
            Main.toggleCombatOff();
            if (_player.getExp() >= _player.getExpToNextLevel()) {
            	Player.playerLeveledUp(_player);
            }
            spawnAnotherNonPlayer(_nonPlayer);
            
    	} else {

    		_nonPlayer.setHitPoints(_nonPlayer.getHitPoints() - _player.getAttack());
            System.out.println(_player.getName() + " hits " + _nonPlayer.getName() + " for " + _player.getAttack() + " point(s) of damage!");
            System.out.println(_nonPlayer.getName() + " HP: " + _nonPlayer.getHitPoints() + "/" + _nonPlayer.getMaxHitPoints());
        }
		
	}

	private static void spawnAnotherNonPlayer(NonPlayer _nonPlayer) {

		System.out.println("Another " + _nonPlayer.getName() + " has appeared!");
		_nonPlayer.setHitPoints(_nonPlayer.getMaxHitPoints());
	}

	private static void killNonPlayer(Player _player, NonPlayer _nonPlayer) {

		_player.setExp(_player.getExp() + _nonPlayer.getExp());
		_player.setMoney(_player.getMoney() + _nonPlayer.getMoney());
		_player.setHitPoints(_player.getMaxHitPoints());
        System.out.println(_player.getName() + " has defeated the " + _nonPlayer.getName() + "!");
        System.out.println(_player.getName() + "'s health has fully recovered!\n");
	}

	private static void processPlayerRound(Player _player, NonPlayer _nonPlayer) {
    	if (_player.getHitPoints() - _nonPlayer.getAttack() <= 0) {
    		killPlayer(_player);
        } else {
        	_player.setHitPoints(_player.getHitPoints() - _nonPlayer.getAttack());
            System.out.println("The " + _player.getName() + " hits " + _player.getName() + " for " + _nonPlayer.getAttack() + " point(s) of damage!");
            System.out.println(_player.getName() + " HP: " + _player.getHitPoints() + "/" + _player.getMaxHitPoints() + "\n");
        }
	}

	private static void killPlayer(Player _player) {
		_player.setHitPoints(_player.getMaxHitPoints());
		_player.setMoney((int) Math.round(_player.getMoney() * 0.9));
        System.out.println(_player.getName() + " has been knocked unconscious! " +
                (_player.getMoney() - _player.getMoney()) + " money has been lost!");
        Main.toggleCombatOff();
        Main.setCurrentRoom(Main.getDefaultRoom());
        World.print(Main.getCurrentRoom());
        // load cell
        return;
	}

	public String toString() {
		String toString = getName();
		return toString;
	}
}
