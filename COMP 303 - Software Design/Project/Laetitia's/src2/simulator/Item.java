package simulator;

/*------------------------------------------------------------------------------
	Item
--------------------------------------------------------------------------------*/

/**
Interface for items.

@author		Laetitia Fesselier
@since		April, 9th 2018
@version	1.0
*/
public interface Item {
	void setWorld(World world);
	
	String getName();
	char getToken();
	
	int getX();
	int getY();

	int getXPrev();
	int getYPrev();
}