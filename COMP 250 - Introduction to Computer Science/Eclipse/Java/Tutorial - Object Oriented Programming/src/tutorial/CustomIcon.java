package tutorial;

import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;

public class CustomIcon implements Icon {
	private int size;
	
	public CustomIcon(int size) {
		this.size = size;
	}
	
	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		g.drawOval(x, y, 200, 200);
		g.fillOval(x + 55, y + 55, 200, 200);
	}

}
