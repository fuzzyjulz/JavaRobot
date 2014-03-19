package entity;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import au.com.rea.robot.entities.BoundaryCoordinates;
import au.com.rea.robot.entities.Coordinate;
import au.com.rea.robot.exceptions.DimensionException;

@RunWith(JUnit4.class)
public class BoundaryCoordinatesTest  {
	@Test
	public void testIsCoordinateWithinBounds() throws DimensionException {
		BoundaryCoordinates boundary = new BoundaryCoordinates(
											new Coordinate(-1, -2), 
											new Coordinate(3, 4));
		
		assertTrue(boundary.isCoordinateWithinBounds(new Coordinate(0, 0)));
		assertTrue(boundary.isCoordinateWithinBounds(new Coordinate(-1, 0)));
		assertTrue(boundary.isCoordinateWithinBounds(new Coordinate(0, -2)));
		assertTrue(boundary.isCoordinateWithinBounds(new Coordinate(3, 0)));
		assertTrue(boundary.isCoordinateWithinBounds(new Coordinate(0, 4)));

		assertFalse(boundary.isCoordinateWithinBounds(new Coordinate(-2, 0)));
		assertFalse(boundary.isCoordinateWithinBounds(new Coordinate(0, -3)));
		assertFalse(boundary.isCoordinateWithinBounds(new Coordinate(4, 0)));
		assertFalse(boundary.isCoordinateWithinBounds(new Coordinate(0, 5)));
	}
}
