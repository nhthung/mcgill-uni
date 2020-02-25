package simulator;

/*------------------------------------------------------------------------------
	SImmovable
--------------------------------------------------------------------------------*/

/**
Class for Immovable items. Implements Item.
@author		Laetitia Fesselier
@since		April, 9th 2018
@version	1.0
*/
public class Immovable implements Item {
	protected String name;
	protected char token;
	protected int x, y, xPrev, yPrev;
	protected World world;
	
	Immovable(String name, char token, int x, int y) {
		this.name = name;
		this.token = token;
		this.x = x;
		this.y = y;
	}
	
	public String getName() {
		return name;
	}
	
	public char getToken() {
		return token;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getXPrev() {
		return xPrev;
	}
	
	public int getYPrev() {
		return yPrev;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
}