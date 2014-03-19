package au.com.rea.robot.entities;

import au.com.rea.robot.exceptions.DimensionException;

public class Table extends BoundaryCoordinates{

	public Table(Coordinate minDimension, Coordinate maxDimension) 
			throws DimensionException {
		super(minDimension, maxDimension);
	}
}
