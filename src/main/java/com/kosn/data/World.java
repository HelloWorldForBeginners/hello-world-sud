package com.kosn.data;

import com.kosn.data.db.BuildRoomDAO;

public class World {
	
	public static void build() {
    	
    	BuildRoomDAO.getPath();
    	BuildRoomDAO.buildRoomsFromFile("roomList", 3);
    	BuildRoomDAO.buildRoomsFromFile("itemList", 7);
//    	BuildRoomDAO.buildRoomsFromFile("creatureList", 10);
    	BuildRoomDAO.buildRoomsFromFile("exitList", 3);
    }
	
	
	
}
