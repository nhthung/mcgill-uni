package JavaCool303;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;

/*------------------------------------------------------------------------------
	Cool303Button.java
--------------------------------------------------------------------------------*/

/**
Button component. Extends Cool303Component.

@author		Laetitia Fesselier
@since		April, 9th 2018
@version	1.0
*/

public class Cool303Button extends Cool303Component {
	
	/*------------------------------------------------------------------------------
		Cool303Button
	--------------------------------------------------------------------------------*/

	/**
 	Constructor. Creates button with fixed size and custom label. <p>
 	Defines action to print label to console when button is clicked.
	
	@param		text Button label.
	*/
	public Cool303Button(String text) {
		component = new JButton(text);
		component.setPreferredSize(new Dimension(56, 40));
		
		((AbstractButton) component).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(((AbstractButton) component).getText());
			}
		});
	}

	/*------------------------------------------------------------------------------
		paint
	--------------------------------------------------------------------------------*/

	/**
	 Set button background, label, border to theme associated colors and border type.
	 
	 @param 	theme Cool303Theme
	 */
	@Override
	protected void paint(Cool303Theme theme) {
		component.setBackground(theme.getSecondaryBgColor());
		component.setBorder(theme.getBorder());
		component.setForeground(theme.getForegroundColor());
		component.setBackground(theme.getPrimaryBgColor());
		component.setFont(theme.getTextStyle());
	}
}
