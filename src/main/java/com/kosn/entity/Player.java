package com.kosn.entity;

import com.kosn.application.Application;
import com.kosn.entity.defaults.PlayerDefaults;

public class Player extends Character {

    private int expToNextLevel;

	public Player(PlayerDefaults pd) {
		
		this(pd.name, pd.description, pd.level, pd.money, pd.exp, pd.expToNextLevel, pd.hitPoints, pd.maxHitPoints, pd.attack, pd.defense);
	}
    
    public Player(String name, String description, int level, int money, int exp, int expToNextLevel, int hitPoints, int maxHitPoints,
                  int attack, int defense) {
        super(name, description, level, money, exp, hitPoints, maxHitPoints, attack, defense);
        this.expToNextLevel = expToNextLevel;
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
		_player.setHitPoints(Math.round(_player.getMaxHitPoints()));
		_player.setAttack(_player.getAttack() + 1);
		_player.setDefense(_player.getDefense() + 1);
        System.out.println(_player.getName() + " has attained level " + _player.getLevel() + "!");
	}
	
	
	public static String killPlayer(Player _player) {
		Room defaultRoom = Application.getDefaultRoom();
		
		_player.setHitPoints(_player.getMaxHitPoints());
		_player.setMoney((int) Math.round(_player.getMoney() * 0.9));
		
        
		if (_player.getMoney() > 0) {
			System.out.println("You have been knocked unconscious! " +
                (_player.getMoney() - _player.getMoney()) + " money has been lost!");
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


	public static int calculateMoneyLost(double _moneyLost) {
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
	
}
