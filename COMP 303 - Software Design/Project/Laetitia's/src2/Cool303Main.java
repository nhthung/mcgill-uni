import java.awt.*;

import JavaCool303.*;
import JavaCool303.Themes.*;

/*------------------------------------------------------------------------------
	Cool303Main.java
--------------------------------------------------------------------------------*/

/**
Application with 20 buttons.

@author		Laetitia Fesselier
@since		April, 9th 2018
@version	1.0
*/

public class Cool303Main {
	private static Cool303Window window;
	private static Cool303Root root;
	private static Cool303Container container;
	private static Cool303Button btn;
	private static Cool303Theme theme;
		
	public static void main(String[] args){
		window = new Cool303Window("My amazing buttons");
		container = new Cool303Container("My amazing Container", true, new Dimension(400, 400));
		for(int i = 1; i <= 20; i++) {
			btn = new Cool303Button(""+i);	
			container.add(btn);
		}
		theme = new PastelTheme();
		root = new Cool303Root(theme);
		root.add(container);
		window.add(root);
	}
}