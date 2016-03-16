package com.knightsofsomethingnotable;

import java.util.ArrayList;
import java.util.HashMap;

public class Commands {

    // This file currently not used
    // Should hold all command constants and their attributes
    // To be implemented later
	
    public enum Command {
        // System commands
        INVENTORY,
        INV,
        EQ,
        EQUIP,
        EQUIPMENT,
        PLAYER,
        RESTORE,
        SAVE,
        RESTART,
        QUIT,
        // Action commands
        OPEN,
        CLOSE,
        GET,
        TALK,
        LOOK,
        READ,
        CALL,
        KILL,
        LEAVE,
        GO,
        TURN,
        // Direction commands
        NORTH,SOUTH,EAST,WEST,
        N,S,E,W,
        NW,NE,SE,SW,
        NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST
	}
    

    // if CommandTest is not static, following error occurs: 
    //    No enclosing instance of type Commands is accessible. Must qualify the allocation with an 
    //	  enclosing instance of type Commands (e.g. x.new A() where x is an instance of Commands).
    public static class CommandTest {
    	Command command;
    	
    	public CommandTest(Command command) {
    		this.command = command;
    	}
    
		public void TakeAction(int WIDTH, int HEIGHT, int x, int y, Room[][] room, ArrayList<Item> inventory, HashMap<String, Item> equipment, Player player, boolean playing) {
			switch (command) {
			case INVENTORY:
			case INV:
                Inventory.print(inventory);
				break;
			case EQ:
			case EQUIP:
			case EQUIPMENT:
				Equipment.print(equipment);		
				break;
			case PLAYER:
				Player.printPlayerInfo(player);
				break;
			case WEST:
			case W:
				if (x > 0) {
					x--;
					World.print(room, x, y);
				} else {
					System.out.println("You can't go that way.");
				}
				break;
			case EAST:
			case E:
				if (x < WIDTH -1) {
					x++;
					World.print(room, x, y);
				} else {
					System.out.println("You can't go that way.");
				}
				System.out.println("east");
				break;
			case NORTH:
			case N:
				if (y > 0) {
					y--;
					World.print(room, x, y);
				} else {
					System.out.println("You can't go that way.");
				}
				break;
			case SOUTH:
			case S:
				if (y < HEIGHT - 1) {
					y++;
					World.print(room, x, y);
				} else {
					System.out.println("You can't go that way.");
				}
				break;
			case CALL:
				break;
			case CLOSE:
				break;
			case GET:
				break;
			case GO:
				break;
			case KILL:
				break;
			case LEAVE:
				break;
			case LOOK:
				World.print(room, x, y);
				break;
			case NE:
			case NORTHEAST:
				break;
			case NW:
			case NORTHWEST:
				break;
			case OPEN:
				break;
			case QUIT:
				System.out.println("Goodbye!");
				playing = false;
				break;
			case READ:
				break;
			case RESTART:
				break;
			case RESTORE:
				break;
			case SAVE:
				break;
			case SE:
			case SOUTHEAST:
				break;
			case SW:
			case SOUTHWEST:
				break;
			case TALK:
				break;
			case TURN:
				break;
			default:
				System.out.println("You can't do that.");
				break;
			}
		}
	}
}
