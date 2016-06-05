package com.kosn.data.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import com.kosn.application.Application;
import com.kosn.entity.Item;
import com.kosn.entity.NonPlayer;
import com.kosn.entity.Room;

public class BuildRoomDAO {

	private BuildRoomDAO () {}
	
	private static String path = "";

	public static void getPath() {

		File currDir = new File(".");
		path = currDir.getAbsolutePath();
		path = path.substring(0, path.length()-1);
	}
    
	public static void buildRoomsFromFile(String _fileName, int _expectedLineLength) {

		File file = new File(path + "/src/resources/" + _fileName + ".txt");
		
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
		Application.getRooms().put(
	    		lineArray[0], 
	    		new Room(
	    				Arrays.copyOfRange(lineArray, 1, lineArray.length)
	    		));
	}

    
	private static void addAnItem(String[] lineArray) {
		Application.getRooms().get(lineArray[0]).setItems( 
	    		new Item(
	    				//lineArray.arraycopy()
	    				Arrays.copyOfRange(lineArray, 1, lineArray.length)
	    				)
	    		);
	}

	
	private static void addACreature(String[] lineArray) {
		Application.getRooms().get(lineArray[0]).setCreatures( 
	    		new NonPlayer(
	    				Arrays.copyOfRange(lineArray, 1, lineArray.length))
	    		);
		
	}

	
	private static void addAnExit(String[] lineArray) {
    	Application.getRooms().get(lineArray[0]).
    	setExits(
    			Arrays.copyOfRange(lineArray, 1, lineArray.length)
    			);
		
	}

	
}
