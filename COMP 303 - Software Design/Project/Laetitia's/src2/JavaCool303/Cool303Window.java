package JavaCool303;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;

/*------------------------------------------------------------------------------
	Cool303Window.java
--------------------------------------------------------------------------------*/

/**
JFrame wrapper.

@author		Laetitia Fesselier
@since		April, 9th 2018
@version	1.0
*/

public class Cool303Window {
	private JFrame component;

	/*------------------------------------------------------------------------------
		Cool303Window
	--------------------------------------------------------------------------------*/
	
	/**
	 Constructor. Instantiates JFrame, sets close operation, minimum size, flow layout and visibility.
	 */
	public Cool303Window() {
		component = new JFrame();
		component.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		component.setMinimumSize(new Dimension(500, 500));
		component.setLayout(new FlowLayout());
	}
	
	/*------------------------------------------------------------------------------
		Cool303Window
	--------------------------------------------------------------------------------*/
	
	/**
	 Constructor but with custom title.
	 
	 @param title String
	 */
	public Cool303Window(String title) {
		this();
		setTitle(title);
	}
	
	/*------------------------------------------------------------------------------
		setTitle
	--------------------------------------------------------------------------------*/
	
	/**
	 Set the Title
	 
	 @param title String 
	 */
	public void setTitle(String title) {
		component.setTitle(title);
	}
	
	/*------------------------------------------------------------------------------
		add
	--------------------------------------------------------------------------------*/
	
	/**
	 Adds Cool303Root to current class object.
	 
	 @param	root Cool303Root
	 */
	public void add(Cool303Root root) {
		component.add(root.getComponent());
		component.pack();
		component.setVisible(true);
	}
}
