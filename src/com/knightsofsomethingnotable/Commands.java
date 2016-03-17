package com.knightsofsomethingnotable;

import java.util.ArrayList;
import java.util.HashMap;

public class Commands {
	final static String noGo = "You can't go that way.";

    public enum Command {
        // System commands
        INVENTORY,
        INV,
        I,
        EQUIPMENT,
        EQUIP,
        EQ,
        UNEQUIP,
        PLAYER,
        P,
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
    	
    	public CommandTest(Command command, String target) {
    		this.command = command;
    		this.target = target;
    	}

    	//TODO: reduce redundant logic in the directional cases
		public void TakeAction(int WIDTH, int HEIGHT, int x, int y, Room[][] room, ArrayList<Item> inventory, HashMap<String, Item> equipment, Player player, boolean playing) {
			switch (command) {
			case INVENTORY:
			case INV:
			case I:
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
			case P:
			case PLAYER:
				Player.printPlayerInfo(player);
				break;
			case WEST:
			case W:
				//TODO: work on this logic. Indicate that exit doesn't exist AND/OR in combat.
				if (x > 0 && Main.combat == false) {
					Main.x--;
					System.out.println("You move to the west");
					System.out.println();
					World.print(room, Main.x, Main.y);
				} else {
					if (Main.combat == true) {
						System.out.println("You're in combat!");
						//TODO: process an attack round? Additional command FLEE, pick random available direction?
					} else {
						System.out.println(noGo);
					}
				}
				break;
			case EAST:
			case E:
				if (x < WIDTH - 1 && Main.combat == false) {
					Main.x++;
					System.out.println("You move to the east");
					System.out.println();
					World.print(room, Main.x, Main.y);
				} else {
					if (Main.combat == true) {
						System.out.println("You're in combat!");
						//TODO: process an attack round? Additional command FLEE, pick random available direction?
					} else {
						System.out.println(noGo);
					}
				}
				break;
			case NORTH:
			case N:
				if (y > 0 && Main.combat == false) {
					Main.y--;
					System.out.println("You move to the north");
					System.out.println();
					World.print(room, Main.x, Main.y);
				} else {
					if (Main.combat == true) {
						System.out.println("You're in combat!");
						//TODO: process an attack round? Additional command FLEE, pick random available direction?
					} else {
						System.out.println(noGo);
					}
				}
				break;
			case SOUTH:
			case S:
				if (y < HEIGHT - 1 && Main.combat == false) {
					Main.y++;
					System.out.println("You move to the south");
					System.out.println();
					World.print(room, Main.x, Main.y);
				} else {
					if (Main.combat == true) {
						System.out.println("You're in combat!");
						//TODO: process an attack round? Additional command FLEE, pick random available direction?
					} else {
						System.out.println(noGo);
					}
				}
				break;
			case GET:
				Inventory.getItem(x, y, target, player, room);
				break;
			case PUT:
				Inventory.putItem(x, y, target, player, room);
				break;
			case ATTACK:
				System.out.println("Combat started...");
				System.out.println();
				Main.combat = true;

				NonPlayer.attackNonPlayer(target, room, x, y, player);
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
			case QUIT:
				System.out.println("Goodbye!");
				Main.playing = false;
				break;
				
				
			//Inactive commands
			case GO:
				break;
			case KILL:
				break;
			case LEAVE:
				break;
			case NE:
			case NORTHEAST:
				break;
			case NW:
			case NORTHWEST:
				break;
			case OPEN:
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
			case CALL:
				break;
			case CLOSE:
				break;
				
			default:
				System.out.println("You can't do that.");
				break;
			}
		}
	}
}
