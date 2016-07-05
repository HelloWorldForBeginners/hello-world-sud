package com.kosn.util;

import java.util.List;

import com.kosn.application.Application;
import com.kosn.entity.NonPlayer;
import com.kosn.entity.Player;
import com.kosn.entity.Room;

public class Combat {

	private static Room combatRoom;
	private static Player combatPlayer;
	private static NonPlayer combatNonPlayer;
	
	public static void attackNonPlayer(NonPlayer creature, Room room, Player player) {

		combatRoom = room;
		combatPlayer = player;
		combatNonPlayer = creature;
		
        Application.toggleCombatOn(combatNonPlayer);
        if (processPlayerAttack().equals("continue")) {
        	processNonPlayerAttack(player, combatNonPlayer);
        }
    }
    
    private static String processPlayerAttack() {
    	
    	combatNonPlayer.lowerHitPoints(combatPlayer.getAttack());
        System.out.println(combatPlayer.getName() + " hits " + combatNonPlayer.getName() + " for " + combatPlayer.getAttack() + " point(s) of damage!");
        
        combatNonPlayer.printHealth();
        
        if (combatNonPlayer.getHitPoints() <= 0) {
    		
        	NonPlayer.killNonPlayer(combatPlayer, combatNonPlayer);
            
        	Application.toggleCombatOff();
            
            if (combatPlayer.getExp() >= combatPlayer.getExpToNextLevel()) {
            	combatPlayer.levelUp();
//            	Player.levelUpPlayer(combatPlayer);
            }
            
            removeCreatureFromRoom();
            
//            NonPlayer.spawnAnotherNonPlayer(_nonPlayer);
            
            return "end round";
    	}
        return "continue";
	}
    
    private static void removeCreatureFromRoom() {
    	List<NonPlayer> roomCreatures = combatRoom.getCreatures(); 
    	int index = 0;
    	
    	for (NonPlayer np : roomCreatures) {
            if (np.equals(combatNonPlayer)) {
            	roomCreatures.remove(index);
            	combatRoom.setCreatures(roomCreatures);
            	break;
            }
    	}
	}

	public static String processNonPlayerAttack(Player _player, NonPlayer _nonPlayer) {
    	
    	_player.setHitPoints(_player.getHitPoints() - _nonPlayer.getAttack());
    	
    	System.out.println("The " + _nonPlayer.getName() + " hits you for " + _nonPlayer.getAttack() + " point(s) of damage!");
    	_player.printHealth();
    	
    	if (_player.getHitPoints() <= 0) {
    		_player.killPlayer();
    		return "respawned";
        }
		return ""; 
	}
}
