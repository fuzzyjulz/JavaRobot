package au.com.rea.robot.exceptions;

/** Represents a command related exception */
public class CommandException extends Exception {
	private static final long serialVersionUID = -2427105145311762811L;

	public CommandException(String message) {
		super(message);
	}

	public CommandException(String string, DimensionException e) {
		super(string, e);
	}

}
