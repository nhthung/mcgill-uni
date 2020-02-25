package JavaCool303.Themes;

import java.awt.Color;
import java.util.Random;
import javax.swing.border.Border;
import JavaCool303.Cool303Theme;

public class PastelTheme implements Cool303Theme {
	final Random random = new Random(System.currentTimeMillis());
	Border border;
	
	public PastelTheme() {
		border = new ThreeDimensionalBorder(generateColor(), 128, 4);
	}

	public Color generateColor() {
		double amount = 0.70;
		
	    int red = (int) ((random.nextInt(256) * (1 - amount) / 255 + amount) * 255);
	    int green = (int) ((random.nextInt(256) * (1 - amount) / 255 + amount) * 255);
	    int blue = (int) ((random.nextInt(256) * (1 - amount) / 255 + amount) * 255);
	    return new Color(red, green, blue);
	}

	public Border getBorder() {
		return border;
	}

	public Color getColor() {
		return generateColor();
	}

	public Color getBackgroundColor() {
		return generateColor();
	}
}