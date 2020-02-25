package JavaCool303;

import javax.swing.JComponent;

/*------------------------------------------------------------------------------
	Cool303Component.java
--------------------------------------------------------------------------------*/

/**
Component abstract class. Extended by Cool303Container, Cool303Root and Cool303Button.
Cannot be an interface because we want paint protected

@author		Laetitia Fesselier
@since		April, 9th 2018
@version	1.0
*/

public abstract class Cool303Component {
	protected JComponent component;
	protected Cool303Root root;
	
	/*------------------------------------------------------------------------------
		getComponent
	--------------------------------------------------------------------------------*/
	
	/**
	 Return reference to component wrapped by each JavaCool303 Components.
	 
	 @return	component
	 */
	protected JComponent getComponent() {
		return component;
	}
	
	protected abstract void paint(Cool303Theme theme);
	
	/*------------------------------------------------------------------------------
		setRoot
	--------------------------------------------------------------------------------*/
	
	/**
	 Return reference to Cool303Root parent of Cool303Containers. <p>
	 Used to call Cool303Root.notify(Cool303Component c).
	 
	 @param		root Cool303Root
	 */
	protected void setRoot(Cool303Root root) {
		this.root = root;
	}
}
