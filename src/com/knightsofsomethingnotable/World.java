package com.knightsofsomethingnotable;


/**
* Created by matt on 1/31/16.
*/

class World {

    public static void build(Room[][] room, final int WIDTH, final int HEIGHT) {

        // Initialize rooms (a 2D array)
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                room[i][j] = new Room(i, "", "", null, null);
            }
        }

        room[0][0].setNumber(1);
        room[0][0].setName("Dungeon");
        room[0][0].setDescription("You are in a dungeon.");
        room[0][0].setExits("Exits: cell, south; mess hall, east.");
        room[0][0].setItems( new Item("shirt","smelly",1,"equipment","body",3));
        room[0][0].setItems( new Item("jacket","cool",1,"equipment","body",3));
        room[0][0].setItems( new Item("shoes","smelly",1,"equipment","feet",1));
        room[0][0].setItems( new Item("boots","smelly",1,"equipment","feet",1));
        room[0][0].setCreatures( new NonPlayer("platypus","semi-aquatic, egg-laying mammal of action",1,5,5,2,2,1,1,null,null));

        room[0][1].setNumber(2);
        room[0][1].setName("Cell");
        room[0][1].setDescription("You are in a cell.");
        room[0][1].setExits("Exits: privy, east; dungeon, north.");
        room[0][1].setItems( new Item("pants","wet",1,"equipment","legs",2));
        room[0][1].setCreatures( new NonPlayer("bugbear","fluffy",3,13,20,10,10,3,3,null,null));

        room[1][1].setNumber(4);
        room[1][1].setName("Privy");
        room[1][1].setDescription("You are in the privy.");
        room[1][1].setExits("Exits: mess hall, north; cell, west.");
        room[1][1].setItems( new Item("shoes","smelly",1,"equipment","feet",1));
        room[1][1].setItems( new Item("boots","smelly",1,"equipment","feet",1));
        room[1][1].setItems( new Item("toilet paper","cool",1,"item","none",0));
        room[1][1].setCreatures( new NonPlayer("goblin","weak",6,22,41,15,15,6,6,null,null));

        room[1][0].setNumber(3);
        room[1][0].setName("Mess Hall");
        room[1][0].setDescription("You are in the mess hall.");
        room[1][0].setExits("Exits: privy, south; dungeon, west.");
        room[1][0].setItems( new Item("gloves","stained",1,"equipment","hands",1));
        room[1][0].setCreatures( new NonPlayer("warg","fast",10,35,80,20,20,10,10,null,null));
    }


    public static void print(Room[][] room, int x, int y) {

        System.out.println(room[x][y].getDescription());
        System.out.println(room[x][y].getExits());
        System.out.println();
        if (room[x][y].getItems().size() > 0) {
            System.out.println("You see: " + room[x][y].getItems());
        }
        System.out.println();
        if (room[x][y].getCreatures().size() > 0) {
            System.out.println("There are creatures here: " + room[x][y].getCreatures());
        }
        System.out.println();
    }

    public static String getRoomName(Room[][] room, int x, int y) {

        return room[x][y].getName();
    }

    // Remove item from room when added to inventory
    public static void removeItem(Room[][] room, int x, int y, Item item) {

        room[x][y].deleteItem(item);
    }

    public static void addItem(Room[][] room, int x, int y, Item item) {

        room[x][y].addItem(item);
    }
    
//    public int getHeight() {
//    	return world.HEIGHT;
//    }
}
