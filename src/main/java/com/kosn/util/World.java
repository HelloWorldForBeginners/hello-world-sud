package com.kosn.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.kosn.db.EntityFactory;
import com.kosn.entity.Ability;
import com.kosn.entity.Item;
import com.kosn.entity.ItemType;
import com.kosn.entity.NonPlayer;
import com.kosn.entity.Room;

public class World {
	
    private List<NonPlayer> creaturePool = new ArrayList<NonPlayer>();
    private List<Room> roomPool = new ArrayList<Room>();
	private List<Item> itemPool = new ArrayList<Item>();
    private List<Ability> abilityPool = new ArrayList<Ability>();
    
    private List<Item> itemsAddedAtLoad = new ArrayList<Item>();

    private final Random random = new Random();
    private Map<String, Room> rooms = new HashMap<String, Room>();
    private final Direction[] directions = Direction.values();
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
    	populateTheRooms();
    	return rooms;
	    	
	    // TODO add saved game loading
    }

	private void populateTheRooms() {
		// TODO Separate thread to handle creature spawning/roaming
	}
	
	/**
	 * add 0-2 items
	 * if a nonconsumable has been added to a room already, don't add it again
	 * use itemsAdded map to check this
	 * consumable items - doesn't matter how many get added
	 */
	private Room loadRoomWithJunk(Room r) {
		Room roomToJunkify = r;
		int randomIndex = 0;
		Item itemToAdd = null;
		List<Item> roomItems = new ArrayList<Item>();
		int numItemsToAdd = random.nextInt(3);
		
		for (int i = 0; i < numItemsToAdd; i++) {
			randomIndex = random.nextInt(itemPool.size());
			itemToAdd = itemPool.get(randomIndex);
			
			if (itemToAdd.getType().equals(ItemType.nonconsumable)) {
				if (itemsAddedAtLoad.contains(itemToAdd)) {
					continue;
				}
			}
			itemsAddedAtLoad.add(itemToAdd);
			roomItems.add(itemToAdd);		
		}
		roomToJunkify.setItems(roomItems);
		return roomToJunkify;
	}

	private void connectTheRooms() {
		Map<Direction, Room> currentExits = new HashMap<Direction, Room>();
		Room roomToAdd = null;
		Direction directionToAdd = null;
		
		for (Room r : rooms.values()) {
			currentExits = r.getExits();
			int numExitsToAdd = random.nextInt(3) + 1;
			
			while (currentExits.size() < numExitsToAdd) {
				
				directionToAdd = directions[random.nextInt(directionSize)];
				//don't add the same direction to the same room twice
				if (currentExits.containsKey(directionToAdd)) {
					continue;
				}
				//get a random room from rooms List
				int randomIndex = random.nextInt(roomPool.size());
				roomToAdd = roomPool.get(randomIndex);
				//don't add the exit if exit is to the current room
				if (r.equals(roomToAdd)) {
					continue;
				}

				r.getExits().put(directionToAdd, roomToAdd);
				rooms.get(roomToAdd.getName()).getExits().put(Direction.getOppositeDirection(directionToAdd), r);
			}
		}
	}

	private void generateRooms() {
		for(Room r : roomPool) {
    		loadRoomWithJunk(r);
            rooms.put(r.getName(), r);
        }
	}

	private void loadEntityPools() throws JsonParseException, JsonMappingException, IOException {
		creaturePool = entityFactory.createNonPlayers();
		roomPool = entityFactory.createRooms();
		itemPool = entityFactory.createItems();
		abilityPool = entityFactory.createAbilities();
	}

	public List<NonPlayer> getCreaturePool() {
		return creaturePool;
	}
	
    public List<Item> getItemPool() {
		return itemPool;
	}
    
	public void addCreatureToRoom(Room roomToAddCreatureTo, NonPlayer creatureToAdd) {
		rooms.get(roomToAddCreatureTo.getName()).addCreature(creatureToAdd);
	}
	
	public void removeCreatureFromRoom(Room room, NonPlayer creature) {
		rooms.get(room.getName()).getCreatures().remove(creature);
	}
}
