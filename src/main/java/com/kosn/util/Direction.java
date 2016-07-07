package com.kosn.util;

public enum Direction {
	north, south, east, west;

	public static Direction getOppositeDirection(Direction directionToAdd) {
		Direction opposite = null;
		
		switch (directionToAdd) {
		case west:
			opposite = Direction.east;
			break;
		case east:
			opposite = Direction.west;
			break;
		case north:
			opposite = Direction.south;
			break;
		case south:
			opposite = Direction.north;
			break;
			default:
				throw new RuntimeException("Invalid direction passed");
		}
		return opposite;
	}


}
