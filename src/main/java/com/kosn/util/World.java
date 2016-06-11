package com.kosn.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.kosn.data.db.BuildRoomDAO;
import com.kosn.data.db.LoadEntityPools;
import com.kosn.entity.Room;

public class World {
	
	private static Map<String, String> creaturePool = new HashMap<String, String>();
    private static Map<String, String> roomPool = new HashMap<String, String>();
    private static Map<String, String> itemPool = new HashMap<String, String>();
    private static final Random random = new Random();
    public static Map<String, Room> rooms = new HashMap<String, Room>();
    private static final Directions[] directions = Directions.values();
    private static final int directionSize = directions.length;
    private static final boolean loadFromSave = false;
	
	public static Map<String, Room> build() {
		
		loadEntityPools();
		
		if (!loadFromSave) {
			generateRooms();
			connectTheRooms();
	    	loadTheRoomsWithJunk();
	    	populateTheRooms();
	    	return rooms;
    	}

	    BuildRoomDAO.buildRoomsFromFile("roomSave", 2);
    	BuildRoomDAO.buildRoomsFromFile("creatureList", 10);
    	BuildRoomDAO.buildRoomsFromFile("otherNpcPlacement", 3);
    	BuildRoomDAO.buildRoomsFromFile("exitSave", 3);
    	BuildRoomDAO.buildRoomsFromFile("itemSave", 7);
		return rooms;
    }

	private static void populateTheRooms() {
		// TODO Separate thread to handle creature spawning/roaming
		
	}

	private static void loadTheRoomsWithJunk() {
		// load items from save for now, or forever!
    	BuildRoomDAO.buildRoomsFromFile("itemSave", 7);
	}

	private static void connectTheRooms() {

		Map<Directions, Room> currentExits = new HashMap<Directions, Room>();
		Room roomToAdd = null;
		Directions directionToAdd = null;
		List<Room> roomList;
		
		roomList = getArrayOfRooms();
		
		for (Room r : rooms.values()) {
			currentExits = r.getExits();
			int numExitsToAdd = random.nextInt(3) + 1;
			
			while (currentExits.size() < numExitsToAdd) {
				
				directionToAdd = directions[random.nextInt(directionSize)];
				
				//don't add the same direction to the same room twice
				if (currentExits.containsKey(directionToAdd)) {
					continue;
				}
				
				//get a random room from rooms Map
				int randomIndex = random.nextInt(roomList.size());
				roomToAdd = roomList.get(randomIndex);
				
				//don't add the exit if exit is to the current room
				if (r.equals(roomToAdd)) {
					continue;
				}

				r.getExits().put(directionToAdd, roomToAdd);

				rooms.get(roomToAdd.getName()).getExits().put(getOppositeDirection(directionToAdd), r);
			}
		}
	}
	

	private static List<Room> getArrayOfRooms() {
		List<Room> roomArray = new ArrayList<Room>();
		
		for (Room r : rooms.values()) {
			roomArray.add(r);
		}
		
		if (roomArray.isEmpty()) {
			throw new RuntimeException("No rooms found");
		}
		
		return roomArray;
	}

	private static Directions getOppositeDirection(Directions directionToAdd) {
		Directions opposite = null;
		
		switch (directionToAdd) {
		case west:
			opposite = Directions.east;
			break;
		case east:
			opposite = Directions.west;
			break;
		case north:
			opposite = Directions.south;
			break;
		case south:
			opposite = Directions.north;
			break;
			default:
				throw new RuntimeException("Invalid direction passed");
		}
	
		return opposite;
	}

	private static void generateRooms() {
		//algorithm to go through room pool and generate room objects
		for(Entry<String, String> entry: roomPool.entrySet()) {
            rooms.put(entry.getKey(), new Room(entry.getKey(), entry.getValue()));
        }
	}

	private static void loadEntityPools() {
		creaturePool = LoadEntityPools.importObjects("creature");
    	roomPool = LoadEntityPools.importObjects("room");
    	itemPool = LoadEntityPools.importObjects("item");
	}

	public static Map<String, String> getCreaturePool() {
		// TODO Auto-generated method stub
		return creaturePool;
	}
	
	
}
