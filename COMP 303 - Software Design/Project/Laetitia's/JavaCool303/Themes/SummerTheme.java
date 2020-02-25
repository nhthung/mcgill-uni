package JavaCool303.Themes;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import javax.swing.border.Border;
import JavaCool303.Cool303Theme;

/*------------------------------------------------------------------------------
	SummerTheme.java
--------------------------------------------------------------------------------*/

/**
Summer theme class.

@author		Laetitia Fesselier
@since		April, 9th 2018
@version	1.0
*/

public class SummerTheme implements Cool303Theme {
	final Random random = new Random(System.currentTimeMillis());
	Color backgroundColor, foregroundColor, primaryBgColor, secondaryBgColor;

	/*------------------------------------------------------------------------------
		SummerTheme
	--------------------------------------------------------------------------------*/
	
	/**
	 Constructor. Makes random background color and text grey.
	 */
	public SummerTheme() {
		backgroundColor = generateColor();
		foregroundColor = Color.DARK_GRAY;
	}
	
	/*------------------------------------------------------------------------------
		generateColor
	--------------------------------------------------------------------------------*/
	
	/**
	 Random color generator.
	 
	 @return 	Random color.
	 */
	private Color generateColor() {
		float h = random.nextFloat();
        float s = random.nextFloat();
        float b = 0.8f + ((1f - 0.8f) * random.nextFloat());
        Color c = Color.getHSBColor(h, s, b);
        return c;
    }

	/*------------------------------------------------------------------------------
		getBorder
	--------------------------------------------------------------------------------*/
	
	/**
	 Border accessor. Returns null because the Summer theme has no round borders.
	 
	 @return	null
	 */
	public Border getBorder() {
		return null;
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
		return foregroundColor;
	}

	/*------------------------------------------------------------------------------
		getTextStyle
	--------------------------------------------------------------------------------*/
	
	/**
	 Button text style accessor.
	 Null because Summer has default text style.
	 
	 @return null
	 */
	public Font getTextStyle() {
		return null;
	}
}