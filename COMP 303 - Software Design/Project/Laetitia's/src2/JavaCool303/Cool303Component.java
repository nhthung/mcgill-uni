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
	
	/*------------------------------------------------------------------------------
		paint
	--------------------------------------------------------------------------------*/
	
	/**
	 Apply the them on components
	 */
	protected abstract void paint(Cool303Theme theme);
}
