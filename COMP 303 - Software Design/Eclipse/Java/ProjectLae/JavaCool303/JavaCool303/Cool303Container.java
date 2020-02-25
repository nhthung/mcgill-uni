package JavaCool303;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Cool303Container extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Component> children = new ArrayList<Component>();
	private JLabel title;
	private JPanel panel;
	
	public Cool303Container() {
		super(new FlowLayout());
	}
	
	public Cool303Container(String title) {
		super(new BorderLayout());
		this.title = new JLabel(title);
		this.title.setFont(this.title.getFont().deriveFont(Font.BOLD));
		this.add(this.title,  BorderLayout.NORTH);
		//super.addImpl(this.title, BorderLayout.NORTH, 0);
		
		panel = new JPanel();
		this.add(panel, BorderLayout.NORTH);
	}
	
	public Cool303Container(String title, Color color) {
		this(title);
		// comment gérer le bg optionnel avec theme???
		this.setBackground(color);
	}
	
	public ArrayList<Component> getChildren() {
		// clone everything to protect value
		return children;
	}
	
	// Si on change le theme -> recolorer			
	protected void addImpl(Component comp, Object constraints, int index) {
		children.add(comp);
		super.addImpl(comp, constraints, index);
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
