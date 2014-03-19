package enums;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import au.com.rea.robot.enums.Direction;

@RunWith(JUnit4.class)
public class DirectionTest  {
	@Test
	public void testDegrees() {
		assertEquals(Direction.NORTH.getDegrees(), 0);
		assertEquals(Direction.EAST.getDegrees(), 90);
		assertEquals(Direction.SOUTH.getDegrees(), 180);
		assertEquals(Direction.WEST.getDegrees(), 270);
		
		assertEquals(
				Direction.getForDegrees(Direction.SOUTH.getDegrees()), 
				Direction.SOUTH);

		assertEquals(
				Direction.getForDegrees(Direction.NORTH.getDegrees()), 
				Direction.NORTH);

		assertEquals(
				Direction.getForDegrees(Direction.EAST.getDegrees()), 
				Direction.EAST);

		assertEquals(
				Direction.getForDegrees(Direction.WEST.getDegrees()), 
				Direction.WEST);
	}
}
