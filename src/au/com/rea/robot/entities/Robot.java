package au.com.rea.robot.entities;

import au.com.rea.robot.enums.Direction;
import au.com.rea.robot.exceptions.DimensionException;

public class Robot {
	private static final int LEFT = -90;
	private static final int RIGHT = 90;
	private static final int FULL_CIRCLE = 360;
	
	
	private Coordinate location;
	private Direction facingDirection;
	private BoundaryCoordinates surface;
	
	/** Creates a new Robot and places it on the given surface */
	public Robot(Coordinate location, Direction direction, BoundaryCoordinates surface) 
			throws DimensionException {
		this.place(location, direction, surface);
	}
	
	/** Creates a new Robot and places it on the given surface. This method is chainable. */
	public Robot place(Coordinate location, Direction direction, BoundaryCoordinates surface) 
			throws DimensionException {
		if (!surface.isCoordinateWithinBounds(location)) {
			throw new DimensionException("The Coordinate"+location + 
					" is outside the boundaries of the new surface.");
		}
		
		this.setFacingDirection(direction);
		this.setLocation(location);
		this.setSurface(surface);
		
		return this;
	}
	
	/** Rotates the Robot 90 degrees left. This method is chainable. */
	public Robot rotateLeft() throws DimensionException {
		this.setFacingDirection(this.facingDirection.getDegrees() + LEFT);
		
		return this;
	}

	/** Rotates the Robot 90 degrees Right. This method is chainable. */
	public Robot rotateRight() throws DimensionException {
		this.setFacingDirection(this.facingDirection.getDegrees() + RIGHT);
		
		return this;
	}
	
	/** Moves the Robot one unit forward. This method is chainable. */
	public Robot moveForward() throws DimensionException {
		Coordinate newLocation = location.add(this.facingDirection.getCoordinateComponent());
		if (!surface.isCoordinateWithinBounds(newLocation)) {
			throw new DimensionException("The movement has put the robot out of boundaries", newLocation);
		}
		this.setLocation(newLocation);
		
		return this;
	}
	
	/** Reports on the Robot's current position.*/
	public String report() {
		return this.location + " " + this.facingDirection.name();
	}
	
	public String toString() {
		return report();
	}

	/** Sets the robot's facing direction in degrees. This translates it to the
	 *   internally stored Enum */
	private void setFacingDirection(int degrees) throws DimensionException {
		degrees = getActualDegrees(degrees);
		Direction facingDirection = Direction.getForDegrees(degrees);
		
		if (facingDirection == null) {
			throw new DimensionException("The degrees ("+degrees+") didn't map to a named direction.");
		}
		
		this.setFacingDirection(facingDirection);
	}

	/** Computes the actual degrees taking into account that negative degrees
	 *   should translate correctly into positive degrees, and that any degrees over
	 *   a full circle should translate back into < 360 degrees*/
	private static int getActualDegrees(int degrees) {
		if (degrees >= 0 && degrees < FULL_CIRCLE) {
			return degrees;
		}
		
		if (degrees < 0) {
			return getActualDegrees(degrees + FULL_CIRCLE);
		}
		
		return degrees % FULL_CIRCLE;
	}
	
	/** The coordinate location of the Robot */
	public Coordinate getLocation() {
		return location;
	}

	private void setLocation(Coordinate location) {
		this.location = location;
	}

	/** The direction the Robot faces */
	public Direction getFacingDirection() {
		return facingDirection;
	}

	private void setFacingDirection(Direction facingDirection) {
		this.facingDirection = facingDirection;
	}

	/** The surface that the robot is moving on */
	public BoundaryCoordinates getSurface() {
		return surface;
	}

	private void setSurface(BoundaryCoordinates surface) {
		this.surface = surface;
	}
}
