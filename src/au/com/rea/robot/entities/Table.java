package au.com.rea.robot.entities;

import au.com.rea.robot.exceptions.DimensionException;

/** A table object is basically a boundary of coordinates */
public class Table extends BoundaryCoordinates{

	public Table(Coordinate minDimension, Coordinate maxDimension) 
			throws DimensionException {
		super(minDimension, maxDimension);
	}
}
