package com.kosn.util;

import com.kosn.application.Application;
import com.kosn.entity.NonPlayer;
import com.kosn.entity.Player;
import com.kosn.entity.Room;

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

        Application.toggleCombatOn(nonPlayer);
        if (processPlayerAttack(player, nonPlayer).equals("continue")) {
        	processNonPlayerAttack(player, nonPlayer);
        }
    }
    
    private static String processPlayerAttack(Player _player, NonPlayer _nonPlayer) {
    	
		_nonPlayer.lowerHitPoints(_player.getAttack());
        System.out.println(_player.getName() + " hits " + _nonPlayer.getName() + " for " + _player.getAttack() + " point(s) of damage!");
        
        _nonPlayer.printHealth();
        
        if (_nonPlayer.getHitPoints() <= 0) {
    		
        	NonPlayer.killNonPlayer(_player, _nonPlayer);
            
        	Application.toggleCombatOff();
            
            if (_player.getExp() >= _player.getExpToNextLevel()) {
            	Player.levelUpPlayer(_player);
            }
            
            NonPlayer.spawnAnotherNonPlayer(_nonPlayer);
            
            return "end round";
    	}
        return "continue";
	}
    
    public static String processNonPlayerAttack(Player _player, NonPlayer _nonPlayer) {
    	
    	_player.setHitPoints(_player.getHitPoints() - _nonPlayer.getAttack());
    	
    	System.out.println("The " + _nonPlayer.getName() + " hits you for " + _nonPlayer.getAttack() + " point(s) of damage!");
    	_player.printHealth();
    	
    	if (_player.getHitPoints() <= 0) {
    		Player.killPlayer(_player);
    		return "respawned";
        }
		return ""; 
	}

}
