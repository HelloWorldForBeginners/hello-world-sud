package com.kosn.entity;

public class NonPlayer extends Character {

    public NonPlayer(
    		String name, 
    		String description, 
    		int level,
    		int money,
    		int exp,
    		int hitPoints, 
    		int maxHitPoints,
    		int attack,
    		int defense) {
        super(name, description, level, money, exp, hitPoints, maxHitPoints, attack, defense);
    }

    
	public NonPlayer(String[] params) {
		super(params[0], params[1], 
				Integer.parseInt(params[2]), 
				Integer.parseInt(params[3]), 
				Integer.parseInt(params[4]), 
				Integer.parseInt(params[5]), 
				Integer.parseInt(params[6]), 
				Integer.parseInt(params[7]), 
				Integer.parseInt(params[8]));
	}


	public static String printNonPlayerInfo(String target, Room room) {

    	NonPlayer nonPlayer = getNonPlayer(target, room);
    	if (nonPlayer != null) {
    		System.out.println(nonPlayer.toString());
    		return "printed";
    	} else {
    		return "";
    	}
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

		_player.addToExp(_nonPlayer.getExp());
		_player.adjustMoney(_nonPlayer.getMoney());
		_player.setHitPoints(_player.getMaxHitPoints());
        System.out.println("You have defeated the " + _nonPlayer.getName() + "!");
        System.out.println("Your health has fully recovered!\n");
	}

	public void lowerHitPoints(int _hitPoints) {
		this.hitPoints -= _hitPoints;
	}
	
	
	@Override
    public String toString() {
        return 
        		"Name: " + this.getName() + "(Lv." + this.getLevel() + ")" + 
			    "\n" +
			    this.getDescription() +
			    "\n" +
			    "HP: " + this.getHitPoints() + "/" + this.getMaxHitPoints() +
			    "\n" +
			    "Attack: " + this.getAttack() +
			    "\n" +
			    "Defense: " + this.getDefense() +
			    "\n";
	}
	
}
