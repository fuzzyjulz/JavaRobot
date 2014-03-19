package au.com.rea.robot.exceptions;

import au.com.rea.robot.entities.Coordinate;

public class DimensionException extends Exception{
	private static final long serialVersionUID = -5730853702527847519L;
	private Coordinate newLocation;

	public DimensionException(String message) {
		super(message);
	}
	
	public DimensionException(String message, Coordinate newLocation) {
		super(message);
		this.newLocation = newLocation;
	}

	public Coordinate getNewLocation() {
		return newLocation;
	}
}
