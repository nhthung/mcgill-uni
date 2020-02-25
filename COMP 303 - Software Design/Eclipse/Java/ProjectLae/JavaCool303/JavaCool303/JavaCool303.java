package JavaCool303;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import JavaCool303.Themes.PastelTheme;

//  If the user leaves the theme empty (NULL), your library must throw an error.

public class JavaCool303 {
	private static JFrame window;
	private static Cool303Root root;
	private static Cool303Container container;
	private static Cool303Button btn;
	private static Cool303Theme theme;
		
	public static void main(String[] args){
		window = new JFrame("My amazing buttons");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(398, 400);
		window.setLayout(new FlowLayout());
		
		container = new Cool303Container("My amazing Container");
		container.setLayout(new FlowLayout(FlowLayout.LEFT));
		container.setPreferredSize(new Dimension(372, 352)); 
		
		for(int i = 1; i <= 20; i++) {
			btn = new Cool303Button(""+i);
			btn.setPreferredSize(new Dimension(56, 40));
			btn.setOpaque(true);
			btn.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  Cool303Button btn = (Cool303Button)e.getSource();
		    	  System.out.println(btn.getText());
		      }
		    }); 
				
			container.add(btn);
		}
		
		theme = new PastelTheme();
		root = new Cool303Root(theme);
		root.add(container);
		
		window.add(root);
		window.setVisible(true);
	}
}