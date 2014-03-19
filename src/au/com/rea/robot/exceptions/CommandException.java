package au.com.rea.robot.exceptions;

public class CommandException extends Exception {
	private static final long serialVersionUID = -2427105145311762811L;

	public CommandException(String message) {
		super(message);
	}

	public CommandException(String string, DimensionException e) {
		super(string, e);
	}

}
