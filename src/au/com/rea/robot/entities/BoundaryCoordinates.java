package au.com.rea.robot.entities;

import au.com.rea.robot.exceptions.DimensionException;

/** Represents a bounding surface. */
public class BoundaryCoordinates {
	final Coordinate minDimension, maxDimension;
	
	public BoundaryCoordinates(Coordinate minDimension, Coordinate maxDimension) 
			throws DimensionException {
		this.minDimension = minDimension;
		this.maxDimension = maxDimension;
		if (this.minDimension.x > this.maxDimension.x ||
			this.minDimension.y > this.maxDimension.y) {
			throw new DimensionException(
					"The minimum dimensions"+this.minDimension+
					" must be greater than the maximum dimension"+this.maxDimension+
					" for a surface");
		}
	}
	
	/** Returns true if the given coordinate is within the boundaries. */
	public boolean isCoordinateWithinBounds(Coordinate newLocation) {
		if (newLocation.x < minDimension.x || newLocation.x > maxDimension.x)
			return false;
		
		if (newLocation.y < minDimension.y || newLocation.y > maxDimension.y)
			return false;
		
		return true;
	}
}
