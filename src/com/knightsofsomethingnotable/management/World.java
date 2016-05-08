package com.knightsofsomethingnotable.management;

import java.util.ArrayList;
import java.util.HashMap;

import com.knightsofsomethingnotable.entities.Item;
import com.knightsofsomethingnotable.entities.NonPlayer;

public class World {

    public static void build(HashMap<String, Room> rooms) {
    	
    	Room dungeon = new Room("Dungeon", "You are in a dungeon.\n", new ArrayList<>(), new ArrayList<>());
    	Room messHall = new Room("Mess Hall", "You are in the mess hall.\n", new ArrayList<>(), new ArrayList<>());
    	Room larder = new Room("Larder", "You are in the larder. There's food here.\n", new ArrayList<>(), new ArrayList<>());
    	Room cell = new Room("Cell", "You are in a cell.\n", new ArrayList<>(), new ArrayList<>());
    	Room privy = new Room("Privy", "You are in the privy.\n", new ArrayList<>(), new ArrayList<>());
    	Room solar = new Room("Solar", "You are in the solar.\n", new ArrayList<>(), new ArrayList<>());
    	Room library = new Room("Library", "You are in the library.\n", new ArrayList<>(), new ArrayList<>());
    	Room servantsQuarters = new Room("Servants Quarters", "You are in the servant's quarters.\n", new ArrayList<>(), new ArrayList<>());
    	Room chapel = new Room("Chapel", "You are in the chapel.\n", new ArrayList<>(), new ArrayList<>());
    	Room gatehouse = new Room("Gatehouse", "You are in the gatehouse.\n", new ArrayList<>(), new ArrayList<>());
    	Room greatHall = new Room("Great Hall", "You are in the great hall.\n", new ArrayList<>(), new ArrayList<>());
    	Room courtyard = new Room("Courtyard", "You are in the courtyard.\n", new ArrayList<>(), new ArrayList<>());
    	Room iceHouse = new Room("Ice House", "You are in the ice house.\n", new ArrayList<>(), new ArrayList<>());
    	Room dovecote = new Room("Dovecote", "You are in the dovecote. There are doves here.\n", new ArrayList<>(), new ArrayList<>());
    	Room guardroom = new Room("Guardroom", "You are in the guardroom.\n", new ArrayList<>(), new ArrayList<>());
    	Room study = new Room("Study", "You are in the study.\n", new ArrayList<>(), new ArrayList<>());
    	Room undercroft = new Room("Undercroft", "You are in the Undercroft.\n", new ArrayList<>(), new ArrayList<>());
    	
        dungeon.setItems( new Item("shirt","smelly",1,"equipment","body",3));
        dungeon.setItems( new Item("jacket","cool",1,"equipment","body",3));
        dungeon.setItems( new Item("shoes","smelly",1,"equipment","feet",1));
        dungeon.setItems( new Item("boots","smelly",1,"equipment","feet",1));
        dungeon.setCreatures( new NonPlayer("platypus","semi-aquatic, egg-laying mammal of action",1,5,5,2,2,1,1,null,null));

        cell.setItems( new Item("pants","wet",1,"equipment","legs",2));
        cell.setCreatures( new NonPlayer("bugbear","fluffy",3,13,20,10,10,3,3,null,null));
        
        messHall.setItems( new Item("gloves","stained",1,"equipment","hands",1));
        messHall.setCreatures( new NonPlayer("warg","fast",10,35,80,20,20,10,10,null,null));
        
        privy.setItems( new Item("shoes","smelly",1,"equipment","feet",1));
        privy.setItems( new Item("boots","smelly",1,"equipment","feet",1));
        privy.setItems( new Item("toilet paper","cool",1,"item","none",0));
        privy.setCreatures( new NonPlayer("goblin","weak",6,22,41,15,15,6,6,null,null));

        dovecote.setCreatures( new NonPlayer("bloodthirsty dove","bloodthirsty",5,22,41,15,15,6,6,null,null));
        
        larder.setItems( new Item("wine","tasty",0,"consumable","FOOD",0));
        larder.setItems( new Item("mead","tasty",0,"consumable","FOOD",0));
        larder.setItems( new Item("spirits","tasty",0,"consumable","FOOD",0));
        larder.setItems( new Item("mutton","it's sheep, you're eating sheep",0,"consumable","FOOD",0));
        
        cell.setExits( new String("east"), dungeon);
        dungeon.setExits( new String("south"), privy);
        dungeon.setExits( new String("east"), messHall);
        dungeon.setExits( new String("west"), cell);
        dungeon.setExits( new String("north"), dovecote);
        messHall.setExits( new String("west"), dungeon);
        messHall.setExits( new String("east"), larder);
        larder.setExits( new String("west"), messHall);
        privy.setExits( new String("north"), dungeon);
        dovecote.setExits( new String("south"), dungeon);
        
        rooms.put("Castle", messHall);
        rooms.put("Castle", privy);
        rooms.put("Castle", cell);
        rooms.put("Castle", dungeon);
    }

    public static void print(Room room) {
    	System.out.println("<<<<<======= " + room.getName() + " =======>>>>>");
        System.out.println(room.getDescription());
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

    // Remove item from room when added to inventory
    public static void removeItem(Room room, Item item) {

        room.deleteItem(item);
    }

    public static void addItem(Room room, Item item) {

        room.addItem(item);
    }
}
