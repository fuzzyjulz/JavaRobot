package entity;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import au.com.rea.robot.entities.Coordinate;

@RunWith(JUnit4.class)
public class CoordinateTest  {
	@Test
	public void testAdd() {
		Coordinate coord = new Coordinate(1,2);
		
		assertEquals(coord.x, 1);
		assertEquals(coord.y, 2);
		
		Coordinate secondCoord = new Coordinate(3,5);
		
		assertEquals(secondCoord.x, 3);
		assertEquals(secondCoord.y, 5);
		
		Coordinate addedCoord = coord.add(secondCoord);
		
		assertEquals(coord.x, 1);
		assertEquals(coord.y, 2);
		assertEquals(secondCoord.x, 3);
		assertEquals(secondCoord.y, 5);
		assertEquals(addedCoord.x, 4);
		assertEquals(addedCoord.y, 7);
	}
}
