package au.com.rea.robot.exceptions;

import au.com.rea.robot.entities.Coordinate;

/** An exception related to some kind of dimension related issue. 
 * May optionally contain a NewLocation */
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

	/** Returns the location that generated the exception, this is most 
	 *   likely an out of boundaries location*/
	public Coordinate getNewLocation() {
		return newLocation;
	}
}
