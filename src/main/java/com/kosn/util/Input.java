package com.kosn.util;
import java.util.List;
import java.util.Scanner;

import com.kosn.db.EntityFactory;
import com.kosn.entity.GameObject;

public class Input {
	
	private static EntityFactory entityFactory = EntityFactory.getInstance();
	
	static String target = "";
	private static World world = World.getInstance();
    
	public static String getCommand() {
        Scanner in = new Scanner(System.in);
        System.out.println();
        String input = in.nextLine().toLowerCase();
        String command = "";
        
        if(input.contains(" ")){
        	command = input.substring(0, input.indexOf(" "));
        	target = input.substring(input.indexOf(" ")+1).toLowerCase();
    	} else {
    		command = input;
    		target = "";
    	}
        
        return command;
    }
	
	public static void characterSelectionPrompt() {
		List<GameObject> chars = entityFactory.loadGameSaves();
		if (chars.isEmpty()) {
			System.out.println("Name your character");
		} else {
			System.out.println("Select your character or provide a new name to start a new character");
			System.out.println("Current saves: ");
			System.out.println(chars);
		}
		Scanner characterSelection = new Scanner(System.in);
		String selection = characterSelection.nextLine();
		world.processInput(selection, chars);
	}
}
