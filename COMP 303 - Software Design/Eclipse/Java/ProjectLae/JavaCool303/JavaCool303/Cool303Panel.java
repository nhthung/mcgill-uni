package JavaCool303;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Cool303Panel extends JPanel implements Cool303Container {

	private static final long serialVersionUID = 1L;
	protected ArrayList<Component> children = new ArrayList<Component>();
	
	public Cool303Panel() {
	}
	
	public ArrayList<Component> getChildren() {
		// clone everything to protect value
		return children;
	}
	
	// Si on change le theme -> recolorer
	public Component add(Component comp) {
		children.add(comp);
		return super.add(comp);
	}
	
	public Component add(String name, Component comp) {
		children.add(comp);
		return super.add(name, comp);
	}
	
	public Component add(Component comp, int index) {
		children.add(comp);
		return super.add(comp, index);
	}
	
	public void remove(int index) {
		Component c = getComponent(index);
		children.remove(c);
		super.remove(index);
	}
	
	public void remove(Component comp) {
		children.remove(comp);
		super.remove(comp);
	}
	
	public void removeAll() {
		children.clear();
		super.removeAll();
	}
}
