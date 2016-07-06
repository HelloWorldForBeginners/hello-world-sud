package com.kosn.application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.kosn.entity.NonPlayer;
import com.kosn.entity.NonPlayerDefaults;
import com.kosn.entity.Room;
import com.kosn.util.Direction;
import com.kosn.util.World;

public class CreatureManager implements Runnable {
	
	private int creatureCount = 0;
	private final int maxCreatureCount = 100;
	private static World world = World.getInstance();
	private final List<NonPlayer> creaturePool = world.getCreaturePool();
	private List<Room> roomsWithCreatures = new ArrayList<Room>();
    private final Random random = new Random();
    private final int timeToWait = 10000;
    private Map<String, Room> rooms = Application.getRooms();

	@Override
	public void run() {
		while (Application.getPlaying()) {
			processCreatures();
			try {
				Thread.sleep(timeToWait);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	private void processCreatures() {
		roomsWithCreatures = getRoomsWithCreatures();
		if (roomsWithCreatures.isEmpty() || creatureCount < maxCreatureCount) {
			spawnCreature();
		}
		if (creatureCount == 1) {
			return;
		}
		Iterator<Room> roomIterator = roomsWithCreatures.iterator();
		while (roomIterator.hasNext()) {
			Room room = roomIterator.next();
			Iterator<NonPlayer> creatureIterator = room.getCreatures().iterator();
			while (creatureIterator.hasNext()) {
				if (creatureIterator.next().getLevel() < Application.getPlayer().getLevel() - 3) {
					creatureIterator.remove();
					continue;
				}
				Room roomToAddCreatureTo = getRoomToAddCreatureTo(room);
				moveCreatures(roomToAddCreatureTo, room, creatureIterator);
			}
		}
	}

	private List<Room> getRoomsWithCreatures() {
		creatureCount = 0;
		List<Room> roomsWithCreatures = new ArrayList<Room>();
		for (Room room : rooms.values()) {
			if (!room.getCreatures().isEmpty()) {
				roomsWithCreatures.add(room);
				creatureCount += room.getCreatures().size();
			}
		}
		return roomsWithCreatures;
	}

	private void spawnCreature() {
		Room roomToAddCreatureTo = getRoomToAddCreatureTo();
		NonPlayer creatureToAdd = getCreatureToAdd();
		addCreatureToRoom(roomToAddCreatureTo, creatureToAdd);
	}

	private void addCreatureToRoom(Room roomToAddCreatureTo, NonPlayer creatureToAdd) {
		world.addCreatureToRoom(roomToAddCreatureTo, creatureToAdd);
		if (Application.getCurrentRoom().equals(roomToAddCreatureTo)) {
			System.out.println(creatureToAdd.getName() + " spawned in the " + roomToAddCreatureTo.getName());
		}
		creatureCount++;
	}

	private NonPlayer getCreatureToAdd() {
		return new NonPlayer(new NonPlayerDefaults());
	}

	private Room getRoomToAddCreatureTo() {
		Room currentRoom = Application.getCurrentRoom();
		
		List<Room> availableRooms = new ArrayList<Room>(); 
		availableRooms.add(currentRoom);
		
		for (Map.Entry<Direction, Room> entry : currentRoom.getExits().entrySet()) {
			availableRooms.add(entry.getValue());
		}
	
		int randomIndex = random.nextInt(availableRooms.size());
		return availableRooms.get(randomIndex);
	}

	private Room getRoomToAddCreatureTo(Room room) {
		Map<Direction, Room> exits = room.getExits();
		List<Room> availableRooms = new ArrayList<Room>();
		
		for (Room nextRoom : exits.values()) {
			availableRooms.add(nextRoom);
		}
		
		int randomIndex = random.nextInt(availableRooms.size());
		return availableRooms.get(randomIndex);
	}

	private void moveCreatures(Room roomMovingTo, Room roomMovingFrom, Iterator<NonPlayer> creatureIterator) {
		if (!creatureIterator.hasNext()) {
			return;
		}
		NonPlayer creature = creatureIterator.next();
		NonPlayer currentCombatTarget = Application.getCurrentCombatTarget();
		if (currentCombatTarget != null) {
			if (Application.getCurrentCombatTarget().equals(creature) && Application.getCombat()) {
				return;
			}
		}
		world.addCreatureToRoom(roomMovingTo, creature);
		if (Application.getCurrentRoom().equals(roomMovingTo)) {
			System.out.println(creature.getName() + " has entered the " + roomMovingTo.getName());
		}
		creatureIterator.remove();
		if (Application.getCurrentRoom().equals(roomMovingFrom)) {
			System.out.println(creature.getName() + " has exited the " + roomMovingFrom.getName());
		}
	}
}
