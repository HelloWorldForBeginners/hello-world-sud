package com.knightsofsomethingnotable.util;

import com.knightsofsomethingnotable.entities.NonPlayer;
import com.knightsofsomethingnotable.entities.Player;
import com.knightsofsomethingnotable.main.Main;
import com.knightsofsomethingnotable.management.Room;

public class Combat {

	
	public static void attackNonPlayer(String target, Room room, Player player) {

		if (target.equals("")) {
			System.out.println("Attack what?");
            return;
		}
		
        NonPlayer nonPlayer = NonPlayer.getNonPlayer(target, room); 

        if (nonPlayer == null) {
            System.out.println("There is no " + target + " here.");
            return;
        }

        Main.toggleCombatOn();
        if (processPlayerAttack(player, nonPlayer).equals("continue")) {
        	processNonPlayerAttack(player, nonPlayer);
        }
    }
    
    private static String processPlayerAttack(Player _player, NonPlayer _nonPlayer) {
    	
		_nonPlayer.setHitPoints(_nonPlayer.getHitPoints() - _player.getAttack());
        System.out.println(_player.getName() + " hits " + _nonPlayer.getName() + " for " + _player.getAttack() + " point(s) of damage!");
        
        _nonPlayer.printHealth();
        
        if (_nonPlayer.getHitPoints() <= 0) {
    		
        	NonPlayer.killNonPlayer(_player, _nonPlayer);
            
        	Main.toggleCombatOff();
            
            if (_player.getExp() >= _player.getExpToNextLevel()) {
            	Player.levelUpPlayer(_player);
            }
            
            NonPlayer.spawnAnotherNonPlayer(_nonPlayer);
            
            return "end round";
    	}
        return "continue";
	}
    
    private static void processNonPlayerAttack(Player _player, NonPlayer _nonPlayer) {
    	
    	_player.setHitPoints(_player.getHitPoints() - _nonPlayer.getAttack());
    	
    	System.out.println("The " + _nonPlayer.getName() + " hits " + _player.getName() + " for " + _nonPlayer.getAttack() + " point(s) of damage!");
    	_player.printHealth();
    	//System.out.println(_player.getName() + " HP: " + _player.getHitPoints() + "/" + _player.getMaxHitPoints() + "\n");
    	System.out.println();
    	
    	if (_player.getHitPoints() <= 0) {
    		Player.killPlayer(_player);
        } 
	}

}
