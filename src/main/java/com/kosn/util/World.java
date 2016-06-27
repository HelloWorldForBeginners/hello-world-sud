package com.kosn.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.kosn.data.db.EntityFactory;
import com.kosn.data.dto.Item;
import com.kosn.data.dto.NonPlayer;
import com.kosn.data.dto.Room;

public class World {
	
    private List<NonPlayer> creaturePool = new ArrayList<NonPlayer>();
    private List<Room> roomPool = new ArrayList<Room>();
    private List<Item> itemPool = new ArrayList<Item>();

    private final Random random = new Random();
    private Map<String, Room> rooms = new HashMap<String, Room>();
    private final Directions[] directions = Directions.values();
    private final int directionSize = directions.length;
    private final boolean loadFromSave = false;
	
    private EntityFactory entityFactory = EntityFactory.getInstance();
    
	//singleton
	private static World instance = null;
	protected World() {
	}
	public static World getInstance() {
		if(instance == null) {
			instance = new World();
		}
		return instance;
	}
    
	public Map<String, Room> buildNewWorld() {
		
		try {
			loadEntityPools();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// newgame loading
			generateRooms();
			connectTheRooms();
	    	loadTheRoomsWithJunk();
	    	populateTheRooms();
	    	return rooms;
	    	
	    // TODO add saved game loading
    }

	private void populateTheRooms() {
		// TODO Separate thread to handle creature spawning/roaming
	}

	private void loadTheRoomsWithJunk() {
		// TODO go through itemPool and randomly add items
	}

	private void connectTheRooms() {

		Map<Directions, Room> currentExits = new HashMap<Directions, Room>();
		Room roomToAdd = null;
		Directions directionToAdd = null;
		
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
				int randomIndex = random.nextInt(roomPool.size());
				roomToAdd = roomPool.get(randomIndex);
				
				//don't add the exit if exit is to the current room
				if (r.equals(roomToAdd)) {
					continue;
				}

				r.getExits().put(directionToAdd, roomToAdd);

				rooms.get(roomToAdd.getName()).getExits().put(getOppositeDirection(directionToAdd), r);
			}
		}
	}

	private Directions getOppositeDirection(Directions directionToAdd) {
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

	private void generateRooms() {
		for(Room r : roomPool) {
            rooms.put(r.getName(), r);
        }
	}

	private void loadEntityPools() throws JsonParseException, JsonMappingException, IOException {
		creaturePool = entityFactory.createNonPlayers();
		roomPool = entityFactory.createRooms();
		itemPool = entityFactory.createItems();
	}

	public List<NonPlayer> getCreaturePool() {
		return creaturePool;
	}
}
