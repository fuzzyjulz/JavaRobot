package entity;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import au.com.rea.robot.entities.BoundaryCoordinates;
import au.com.rea.robot.entities.Coordinate;
import au.com.rea.robot.entities.Robot;
import au.com.rea.robot.entities.Table;
import au.com.rea.robot.enums.Direction;
import au.com.rea.robot.exceptions.DimensionException;

@RunWith(JUnit4.class)
public class RobotTest  {
	Table table;
	Robot testRobot;
	@Before
	public void initialise() throws DimensionException {
		table = new Table(new Coordinate(0, 0), new Coordinate(4, 5));
		testRobot = new Robot(new Coordinate(0, 0), Direction.NORTH, table);
	}
	
	@Test
	public void testTurnLeft() throws DimensionException {
		assertEquals(testRobot.getFacingDirection(), Direction.NORTH);

		//This should become -90 which translates to 270 Deg
		assertEquals(testRobot.rotateLeft().getFacingDirection(), Direction.WEST);
		assertEquals(testRobot.rotateLeft().getFacingDirection(), Direction.SOUTH);
		assertEquals(testRobot.rotateLeft().getFacingDirection(), Direction.EAST);
		
		//This should flick around past 360 Deg for a second time
		assertEquals(testRobot.rotateLeft().getFacingDirection(), Direction.NORTH);
		assertEquals(testRobot.rotateLeft().getFacingDirection(), Direction.WEST);
	}

	@Test
	public void testTurnRight() throws DimensionException {
		assertEquals(testRobot.getFacingDirection(), Direction.NORTH);

		assertEquals(testRobot.rotateRight().getFacingDirection(), Direction.EAST);
		assertEquals(testRobot.rotateRight().getFacingDirection(), Direction.SOUTH);
		assertEquals(testRobot.rotateRight().getFacingDirection(), Direction.WEST);
		
		//This should flick around past 360 Deg from 270 to 0
		assertEquals(testRobot.rotateRight().getFacingDirection(), Direction.NORTH);
		assertEquals(testRobot.rotateRight().getFacingDirection(), Direction.EAST);
	}

	@Test
	public void testPlace() throws DimensionException {
		assertEquals(testRobot.getLocation(), new Coordinate(0, 0));
		assertEquals(testRobot.getFacingDirection(), Direction.NORTH);

		testRobot.place(new Coordinate(4, 5), Direction.SOUTH, table);

		assertEquals(testRobot.getLocation(), new Coordinate(4, 5));
		assertEquals(testRobot.getFacingDirection(), Direction.SOUTH);

		placeBoundaryFailureExpected(new Coordinate(5, 5), Direction.SOUTH, table);

		assertEquals(testRobot.getLocation(), new Coordinate(4, 5));
		assertEquals(testRobot.getFacingDirection(), Direction.SOUTH);

		placeBoundaryFailureExpected(new Coordinate(-1, 5), Direction.SOUTH, table);

		assertEquals(testRobot.getLocation(), new Coordinate(4, 5));
		assertEquals(testRobot.getFacingDirection(), Direction.SOUTH);

		placeBoundaryFailureExpected(new Coordinate(0, -1), Direction.SOUTH, table);

		assertEquals(testRobot.getLocation(), new Coordinate(4, 5));
		assertEquals(testRobot.getFacingDirection(), Direction.SOUTH);
		
		
		//Try on a slightly bigger table and testing -ve coordinates
		table = new Table(new Coordinate(-1, -2), new Coordinate(5, 6));
		testRobot.place(new Coordinate(5, 6), Direction.EAST, table);

		assertEquals(testRobot.getLocation(), new Coordinate(5, 6));
		assertEquals(testRobot.getFacingDirection(), Direction.EAST);
		
		testRobot.place(new Coordinate(-1, -2), Direction.EAST, table);

		assertEquals(testRobot.getLocation(), new Coordinate(-1, -2));
		assertEquals(testRobot.getFacingDirection(), Direction.EAST);
	}
	
	@Test
	public void testMoveForward() throws DimensionException {
		assertEquals(testRobot.getFacingDirection(), Direction.NORTH);
		assertEquals(testRobot.getLocation(), new Coordinate(0, 0));
		
		testRobot.rotateRight().rotateRight();
		assertEquals(testRobot.getFacingDirection(), Direction.SOUTH);
		
		moveForwardBoundaryFailureExpected(); //Testing South Boundary
		assertEquals(testRobot.getLocation(), new Coordinate(0, 0));

		testRobot.rotateRight();
		assertEquals(testRobot.getFacingDirection(), Direction.WEST);
		moveForwardBoundaryFailureExpected(); //Testing west boundary
		
		testRobot.rotateRight();
		assertEquals(testRobot.getFacingDirection(), Direction.NORTH);
		assertEquals(testRobot.getLocation(), new Coordinate(0, 0));

		testRobot.moveForward()
				 .moveForward()
				 .moveForward()
				 .moveForward()
				 .moveForward();
		assertEquals(
				testRobot.getLocation(), 
				new Coordinate(0, 5));

		moveForwardBoundaryFailureExpected(); // Testing North boundary
		
		//We should still be in the same spot after the failure
		assertEquals(testRobot.getLocation(), new Coordinate(0, 5));

		testRobot.rotateRight()
				 .moveForward()
				 .moveForward()
				 .moveForward()
				 .moveForward();

		assertEquals(testRobot.getLocation(), new Coordinate(4, 5));
		assertEquals(testRobot.getFacingDirection(), Direction.EAST);

		moveForwardBoundaryFailureExpected(); // Testing East boundary
	}
	
	@Test
	public void testReport() throws DimensionException {
		assertEquals(testRobot.report(), "[0,0] NORTH");
		
		assertEquals(testRobot.place(new Coordinate(2, 1), Direction.EAST, table)
				.report(), "[2,1] EAST");
	}
	
	@Test
	public void testExampleA() throws DimensionException {
	/*	PLACE 0,0,NORTH
		MOVE
		REPORT
		Output: 0,1,NORTH */

		testRobot.place(new Coordinate(0, 0), Direction.NORTH, table)
				 .moveForward();
		
		assertEquals(testRobot.report(), "[0,1] NORTH");
	}

	@Test
	public void testExampleB() throws DimensionException {
	/*	PLACE 0,0,NORTH
		LEFT
		REPORT
		Output: 0,0,WEST */

		testRobot.place(new Coordinate(0, 0), Direction.NORTH, table)
				 .rotateLeft();
		
		assertEquals(testRobot.report(), "[0,0] WEST");
	}
	
	@Test
	public void testExampleC() throws DimensionException {
	/*	PLACE 1,2,EAST
		MOVE
		MOVE
		LEFT
		MOVE
		REPORT
		Output: 3,3,NORTH */

		testRobot.place(new Coordinate(1, 2), Direction.EAST, table)
				 .moveForward()
				 .moveForward()
				 .rotateLeft()
				 .moveForward();
		
		assertEquals(testRobot.report(), "[3,3] NORTH");
	}
	
	/** Asserts that there will be a failure when moving forward. */
	private Robot placeBoundaryFailureExpected(Coordinate location, Direction direction, BoundaryCoordinates surface) {
		try {
			testRobot.place(location, direction, surface);
			fail("This should cause a boundary Failure");
		} catch (DimensionException e) {}
		
		return this.testRobot;
	}
	
	/** Asserts that there will be a failure when moving forward. */
	private Robot moveForwardBoundaryFailureExpected() {
		try {
			testRobot.moveForward();
			fail("This should cause a boundary Failure");
		} catch (DimensionException e) {}
		
		return this.testRobot;
	}
}
