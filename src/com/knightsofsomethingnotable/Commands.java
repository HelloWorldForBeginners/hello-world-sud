package com.knightsofsomethingnotable;

import java.util.ArrayList;
import java.util.HashMap;

public class Commands {
	final static String noGo = "You can't go that way.";

    public enum Command {
        // System commands
        INVENTORY,
        INV,
        EQ,
        EQUIP,
        EQUIPMENT,
        UNEQUIP,
        PLAYER,
        RESTORE,
        SAVE,
        RESTART,
        QUIT,
        // Action commands
        OPEN,
        CLOSE,
        GET,
        PUT,
        TALK,
        LOOK,
        CHECK,
        READ,
        CALL,
        ATTACK,
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
    	String target;
    	
//    	private int HEIGHT = World.HEIGHT;
    	
    	public CommandTest(Command command, String target) {
    		this.command = command;
    		this.target = target;
    	}

		public void TakeAction(int WIDTH, int HEIGHT, int x, int y, Room[][] room, ArrayList<Item> inventory, HashMap<String, Item> equipment, Player player, boolean playing) {
			switch (command) {
			case INVENTORY:
			case INV:
                Inventory.print(inventory);
				break;
			case EQUIP:
				if (target != "") {
					Equipment.equipItem(x, y, target, player);
					break;
				}
				/* FALLTHROUGH if target == "" */
			case EQ:
			case EQUIPMENT:
				Equipment.print(equipment);
				System.out.println(target);
				break;
			case UNEQUIP:
				Equipment.unequipItem(x, y, target, player);
				break;
			case PLAYER:
				Player.printPlayerInfo(player);
				break;
			case WEST:
			case W:
				if (x > 0) {
					Main.x--;
					World.print(room, Main.x, Main.y);
				} else {
					System.out.println(noGo);
				}
				break;
			case EAST:
			case E:
				if (x < WIDTH - 1) {
					Main.x++;
					World.print(room, Main.x, Main.y);
				} else {
					System.out.println(noGo);
				}
				
				break;
			case NORTH:
			case N:
				if (y > 0) {
					Main.y--;
					World.print(room, Main.x, Main.y);
				} else {
					System.out.println(noGo);
				}
				break;
			case SOUTH:
			case S:
				if (y < HEIGHT - 1) {
					Main.y++;
					World.print(room, Main.x, Main.y);
				} else {
					System.out.println(noGo);
				}
				break;
			case CALL:
				break;
			case CLOSE:
				break;
			case GET:
				Inventory.getItem(x, y, target, player, room);
				break;
			case PUT:
				Inventory.putItem(x, y, target, player, room);
				break;
			case GO:
				break;
			case ATTACK:
				NonPlayer.attackNonPlayer(target, room, x, y, player);
				
				break;
			case KILL:
				break;
			case LEAVE:
				break;
			case LOOK:
				World.print(room, Main.x, Main.y);
				break;
			case CHECK:
				//tries to look at the NonPlayer first, then looks at the items in the room
				if (NonPlayer.printNonPlayerInfo(target, room, x, y).equals("")) {
					Item.printItemInfo(target, room, x, y);
				}
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
				Main.playing = false;
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
