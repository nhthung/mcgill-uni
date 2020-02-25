package JavaCool303;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;

/*------------------------------------------------------------------------------
		Cool303Root.java
--------------------------------------------------------------------------------*/

/**
Root Component. Take a theme and apply it to all Cool303Container/Cool303Component contained within it. 


@author		Laetitia Fesselier
@since		April, 9th 2018
@version	1.0
*/

public class Cool303Root extends Cool303Component {
	private Cool303Theme theme;
	private Dimension size;
	private ArrayList<Cool303Component> componentsList;
	
	/*------------------------------------------------------------------------------
		Cool303Root
	--------------------------------------------------------------------------------*/

	/**
	Constructor. Throws exception if the theme is not specified (null).

	@param		theme Cool303Theme 
	@throws		IllegalArgumentException
	@author		Laetitia Fesselier
	*/
	public Cool303Root(Cool303Theme theme) {
		component = new JPanel(new FlowLayout());
		if(theme == null) {
			throw new IllegalArgumentException("NULL");
		}
		
		this.theme = theme;
		componentsList = new ArrayList<Cool303Component>();
	}
	
	/*------------------------------------------------------------------------------
		add
	--------------------------------------------------------------------------------*/
	
	/**
	 Method used to add a Cool303Component into the Root.
	 
	 @param 	component
	 @author 	Laetitia Fesselier
	 */
	public void add(Cool303Component component) {
		componentsList.add(component);
		this.component.add(component.getComponent());
		component.paint(theme);
		
		if(component instanceof Cool303Container) {
			component.setRoot(this);

		}
	}
	
	/*------------------------------------------------------------------------------
		notify
	--------------------------------------------------------------------------------*/
	
	/**
	 Called by Cool303Container.add() to notify the Root when a new component needs to be paint.
	 
	 @param 	component
	 @author	Laetitia Fesselier
	 */
	protected void notify(Cool303Component component) {
		component.paint(theme);
	}
	
	
	/*------------------------------------------------------------------------------
		setSize
	--------------------------------------------------------------------------------*/
	
	/**
	 Set Root's size.
	 
	 @param 	size
	 @author	Laetitia Fesselier
	 */
	public void setSize(Dimension size) {
		this.size = size;
		component.setPreferredSize(size);
	}
	
	/*------------------------------------------------------------------------------
		getSize
	--------------------------------------------------------------------------------*/
	
	/**
	 Accessor of Root's size.
	 
	 @return 	size Type Dimension.
	 @author	Laetitia Fesselier
	 */
	public Dimension getSize() {
		return size;
	}
	
	/*------------------------------------------------------------------------------
		getComponents
	--------------------------------------------------------------------------------*/
	
	/**
	 Return reference to Root's children array list.
	 
	 @return 	componentsList 
	 */
	public ArrayList<Cool303Component> getComponents() {
		return componentsList;
	}

	/**
	 Parent abstract class method override. <p>
	 Iterates through every child Component and calls its paint().
	 
	 @param		theme Theme to apply to children.
	 @author	Laetitia Fesselier
	 */
	@Override
	protected void paint(Cool303Theme theme) {
		for (int i = 0; i < componentsList.size(); i++) {
			componentsList.get(i).paint(theme);
		}
	}
}