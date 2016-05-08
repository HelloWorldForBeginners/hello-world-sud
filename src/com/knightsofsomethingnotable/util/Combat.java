package com.knightsofsomethingnotable.util;

import com.knightsofsomethingnotable.entities.NonPlayer;
import com.knightsofsomethingnotable.entities.Player;
import com.knightsofsomethingnotable.main.Main;
import com.knightsofsomethingnotable.management.Room;

public class Combat {

	
	public static void attackNonPlayer(String target, Room room, Player player) {

        NonPlayer nonPlayer = NonPlayer.getNonPlayer(target, room); 

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
    		NonPlayer.killNonPlayer(_player, _nonPlayer);
            Main.toggleCombatOff();
            if (_player.getExp() >= _player.getExpToNextLevel()) {
            	Player.levelUpPlayer(_player);
            }
            NonPlayer.spawnAnotherNonPlayer(_nonPlayer);
            
    	} else {

    		_nonPlayer.setHitPoints(_nonPlayer.getHitPoints() - _player.getAttack());
            System.out.println(_player.getName() + " hits " + _nonPlayer.getName() + " for " + _player.getAttack() + " point(s) of damage!");
            System.out.println(_nonPlayer.getName() + " HP: " + _nonPlayer.getHitPoints() + "/" + _nonPlayer.getMaxHitPoints());
        }
		
	}
    
    private static void processPlayerRound(Player _player, NonPlayer _nonPlayer) {
    	if (_player.getHitPoints() - _nonPlayer.getAttack() <= 0) {
    		Player.killPlayer(_player);
        } else {
        	_player.setHitPoints(_player.getHitPoints() - _nonPlayer.getAttack());
            System.out.println("The " + _player.getName() + " hits " + _player.getName() + " for " + _nonPlayer.getAttack() + " point(s) of damage!");
            System.out.println(_player.getName() + " HP: " + _player.getHitPoints() + "/" + _player.getMaxHitPoints() + "\n");
        }
	}
	
	
}
