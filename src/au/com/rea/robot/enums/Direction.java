package au.com.rea.robot.enums;

import au.com.rea.robot.entities.Coordinate;

public enum Direction {
	NORTH(0, new Coordinate(0,1)),
	EAST(90, new Coordinate(1,0)),
	SOUTH(180, new Coordinate(0,-1)),
	WEST(270, new Coordinate(-1,0));

	private int degrees;
	private Coordinate coordinateComponent;

	Direction(int degrees, Coordinate coordinateComponent) {
		this.degrees = degrees;
		this.coordinateComponent = coordinateComponent;
	}
	
	public int getDegrees() {
		return degrees;
	}
	
	public static Direction getForDegrees(int degrees) {
		for (Direction direction : Direction.values()) {
			if (direction.degrees == degrees) {
				return direction;
			}
		}
		
		return null;
	}

	public Coordinate getCoordinateComponent() {
		return coordinateComponent;
	}

	public static Direction getForName(String name) {
		for (Direction direction : Direction.values()) {
			if (direction.name().equalsIgnoreCase(name)) {
				return direction;
			}
		}
		
		return null;
	}
}
