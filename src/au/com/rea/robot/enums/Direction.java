package au.com.rea.robot.enums;

import au.com.rea.robot.entities.Coordinate;

/** A Compass direction, this has been designed so that it would be possible
 *   to put in potential other angles*/
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
	
	/** Returns the degrees for this Direction object */
	public int getDegrees() {
		return degrees;
	}
	
	/** Returns the Direction for the given degrees */
	public static Direction getForDegrees(int degrees) {
		for (Direction direction : Direction.values()) {
			if (direction.degrees == degrees) {
				return direction;
			}
		}
		
		return null;
	}

	/** Returns the coordinate difference for a single movement in this direction */
	public Coordinate getCoordinateComponent() {
		return coordinateComponent;
	}

	/** Returns the Direction for the given name, or null if not found */
	public static Direction getForName(String name) {
		for (Direction direction : Direction.values()) {
			if (direction.name().equalsIgnoreCase(name)) {
				return direction;
			}
		}
		
		return null;
	}
}
