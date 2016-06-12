package com.kosn.data.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import com.kosn.application.Application;
import com.kosn.entity.Item;
import com.kosn.entity.NonPlayer;
import com.kosn.entity.Room;
import com.kosn.util.Directions;
import com.kosn.util.World;

public class BuildRoomDAO {

	private BuildRoomDAO () {}
	
	private static String path = "";

	public static void getPath() {

		File currDir = new File(".");
		path = currDir.getAbsolutePath();
		path = path.substring(0, path.length()-1);
	}
    
	public static void buildRoomsFromFile(String _fileName, int _expectedLineLength) {
		getPath();

		String _profileFileName = String.format("%s_%s", Application.getPlayer().getName(), _fileName);
		
		File file = new File(path + "src/main/resources/profiles/" + _profileFileName + ".txt");
		
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
		            case "roomSave":
		            	buildARoom(lineArray);
		            	break;
		            case "itemSave":
		            	addAnItem(lineArray);
		            	break;
		            case "creatureSave":
		            	addACreature(lineArray);
		            	break;
		            case "exitSave":
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
		
//		System.out.println(World.rooms.keySet());
//		System.out.println(lineArray[0]);
//		System.out.println(World.rooms.get(lineArray[0]));

		World.rooms.get(lineArray[0]).setItems( 
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
    	Application.getRooms().get(Directions.valueOf(lineArray[0])).
    	setExits(
    			Arrays.copyOfRange(lineArray, 1, lineArray.length)
    			);
		
	}
	
}
