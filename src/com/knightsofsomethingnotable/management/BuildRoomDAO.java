package com.knightsofsomethingnotable.management;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import com.knightsofsomethingnotable.entities.Item;
import com.knightsofsomethingnotable.entities.NonPlayer;
import com.knightsofsomethingnotable.main.Main;

public class BuildRoomDAO {

	private BuildRoomDAO () {}
	
	private static String path = "";

	public static void getPath() {

		File currDir = new File(".");
		path = currDir.getAbsolutePath();
		path = path.substring(0, path.length()-1);
	}
    
	static void buildRoomsFromFile(String _fileName, int _expectedLineLength) {

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
	    		lineArray[0], 
	    		new Room(
	    				Arrays.copyOfRange(lineArray, 1, lineArray.length)
	    		));
	}

    
	private static void addAnItem(String[] lineArray) {
		Main.rooms.get(lineArray[0]).setItems( 
	    		new Item(
	    				//lineArray.arraycopy()
	    				Arrays.copyOfRange(lineArray, 1, lineArray.length)
	    				)
	    		);
	}

	
	private static void addACreature(String[] lineArray) {
		Main.rooms.get(lineArray[0]).setCreatures( 
	    		new NonPlayer(
	    				Arrays.copyOfRange(lineArray, 1, lineArray.length))
	    		);
		
	}

	
	private static void addAnExit(String[] lineArray) {
    	Main.rooms.get(lineArray[0]).
    	setExits(
    			Arrays.copyOfRange(lineArray, 1, lineArray.length)
    			);
		
	}

	
}
