package JavaCool303;

import java.awt.Dimension;
import java.util.ArrayList;

/*------------------------------------------------------------------------------
Cool303Composite.java
--------------------------------------------------------------------------------*/

/**
Composite abstract class. Extended by Cool303Container, Cool303Root.

@author		Laetitia Fesselier
@since		April, 9th 2018
@version	1.0
*/

public abstract class Cool303Composite extends Cool303Component {
	protected Dimension size;
	protected ArrayList<Cool303Component> componentsList;
	
	/*------------------------------------------------------------------------------
		getComponents
	--------------------------------------------------------------------------------*/
	
	/**
	 Return reference to Container's children array list.
	 
	 @return 	componentsList 
	 */
	public ArrayList<Cool303Component> getComponents() {
		return componentsList;
	}
	
	/*------------------------------------------------------------------------------
		setSize
	--------------------------------------------------------------------------------*/
	
	/**
	 Mutator for Container's size.
	 
	 @param 	size Dimension
	 */
	public void setSize(Dimension size) {
		this.size = size;
		component.setPreferredSize(size);
	}
	
	/*------------------------------------------------------------------------------
		getSize
	--------------------------------------------------------------------------------*/
	
	/**
	 Accessor for Container's size.
	 
	 @return 	size Type dimension.
	 */
	public Dimension getSize() {
		return size;
	}
}
