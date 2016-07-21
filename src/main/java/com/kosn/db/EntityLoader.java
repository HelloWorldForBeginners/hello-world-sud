package com.kosn.db;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.kosn.entity.EquipSlot;
import com.kosn.entity.Item;
import com.kosn.entity.Player;
import com.kosn.entity.Room;

public class EntityLoader {
	private File file;
	private static final String path = getPath();
	private static final String resourcesPath = String.format("%ssrc/main/resources/", path);
	private static final String entityRepositoryPath = "profiles/";

	private static final String equipmentSaveFileName = "equipment.json";
	private static final String exitsSaveFileName = "exits.json";
	private static final String inventorySaveFileName = "inventory.json";
	private static final String itemsSaveFileName = "items.json";
	private static final String playerSaveFileName = "player.json";
	private static final String roomsSaveFileName = "rooms.json";
	
	//singleton
	private static EntityLoader instance = null;
	protected EntityLoader() {
	}
	public static EntityLoader getInstance() {
		if(instance == null) {
			instance = new EntityLoader();
		}
		return instance;
	}

	public static String getPath() {
		File currDir = new File(".");
		String path = "";
		path = currDir.getAbsolutePath();
		return path.substring(0, path.length()-1);
	}
	
	public Player loadPlayer(String characterName) {
		Player player = new Player(null);
		
		player.setInventory(loadInventory(player));
		player.setEquipment(loadEquipment(player));

		
		// TODO Auto-generated method stub
		return null;
	}
	
	private HashMap<EquipSlot, Item> loadEquipment(Player player) {
		// TODO Auto-generated method stub
		return null;
	}
	private ArrayList<Item> loadInventory(Player player) {
		// TODO Auto-generated method stub
		return null;
	}
	public Map<String, Room> loadRooms(String characterName) {
		Map<String, Room> rooms = new HashMap<String, Room>(); 
		//some json loader from characterName/rooms.json
		//add exits to rooms using some json loader for characterName/exits.json
		//add items to rooms using some json loader for characterName/items.json
		return rooms;
	}
}
