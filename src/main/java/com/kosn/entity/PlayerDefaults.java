package com.kosn.entity;

public final class PlayerDefaults {
	public String name, description;
	public int level, money, exp, expToNextLevel, hitPoints, maxHitPoints, attack, defense;
	
	public PlayerDefaults() {
		this.name = "Everyman";
		this.description = "generic";
		this.level = 1;
		this.money = 0;
		this.exp = 0;
		this.expToNextLevel = 10;
		this.hitPoints = 10;
		this.maxHitPoints = 10;
		this.attack = 1;
		this.defense = 1;
	}
	
}
