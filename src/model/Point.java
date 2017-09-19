package model;

public class Point {
	public Point( int row , int col ) {
		this.row = row;
		this.col = col;
	}

    // Get direction to an other point which is assumed to
    // be at perpendicular offset from this object
    public Point.Direction getDirection( Point target ) {
        if      ( target.row < row ) return Direction.UP;
        else if ( target.row > row ) return Direction.DOWN;
        else if ( target.col < col ) return Direction.LEFT;
        else if ( target.col > col ) return Direction.RIGHT;
        else
        	return Direction.ERROR;
    }

    // move this point one step
    public void move( Direction d ) {
        switch ( d ) {
        	case UP:    row--; break;
        	case RIGHT: col++; break;
        	case DOWN:  row++; break;
        	case LEFT:  col--; break;
        }
    }
    
    public enum Direction { UP, RIGHT, DOWN, LEFT, ERROR }
    
    public int row = 0, col = 0;
}
