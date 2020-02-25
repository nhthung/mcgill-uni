package JavaCool303;	
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/*------------------------------------------------------------------------------
		Cool303Container.java
--------------------------------------------------------------------------------*/

/**
Wrapper for a JPanel only holding Cool303Containers.

@author		Laetitia Fesselier
@since		April, 9th 2018
@version	1.0
*/

public class Cool303Container extends Cool303Composite {
	private TitledBorder title;
	private boolean enableBg;
	private Cool303Root root;
	
	/*------------------------------------------------------------------------------
		Cool303Container
	--------------------------------------------------------------------------------*/
	
	/**
	 Constructor. Makes container without title, background nor specific size.
	 */
	public Cool303Container() {
		this(null, false, null);
	}
	
	/*------------------------------------------------------------------------------
		Cool303Container
	--------------------------------------------------------------------------------*/
	
	/**
	 Constructor with custom title, choice to enable background and custom size.
	 
	 @param 	title
	 @param 	enableBg
	 @param 	size
	 */
	public Cool303Container(String title, boolean enableBg, Dimension size) {
		component = new JPanel(new FlowLayout());	
		componentsList = new ArrayList<Cool303Component>();
		setTitle(title);
		enableBg(enableBg);
		setSize(size);
	}
	
	/*------------------------------------------------------------------------------
		isEnableBg
	--------------------------------------------------------------------------------*/
	
	/**
	 Accessor for background status (enable or disabled).
	 
	 @return 	enableBg Background status (enabled or not)
	 */
	public boolean isEnabledBg() {
		return enableBg;
	}
	
	/*------------------------------------------------------------------------------
		enableBg
	--------------------------------------------------------------------------------*/
	
	/**
	 Set preferred state for background (disabled or enabled)
	 
	 @param 	state boolean
	 */
	public void enableBg(boolean state) {
		enableBg = state;
	}
	
	/*------------------------------------------------------------------------------
		setTitle
	--------------------------------------------------------------------------------*/

	/**
	 Set the Container's title.
	 
	 @param 	title String
	 */
	public void setTitle(String title) {
		if(title != null && !title.isEmpty()) {
			this.title = new TitledBorder(title);
			component.setBorder(this.title);
		}
	}
	
	/*------------------------------------------------------------------------------
		add
	--------------------------------------------------------------------------------*/
	
	/**
	 Add a Cool303Component into the current object.
	 
	 @param 	component Cool303Component
	 */
	public void add(Cool303Component component) {
		componentsList.add(component);
		this.component.add(component.getComponent());
		
		if(root != null) {
			root.notify(this);
		}
	}
		
	/*------------------------------------------------------------------------------
		paint
	--------------------------------------------------------------------------------*/
	
	/**
	 Iterates through children and call their paint().
	 */
	@Override
	protected void paint(Cool303Theme theme) {
		if(isEnabledBg()) {
			component.setBackground(theme.getSecondaryBgColor());
		}
		
		for (int i = 0; i < componentsList.size(); i++) {
			componentsList.get(i).paint(theme);
		}
	}
	
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