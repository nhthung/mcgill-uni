package JavaCool303;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.Border;

/*------------------------------------------------------------------------------
	Cool303Theme.java
--------------------------------------------------------------------------------*/

/**
Theme interface to be implemented by any Theme class. <p>
Implemented by SummerTheme and PastelTheme.

@author		Laetitia Fesselier
@since		April, 9th 2018
@version	1.0
*/

public interface Cool303Theme {	
	// Border color
	Border getBorder();
	
	// Button color
	Color getPrimaryBgColor();
	
	// Container background color
	Color getSecondaryBgColor();
	
	// Button text color
	Color getForegroundColor();
	
	// Button text font
	Font getTextStyle();
}