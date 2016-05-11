package com.knightsofsomethingnotable.management;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.knightsofsomethingnotable.entities.Item;
import com.knightsofsomethingnotable.entities.NonPlayer;
import com.knightsofsomethingnotable.main.Main;

public class World {
	
	private static String path = "";

	public static void build() {
    	
    	getPath();
    	buildRoomsFromFile("roomList", 3);
    	buildRoomsFromFile("itemList", 7);
    	buildRoomsFromFile("creatureList", 10);
    	buildRoomsFromFile("exitList", 3);
    }

	
	public static void getPath() {

		File currDir = new File(".");
		path = currDir.getAbsolutePath();
		path = path.substring(0, path.length()-1);
	}
    
    
	private static void buildRoomsFromFile(String _fileName, int _expectedLineLength) {

    	File file = new File(path + "/src/Resources/" + _fileName + ".txt");
		
		Scanner scan = null;
		
	    try {
	    	scan = new Scanner(file);
	
	    	while (scan.hasNextLine()) {
	    		String line = scan.nextLine();
	            String[] lineArray = line.split(",");
	            if (lineArray.length < _expectedLineLength) { 
	            	continue;
	            }
	            
	            switch (_fileName) {
		            case "roomList":
		            	buildARoom(lineArray);
		            	break;
		            case "itemList":
		            	addAnItem(lineArray);
		            	break;
		            case "creatureList":
		            	addACreature(lineArray);
		            	break;
		            case "exitList":
		            	addAnExit(lineArray);
		            	break;
	            }
	    	}
	    	
	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    } finally {
	    	scan.close();
	    }
	}

    
    private static void buildARoom(String[] lineArray) {
		Main.rooms.put(
	    		lineArray[1], 
	    		new Room(
	    				lineArray[0], 
	    				lineArray[1], 
	    				lineArray[2], 
	    				new ArrayList<>(), 
	    				new ArrayList<>())
	    		);
	}

    
	private static void addAnItem(String[] lineArray) {
		Main.rooms.get(lineArray[0]).setItems( 
	    		new Item(
	    				lineArray[1], 
	    				lineArray[2], 
	    				Integer.parseInt(lineArray[3]), 
	    				lineArray[4], 
	    				lineArray[5], 
	    				Integer.parseInt(lineArray[6]))
	    		);
		
	}

	
	private static void addACreature(String[] lineArray) {
		Main.rooms.get(lineArray[0]).setCreatures( 
	    		new NonPlayer(
	    				lineArray[1], 
	    				lineArray[2], 
	    				Integer.parseInt(lineArray[3]), 
	    				Integer.parseInt(lineArray[4]), 
	    				Integer.parseInt(lineArray[5]), 
	    				Integer.parseInt(lineArray[6]), 
	    				Integer.parseInt(lineArray[7]), 
	    				Integer.parseInt(lineArray[8]), 
	    				Integer.parseInt(lineArray[9]), 
	    				new ArrayList<>(), new HashMap<>())
	    		);
		
	}

	
	private static void addAnExit(String[] lineArray) {
    	Main.rooms.get(lineArray[0]).
    	setExits(new String(lineArray[1]), Main.rooms.get(lineArray[2]));
		
	}

}
