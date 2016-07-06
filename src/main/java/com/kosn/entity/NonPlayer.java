package com.kosn.entity;

public class NonPlayer {

	private String classType;
	private String name;
	private String description;
	private int level;
	private int money;
	private int exp;
	private int hitPoints;
	private int maxHitPoints;
	private int attack;
	private int defense;
	
	public NonPlayer() {}
	
	public NonPlayer(String name, String description, int level, int money, int exp, int hitPoints,
			int maxHitPoints, int attack, int defense) {
		this.name = name;
		this.description = description;
		this.level = level;
		this.money = money;
		this.exp = exp;
		this.hitPoints = hitPoints;
		this.maxHitPoints = maxHitPoints;
		this.attack = attack;
		this.defense = defense;
	}

	public NonPlayer(NonPlayerDefaults npd) {
		this(npd.name, 
				npd.description, 
				npd.level, 
				npd.money, 
				npd.exp, 
				npd.hitPoints, 
				npd.maxHitPoints, 
				npd.attack, 
				npd.defense 
				);
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public int getMaxHitPoints() {
		return maxHitPoints;
	}

	public void setMaxHitPoints(int maxHitPoints) {
		this.maxHitPoints = maxHitPoints;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}


    
	public static void spawnAnotherNonPlayer(NonPlayer _nonPlayer) {

		System.out.println("Another " + _nonPlayer.getName() + " has appeared!");
		_nonPlayer.setHitPoints(_nonPlayer.getMaxHitPoints());
	}

	
	public static void killNonPlayer(Player _player, NonPlayer _nonPlayer) {
		_player.addToExp(_nonPlayer.getExp());
		_player.adjustMoney(_nonPlayer.getMoney());
		_player.setHitPoints(_player.getMaxHitPoints());
        System.out.println("You have defeated the " + _nonPlayer.getName() + "!");
        System.out.println("Your health has fully recovered!\n");
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void lowerHitPoints(int _hitPoints) {
		this.hitPoints -= _hitPoints;
	}
	
    public void printInfo() {
		System.out.println("Name: " + this.getName() + "(Lv." + this.getLevel() + ")" + 
				"\n" +
				this.getDescription() +
				"\n" +
				"HP: " + this.getHitPoints() + "/" + this.getMaxHitPoints() +
				"\n" +
				"Attack: " + this.getAttack() +
				"\n" +
				"Defense: " + this.getDefense() +
				"\n");
	}
	
	@Override //means this method exists somewhere; it comes from the Object type
	public String toString() {
		String toString = getName();
		return toString;
	}

	public void printHealth() {
    	System.out.println(this.getName() + " HP: " + this.hitPoints + "/" + this.maxHitPoints + "\n");
    	System.out.println();
	}
}
