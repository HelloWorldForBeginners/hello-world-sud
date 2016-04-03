package com.knightsofsomethingnotable;

import java.util.ArrayList;
import java.util.HashMap;

/**
* Created by matt on 1/31/16.
*/

class World {

    public static void build(HashMap<String, Room> rooms) {
    	
    	Room dungeon = new Room("", "", new ArrayList<>(), new ArrayList<>()); //, new HashMap<String, String>());
    	Room messHall = new Room("", "", new ArrayList<>(), new ArrayList<>()); //, new HashMap<String, String>());
    	Room cell = new Room("", "", new ArrayList<>(), new ArrayList<>()); //, new HashMap<String, String>());
    	Room privy = new Room("", "", new ArrayList<>(), new ArrayList<>()); //, new HashMap<String, String>());
        
        dungeon.setName("Dungeon");
        dungeon.setDescription("You are in a dungeon.\n");
        dungeon.setItems( new Item("shirt","smelly",1,"equipment","body",3));
        dungeon.setItems( new Item("jacket","cool",1,"equipment","body",3));
        dungeon.setItems( new Item("shoes","smelly",1,"equipment","feet",1));
        dungeon.setItems( new Item("boots","smelly",1,"equipment","feet",1));
        dungeon.setCreatures( new NonPlayer("platypus","semi-aquatic, egg-laying mammal of action",1,5,5,2,2,1,1,null,null));

        cell.setName("Cell");
        cell.setDescription("You are in a cell.\n");
        cell.setItems( new Item("pants","wet",1,"equipment","legs",2));
        cell.setCreatures( new NonPlayer("bugbear","fluffy",3,13,20,10,10,3,3,null,null));
        
        messHall.setName("Mess Hall");
        messHall.setDescription("You are in the mess hall.\n");
        messHall.setItems( new Item("gloves","stained",1,"equipment","hands",1));
        messHall.setCreatures( new NonPlayer("warg","fast",10,35,80,20,20,10,10,null,null));
        
        privy.setName("Privy");
        privy.setDescription("You are in the privy.\n");
        privy.setItems( new Item("shoes","smelly",1,"equipment","feet",1));
        privy.setItems( new Item("boots","smelly",1,"equipment","feet",1));
        privy.setItems( new Item("toilet paper","cool",1,"item","none",0));
        privy.setCreatures( new NonPlayer("goblin","weak",6,22,41,15,15,6,6,null,null));

        cell.setExits( new String("east"), dungeon);
        dungeon.setExits( new String("south"), privy);
        dungeon.setExits( new String("east"), messHall);
        dungeon.setExits( new String("west"), cell);
        messHall.setExits( new String("west"), dungeon);
        privy.setExits( new String("north"), dungeon);
        
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

    public static String getRoomName(Room[][] room, int x, int y) {

        return room[x][y].getName();
    }

    // Remove item from room when added to inventory
    public static void removeItem(Room room, Item item) {

        room.deleteItem(item);
    }

    public static void addItem(Room room, Item item) {

        room.addItem(item);
    }
    
}
