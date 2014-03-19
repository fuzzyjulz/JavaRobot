package au.com.rea.robot.Controller;

import au.com.rea.robot.entities.Coordinate;
import au.com.rea.robot.entities.Robot;
import au.com.rea.robot.entities.Table;
import au.com.rea.robot.enums.Direction;
import au.com.rea.robot.exceptions.CommandException;
import au.com.rea.robot.exceptions.DimensionException;

/** Performs actions based on the commands given for the scenario of a 
 *   robot on a table*/
public class CommandController {
	private String COMMAND_PLACE = "PLACE";
	private String COMMAND_LEFT = "LEFT";
	private String COMMAND_RIGHT = "RIGHT";
	private String COMMAND_MOVE = "MOVE";
	private String COMMAND_REPORT = "REPORT";
	private String COMMAND_EXIT = "EXIT";
	
	Robot robot = null;
	Table table;
	boolean commandsFinished = false;
	
	public CommandController(Table table) {
		this.table = table;
	}
	
	/** Interprets the command and executes the action on the robot. */
	public void interpretCommand(String commandStr) throws CommandException {
		String[] commandSection = commandStr.split(" ", 2);
				
		String command = commandSection[0];
		
		try {
			if (COMMAND_PLACE.equalsIgnoreCase(command)) {
				if (commandSection.length != 2) {
					throw new CommandException("Place command requires 3 parameters(eg. PLACE 1,2,north)");
				}
				
				place(commandSection[1]);
			} else if (COMMAND_EXIT.equalsIgnoreCase(command)) {
				this.commandsFinished = true;
				// this will now signal that the application should finish
			} else {
				if (robot == null) {
					throw new CommandException("Cannot process any commands until the robot " +
							"has been PLACEd.");
				}
				
				if (COMMAND_LEFT.equalsIgnoreCase(command)) {
					robot.rotateLeft();
				} else if (COMMAND_RIGHT.equalsIgnoreCase(command)) {
					robot.rotateRight();
				} else if (COMMAND_MOVE.equalsIgnoreCase(command)) {
					robot.moveForward();
				} else if (COMMAND_REPORT.equalsIgnoreCase(command)) {
					System.out.println(robot.report());
				} else {
					throw new CommandException("Command not recognised.");
				}
			}
		} catch (DimensionException e) {
			throw new CommandException(command + " command failed: "+e.getMessage(), e);
		}
	}

	/** Runs the more complex PLACE command on the robot */
	private void place(String parameterStr) 
			throws CommandException, DimensionException {
		String[] parameters = parameterStr.split(",");
		if (parameters.length != 3) {
			throw new CommandException("Exactly 3 parameters are required");
		}
		
		int x = clenseIntPaameter(parameters[0]);
		int y = clenseIntPaameter(parameters[1]);
		Direction direction = Direction.getForName(clensePaameter(parameters[2]));
		
		if (direction == null) {
			throw new CommandException("Direction was not a valid direction");
		}
		
		if (robot == null) {
			robot = new Robot(new Coordinate(x, y), direction, table);
		}
		
		robot.place(new Coordinate(x, y), direction, table);
	}

	/** Returns a clean Integer or throws a CommandException */
	private int clenseIntPaameter(String string) throws CommandException {
		string = clensePaameter(string);
		
		if (string.equals(""))
			throw new CommandException("parameter must be set");
		
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException e) {
			throw new CommandException("parameter is not numeric");
		}
	}

	/** Perform clensing on a string, this could potentially be expanded */
	private String clensePaameter(String string) {
		return string.trim();
	}
	
	/** Returns true if the command controller has completed. */
	public boolean isCommandsFinished() {
		return commandsFinished;
	}
	
	/** Returns the internal Robot object, used for testing.*/
	public Robot getRobot() {
		return robot;
	}
}
