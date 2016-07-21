package com.kosn.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.kosn.application.Application;
import com.kosn.application.GameState;
import com.kosn.entity.NonPlayer;
import com.kosn.entity.NonPlayerDefaults;
import com.kosn.entity.Room;

public class CreatureManager implements Runnable {
	
	private int creatureCount = 0;
	private final int maxCreatureCount = 20;
	private static World world = World.getInstance();
	private GameState gameState = GameState.getInstance();
	private List<Room> roomsWithCreatures = new ArrayList<Room>();
    private final Random random = new Random();
    private final double timeToWait = 10;
    private Map<String, Room> rooms = world.getRooms();
    

	@Override
	public void run() {
		while (gameState.getPlaying()) {
			processCreatures();
			FlowManagement.wait(timeToWait);
		}
	}

	private void processCreatures() {
		roomsWithCreatures = getRoomsWithCreatures();
		if (roomsWithCreatures.isEmpty() || creatureCount < maxCreatureCount) {
			spawnCreature();
		}
		if (creatureCount <= 1) { return; }
		Iterator<Room> roomIterator = roomsWithCreatures.iterator();
		while (roomIterator.hasNext()) {
			Room room = roomIterator.next();
			Iterator<NonPlayer> creatureIterator = room.getCreatures().iterator();
			while (creatureIterator.hasNext()) {
				if (creatureIterator.next().getLevel() < world.getPlayer().getLevel() - 3) {
					creatureIterator.remove();
					continue;
				}
				if (shouldCreatureMove()) {
					Map<Direction, Room> roomToAddCreatureTo = getRoomToMoveCreatureTo(room);
					moveCreatures(roomToAddCreatureTo, room, creatureIterator);
				}
			}
		}
	}

	private boolean shouldCreatureMove() {
		int rollForMove = random.nextInt(100);
		if (rollForMove > 30) { return false; }
		return true;
	}

	private List<Room> getRoomsWithCreatures() {
		this.creatureCount = 0;
		List<Room> roomsWithCreatures = new ArrayList<Room>();
		for (Room room : rooms.values()) {
			if (!room.getCreatures().isEmpty()) {
				roomsWithCreatures.add(room);
				this.creatureCount += room.getCreatures().size();
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
		if (world.getCurrentRoom().equals(roomToAddCreatureTo)) {
			System.out.println(creatureToAdd.getName() + " spawned");
		}
		this.creatureCount++;
	}

	private NonPlayer getCreatureToAdd() {
		return new NonPlayer(new NonPlayerDefaults());
	}

	private Room getRoomToAddCreatureTo() {
		Room currentRoom = world.getCurrentRoom();
		List<Room> availableRooms = new ArrayList<Room>(currentRoom.getExits().values());
		availableRooms.add(currentRoom);
		int randomIndex = random.nextInt(availableRooms.size());
		return availableRooms.get(randomIndex);
	}

	private Map<Direction, Room> getRoomToMoveCreatureTo(Room room) {
		Map<Direction, Room> availableRooms = room.getExits();
		List<Direction> availableDirections = new ArrayList<Direction>(availableRooms.keySet());
		int randomIndex = random.nextInt(availableDirections.size());
		Direction direction = availableDirections.get(randomIndex);
		availableRooms.entrySet().removeIf(e -> !e.getKey().equals(direction));
		return availableRooms; 
	}

	private void moveCreatures(Map<Direction, Room> singleRoomMap, Room roomMovingFrom, Iterator<NonPlayer> creatureIterator) {
		if (!creatureIterator.hasNext()) { return; }
		NonPlayer creature = creatureIterator.next();
		if (checkIfCombatTarget(creature)) { return; }
		Direction direction = new ArrayList<Direction>(singleRoomMap.keySet()).get(0);
		Room roomMovingTo = singleRoomMap.get(direction);
		world.addCreatureToRoom(roomMovingTo, creature);
		if (world.getCurrentRoom().equals(roomMovingTo)) {
			System.out.println(creature.getName() + " has entered from the " + Direction.getOppositeDirection(direction));
		}
		creatureIterator.remove();
		if (world.getCurrentRoom().equals(roomMovingFrom)) {
			System.out.println(creature.getName() + " has exited " + direction);
		}
	}

	private boolean checkIfCombatTarget(NonPlayer creature) {
		NonPlayer currentCombatTarget = Application.getCurrentCombatTarget();
		if (currentCombatTarget != null) {
			if (Application.getCurrentCombatTarget().equals(creature) && Application.getCombat()) {
				return true;
			}
		}
		return false;
	}
}
