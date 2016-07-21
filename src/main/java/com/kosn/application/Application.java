package com.kosn.application;
import com.kosn.entity.NonPlayer;
import com.kosn.util.CommandProcessor;
import com.kosn.util.CreatureManager;
import com.kosn.util.Input;
import com.kosn.util.World;

public class Application {
    private static boolean combat = false;
    
    
    private static NonPlayer currentCombatTarget = null;
    
    private static World world = World.getInstance();
    private static CommandProcessor cp = CommandProcessor.getInstance();
    private static GameState gameState = GameState.getInstance();
	static final String newChar = "new";
    
    private static Thread creatureManagerThread;
    
    public static void main(String args[]) {
		System.out.println("Welcome to The Knights of Something Notable!\n");
		
		// Build rooms and set default
		Input.characterSelectionPrompt();
	
	    // start creature manager
	    creatureManagerThread = new Thread(new CreatureManager());
	    creatureManagerThread.start();
	    
	    try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		    System.exit(0);
		}
	    
	    // Start game
	    while (gameState.getPlaying()) {
	    	cp.processCommand(Input.getCommand());
	    }
	    System.exit(0);
	}

	public static boolean getCombat() {
		return combat;
	}

	public static void setCombat(boolean combat) {
		Application.combat = combat;
	}

	public static void toggleCombatOff() {
	    setCombat(false);
	    System.out.println();
	    System.out.println("Combat ended");
	    System.out.println();
	}

	public static void toggleCombatOn(NonPlayer nonPlayer) {
		if (!Application.getCombat()) {
			System.out.println("Combat started...");
			System.out.println();
			Application.setCombat(true);
			Application.setCurrentCombatTarget(nonPlayer);
	    }
	}

	public static NonPlayer getCurrentCombatTarget() {
		return currentCombatTarget;
	}

	public static void setCurrentCombatTarget(NonPlayer nonPlayer) {
		Application.currentCombatTarget = nonPlayer;
	}

	
}
