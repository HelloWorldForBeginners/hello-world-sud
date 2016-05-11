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

    public static void build() {
    	
		buildRoomsFromFile();
		addItemsToRooms();
		addCreaturesToRooms();
		addExitsToRooms();
    }

    
    private static void buildRoomsFromFile() {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length()-1);
		
		File file = new File(path + "/src/Resources/roomList.txt");
		
		Scanner scan = null;
		
	    try {
	    	scan = new Scanner(file);
	
	    	while (scan.hasNextLine()) {
	    		String line = scan.nextLine();
	            String[] lineArray = line.split(",");
	            if (lineArray.length < 3) { 
	            	continue;
	            }
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
	    	
	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    } finally {
	    	scan.close();
	    }
	}

    
    private static void addItemsToRooms() {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length()-1);
		
		File file = new File(path + "/src/Resources/itemList.txt");
		
		Scanner scan = null;
		
	    try {
	    	scan = new Scanner(file);
	
	    	while (scan.hasNextLine()) {
	    		String line = scan.nextLine();
	            String[] lineArray = line.split(",");
	            if (lineArray.length < 7) {
	            	continue;
	            }
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
	    	
	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    } finally {
	    	scan.close();
	    }
	}
    
    
    private static void addCreaturesToRooms() {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length()-1);
		
		File file = new File(path + "/src/Resources/creatureList.txt");
		
		Scanner scan = null;
		
	    try {
	    	scan = new Scanner(file);
	
	    	while (scan.hasNextLine()) {
	    		String line = scan.nextLine();
	            String[] lineArray = line.split(",");
	            if (lineArray.length < 10) {
	            	continue;
	            }
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
	    	
	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    } finally {
	    	scan.close();
	    }
	}
    

    private static void addExitsToRooms() {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length()-1);
		
		File file = new File(path + "/src/Resources/exitList.txt");
		
		Scanner scan = null;
		
	    try {
	    	scan = new Scanner(file);
	
	    	while (scan.hasNextLine()) {
	    		String line = scan.nextLine();
	            String[] lineArray = line.split(",");
	            if (lineArray.length < 3) {
	            	continue;
	            }
	            Main.rooms.get(lineArray[0]).
	            	setExits(new String(lineArray[1]), Main.rooms.get(lineArray[2]));
	    	}
	    	
	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    } finally {
	    	scan.close();
	    }
	}


	public static void print(Room room) {
    	
    	System.out.println("<<<<<======= " + room.getName() + " =======>>>>>\n");
        System.out.println(room.getDescription());
        System.out.println();
        System.out.println("Exits: " + room.getExits());
        System.out.println();
        if (room.getItems().size() > 0) {
            System.out.println("You see: " + room.getItems());
        }
        System.out.println();
        if (room.getCreatures().size() > 0) {
            System.out.println("There are creatures here: " + room.getCreatures());
        }
        System.out.println();
    }

    public static String getRoomName(Room room) {

        return room.getName();
    }

    public static void removeItem(Room room, Item item) {

        room.deleteItem(item);
    }

    public static void addItem(Room room, Item item) {

        room.addItem(item);
    }
}
