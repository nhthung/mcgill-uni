package JavaCool303.Themes;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import javax.swing.border.Border;

import JavaCool303.Cool303Theme;
import JavaCool303.Themes.BubbleBorder;

/*------------------------------------------------------------------------------
	PastelTheme.java
--------------------------------------------------------------------------------*/

/**
Pastel theme class.

@author		Laetitia Fesselier
@since		April, 9th 2018
@version	1.0
*/

public class PastelTheme implements Cool303Theme {
	private final Random RANDOM = new Random(System.currentTimeMillis());
	private Border border;
	private Color foreground;
	
	/*------------------------------------------------------------------------------
		PastelTheme
	--------------------------------------------------------------------------------*/
	
	/**
	 Constructor. Makes random background color.
	 */
	public PastelTheme() {
		border = new BubbleBorder(Color.GRAY,1,16);
		foreground = Color.DARK_GRAY;
	}
	
	/*------------------------------------------------------------------------------
		generateColor
	--------------------------------------------------------------------------------*/
	
	/**
	 Random color generator.
	 
	 @return 	Random color.
	 */
	public Color generateColor() {
		double amount = 0.70;
		
	    int red = (int) ((RANDOM.nextInt(256) * (1 - amount) / 255 + amount) * 255);
	    int green = (int) ((RANDOM.nextInt(256) * (1 - amount) / 255 + amount) * 255);
	    int blue = (int) ((RANDOM.nextInt(256) * (1 - amount) / 255 + amount) * 255);
	    return new Color(red, green, blue);
	}
	
	/*------------------------------------------------------------------------------
		getBorder
	--------------------------------------------------------------------------------*/
	
	/**
	 Border accessor.
	 
	 @return	Border for buttons
	 */
	
	public Border getBorder() {
		return border;
	}

	/*------------------------------------------------------------------------------
		getPrimaryBgColor
	--------------------------------------------------------------------------------*/
	
	/**
	 Button background color accessor.
	 
	 @return	Color Random color for button background.
	 */
	public Color getPrimaryBgColor() {
		return generateColor();
	}
	
	/*------------------------------------------------------------------------------
		getSecondaryBgColor
	--------------------------------------------------------------------------------*/
	
	/**
	 Container background color accessor.
	 
	 @return	Color Random color for container background.
	 */
	public Color getSecondaryBgColor() {
		return generateColor();
	}
	
	/*------------------------------------------------------------------------------
		getForegroundColor
	--------------------------------------------------------------------------------*/
	
	/**
		 Button text color accessor.
	
		 @return	Color Random color for text.
	*/
	public Color getForegroundColor() {
		return foreground;
	}
	
	/*------------------------------------------------------------------------------
		getTextStyle
	--------------------------------------------------------------------------------*/
	
	/**
	 Button text style accessor.
	 
	 @return Font for buttons
	 */
	public Font getTextStyle() {
		return new Font("Arial", Font.BOLD, 20);
	}
}