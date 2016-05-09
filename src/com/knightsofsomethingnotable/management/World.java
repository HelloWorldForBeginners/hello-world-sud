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
//	private static HashMap<String, Room> rooms = null;

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
	            Main.rooms.put(lineArray[1], new Room(lineArray[0], lineArray[1], lineArray[2], new ArrayList<>(), new ArrayList<>()));
	    	}
	    	
	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    } finally {
	    	scan.close();
	    }
		
	}

	private static void addItemsToRooms() {
		Main.rooms.get("dungeon").setItems( new Item("shirt","smelly",1,"equipment","body",3));
	    Main.rooms.get("dungeon").setItems( new Item("jacket","cool",1,"equipment","body",3));
	    Main.rooms.get("dungeon").setItems( new Item("shoes","smelly",1,"equipment","feet",1));
	    Main.rooms.get("dungeon").setItems( new Item("boots","smelly",1,"equipment","feet",1));
	    Main.rooms.get("cell").setItems( new Item("pants","wet",1,"equipment","legs",2));
	    Main.rooms.get("messHall").setItems( new Item("gloves","stained",1,"equipment","hands",1));
	    Main.rooms.get("privy").setItems( new Item("shoes","smelly",1,"equipment","feet",1));
	    Main.rooms.get("privy").setItems( new Item("boots","smelly",1,"equipment","feet",1));
	    Main.rooms.get("privy").setItems( new Item("toilet paper","cool",1,"item","none",0));
	    Main.rooms.get("larder").setItems( new Item("wine","tasty",0,"consumable","FOOD",0));
	    Main.rooms.get("larder").setItems( new Item("mead","tasty",0,"consumable","FOOD",0));
	    Main.rooms.get("larder").setItems( new Item("spirits","tasty",0,"consumable","FOOD",0));
	    Main.rooms.get("larder").setItems( new Item("mutton","it's sheep, you're eating sheep",0,"consumable","FOOD",0));
	}

	private static void addCreaturesToRooms() {
        Main.rooms.get("dungeon").setCreatures( new NonPlayer("platypus","semi-aquatic, egg-laying mammal of action",1,5,5,2,2,1,1,null,null));
        Main.rooms.get("cell").setCreatures( new NonPlayer("bugbear","fluffy",3,13,20,10,10,3,3,null,null));
        Main.rooms.get("messHall").setCreatures( new NonPlayer("warg","fast",10,35,80,20,20,10,10,null,null));
        Main.rooms.get("privy").setCreatures( new NonPlayer("goblin","weak",6,22,41,15,15,6,6,null,null));
        Main.rooms.get("dovecote").setCreatures( new NonPlayer("bloodthirsty dove","bloodthirsty",5,22,41,15,15,6,6,null,null));

	}

	private static void addExitsToRooms() {
	    Main.rooms.get("cell").setExits( new String("east"), Main.rooms.get("dungeon"));
	    Main.rooms.get("dungeon").setExits( new String("south"), Main.rooms.get("privy"));
	    Main.rooms.get("dungeon").setExits( new String("east"), Main.rooms.get("messHall"));
	    Main.rooms.get("dungeon").setExits( new String("west"), Main.rooms.get("cell"));
	    Main.rooms.get("dungeon").setExits( new String("north"), Main.rooms.get("dovecote"));
	    Main.rooms.get("messHall").setExits( new String("west"), Main.rooms.get("dungeon"));
	    Main.rooms.get("messHall").setExits( new String("east"), Main.rooms.get("larder"));
	    Main.rooms.get("larder").setExits( new String("west"), Main.rooms.get("messHall"));
	    Main.rooms.get("privy").setExits( new String("north"), Main.rooms.get("dungeon"));
	    Main.rooms.get("dovecote").setExits( new String("south"), Main.rooms.get("dungeon"));
		
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
