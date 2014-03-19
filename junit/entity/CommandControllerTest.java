package entity;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import au.com.rea.robot.Controller.CommandController;
import au.com.rea.robot.entities.Coordinate;
import au.com.rea.robot.entities.Table;
import au.com.rea.robot.enums.Direction;
import au.com.rea.robot.exceptions.CommandException;
import au.com.rea.robot.exceptions.DimensionException;

@RunWith(JUnit4.class)
public class CommandControllerTest  {
	Table table;
	CommandController controller;
	
	@Before
	public void initialise() throws DimensionException {
		this.table = new Table(new Coordinate(0, 0), new Coordinate(5, 5));
		controller = new CommandController(this.table);
	}
	
	@Test
	public void testInterpretCommand_NoPlace() throws CommandException {
		assertCommandFailure("LEFT");
		assertCommandFailure("RIGHT");
		assertCommandFailure("MOVE");
		assertCommandFailure("REPORT");
		
		assertFalse(controller.isCommandsFinished());
		controller.interpretCommand("EXIT");
		assertTrue(controller.isCommandsFinished());
	}

	@Test
	public void testInterpretCommand_Place() throws CommandException {
		controller.interpretCommand("Place 1,2, North");
		controller.interpretCommand("Right");
		controller.interpretCommand("Move");
		controller.interpretCommand("Report");//because this outputs to stdout, we don't
												// know if this has worked, but we can look
												// at the Robot object
		
		assertEquals(controller.getRobot().getLocation(), new Coordinate(2, 2));
		assertEquals(controller.getRobot().getFacingDirection(), Direction.EAST);
		
		assertFalse(controller.isCommandsFinished());
		controller.interpretCommand("EXIT");
		assertTrue(controller.isCommandsFinished());
	}
	
	private void assertCommandFailure(String command) {
		try {
			controller.interpretCommand(command);
			fail();
		} catch (CommandException e) {}
	}
}
