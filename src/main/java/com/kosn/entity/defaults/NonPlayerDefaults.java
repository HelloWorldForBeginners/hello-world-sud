package com.kosn.entity.defaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.kosn.application.Application;
import com.kosn.util.World;

public final class NonPlayerDefaults {
	public String name, description;
	public int level, money, exp, hitPoints, maxHitPoints, attack, defense;

	private Map<String, String> creaturePool = World.getCreaturePool();
	
	private List<String> keysList = new ArrayList<String>(creaturePool.keySet());
	int randomIndex = new Random().nextInt(keysList.size());
	String randomCreatureName = keysList.get(randomIndex);
	
	private int playerLevel = Application.getPlayer().getLevel();
		
	public NonPlayerDefaults() {
		this.name = randomCreatureName;
		this.description = creaturePool.get(randomCreatureName); 
		this.level = this.playerLevel;
		this.money = 3;
		this.exp = Application.getPlayer().getExpToNextLevel()/10;
		this.hitPoints = this.playerLevel * 2;
		this.maxHitPoints = this.playerLevel * 2;
		this.attack = this.playerLevel;
		this.defense = this.playerLevel;
	}
}
