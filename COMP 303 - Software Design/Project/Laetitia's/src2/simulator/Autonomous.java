package simulator;

import java.awt.Color;
import java.util.ArrayList;

/*------------------------------------------------------------------------------
	Autonomous
--------------------------------------------------------------------------------*/

/**
Class for Autonomous items. Extends Movable.
@author		Laetitia Fesselier
@since		April, 9th 2018
@version	1.0
*/
public class Autonomous extends Movable { 
	ArrayList<Direction> directions;
	Direction direction;
	int xNext, yNext;
	Color color;
	
	Autonomous(String name, char token, int x, int y) {
		super(name, token, x, y);
		resetDirections();
		color = generateColor();
	}
	 
	private Color generateColor() {
		double amount = 0.70;
			
	    int red = (int) ((Math.random()*256 * (1 - amount) / 255 + amount) * 255);
	    int green = (int) ((Math.random()*256 * (1 - amount) / 255 + amount) * 255);
	    int blue = (int) ((Math.random()*256 * (1 - amount) / 255 + amount) * 255);
	    return new Color(red, green, blue);
	}
	
	public Color getColor() {
		return color;
	}
	
	/*------------------------------------------------------------------------------
		resetDirections
	--------------------------------------------------------------------------------*/
	
	/**
	Reset the number of possible directions for random movements.
	
	@author		Laetitia Fesselier
	*/
	public void resetDirections() {
		directions = new ArrayList<Direction>();
		directions.add(Direction.UP);
		directions.add(Direction.RIGHT);
		directions.add(Direction.DOWN);
		directions.add(Direction.LEFT);
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public int getXNext() {
		return xNext;
	}
	
	public int getYNext() {
		return yNext;
	}
	
	private void setDirection() {
		int index = (int)(Math.random() * directions.size());
		direction = directions.remove(index);
	}
	
	/*------------------------------------------------------------------------------
		step
	--------------------------------------------------------------------------------*/
	
	/**
	Choose a random movement. Set the new position in newX and newY.
	
	@author		Laetitia Fesselier
	*/
	public void step() {
		setDirection();
		setNewPosition();
	}
	
	private void setNewPosition() {
		xNext = x;
		yNext = y;
		
		switch (direction) {
		case UP:
			yNext--;
			break;
		
		case RIGHT:
			xNext++;
			break;
		
		case DOWN:
			yNext++;
			break;
		
		case LEFT:
			xNext--;	
		}
	}
}
