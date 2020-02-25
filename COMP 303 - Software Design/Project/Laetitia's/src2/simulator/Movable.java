package simulator;

/*------------------------------------------------------------------------------
Movable
--------------------------------------------------------------------------------*/

/**
Class for Movable items. Extends Immovable.
@author		Laetitia Fesselier
@since		April, 9th 2018
@version	1.0
*/
public class Movable extends Immovable {	
	public enum Direction {
	    UP, RIGHT, DOWN, LEFT;
	}
	
	/*------------------------------------------------------------------------------
		Movable
	--------------------------------------------------------------------------------*/
	
	/**
	Constructor.
	
	@param		String name, char token, int x, int y
	@author		Laetitia Fesselier
	*/
	Movable(String name, char token, int x, int y) {
		super(name, token, x, y);
	}
	
	/*------------------------------------------------------------------------------
		move
	--------------------------------------------------------------------------------*/
	
	/**
	Called when the item is bumped.
	
	@param		Direction dir
	@author		Laetitia Fesselier
	*/
	public void move(Direction dir) {
		xPrev = x;
		yPrev = y;
		
		switch (dir) {
			case UP:
				y--;
				break;
			
			case RIGHT:
				x++;
				break;
			
			case DOWN:
				y++;
				break;
			
			case LEFT:
				x--;
		}
		
		world.update(this);
	}
}