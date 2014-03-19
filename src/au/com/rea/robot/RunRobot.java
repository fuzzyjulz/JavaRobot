package au.com.rea.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import au.com.rea.robot.Controller.CommandController;
import au.com.rea.robot.entities.Coordinate;
import au.com.rea.robot.entities.Table;
import au.com.rea.robot.exceptions.CommandException;
import au.com.rea.robot.exceptions.DimensionException;

/** The main class for executing the application */
public class RunRobot {
	private static final String PROMPT = ">";

	public static void main(String[] args) {
		Table table;

		try {
			table = new Table(new Coordinate(0, 0), new Coordinate(5, 5));
		} catch (DimensionException e) {
			System.out.println(e.getMessage());
			return;
		}
		
		CommandController controller = new CommandController(table);
		
		while (!controller.isCommandsFinished()) {
			System.out.print(PROMPT);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String commandStr;
			
			try {
				commandStr = reader.readLine();
				if (commandStr != null) {
					controller.interpretCommand(commandStr);
				}
			} catch (IOException e) {
				System.out.println("IO Error:");
				e.printStackTrace();
			} catch (CommandException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
