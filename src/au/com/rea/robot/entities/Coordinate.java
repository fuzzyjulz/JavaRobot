package au.com.rea.robot.entities;

/** A coordinate, this contains an x and a y component. Note that this
 *   object is immutable. */
public class Coordinate {
	public final int x, y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/** Adds the given coordinates to this coordinate, and returns the result 
	 *   as a new object. */
	public Coordinate add(Coordinate coordinate) {
		return new Coordinate(this.x + coordinate.x, this.y + coordinate.y);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof Coordinate)) {
			return false;
		}
		
		Coordinate otherCoord = (Coordinate) obj;
			
		return  this.x == otherCoord.x &&
				this.y == otherCoord.y;
	}
	
	public String toString() {
		return "["+this.x+","+this.y+"]";
	}
}
