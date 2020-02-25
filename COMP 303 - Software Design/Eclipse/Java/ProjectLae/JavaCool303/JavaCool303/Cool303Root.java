package JavaCool303;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.border.Border;

public class Cool303Root extends Cool303Container {
	private static final long serialVersionUID = 1L;
	
	Cool303Theme theme;
	Color color1;
	Color color2;
	Border border;
	
	public Cool303Root(Cool303Theme theme) {
		this.theme = theme;
		border = theme.getBorder();
	}
	
	// Si on change le theme -> recolorer
	// wrap paint methods
	/* Class Cool303Root is a container populated with Components and Containers. A user can implement more than one Cool303Root in their application.  
	 * The Root is fancy and follows the selected theme and applies the theme to all elements populated within it. Root does not have a title or any other text.  
	 * Root will auto size itself to the minimum size needed to display all the components if the user does not specify a size.  
	 * If the user of Root defines a size the class will attempt to follow that request if it is sufficient to display all the components.  
	 * If it is not big enough then the automatic sizing option is automatically initiated overriding the user’s specified size. */
	
	
	public Component add(Component comp) {
		comp.setBackground(theme.getColor());
		
		if(comp instanceof Cool303Container) {
			Cool303Container container = (Cool303Container)comp;
			ArrayList<Component> children = container.getChildren();
			for (int i = 0; i < children.size(); i++) {
				JComponent child = (JComponent)children.get(i);
				child.setBackground(theme.getColor());
				child.setBorder(border);
				child.setForeground(Color.DARK_GRAY);
				
				/*if(child instanceof Cool303Button) {
					Cool303Button btn = (Cool303Button) child;
				}*/
			}
		}
		
		return super.add(comp);
	}
	
	public Component add(String name, Component comp) {
		comp.setBackground(theme.getColor());
		return super.add(name, comp);
	}
	
	public Component add(Component comp, int index) {
		comp.setBackground(theme.getColor());
		return super.add(comp, index);
	}
	
	Cool303Theme getTheme() {
		return theme;
	}
}