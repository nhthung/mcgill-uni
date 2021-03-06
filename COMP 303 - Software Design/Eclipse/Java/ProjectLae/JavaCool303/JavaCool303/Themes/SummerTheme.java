package JavaCool303.Themes;

import java.awt.Color;
import java.util.Random;
import javax.swing.border.Border;
import JavaCool303.Cool303Theme;

public class SummerTheme implements Cool303Theme {
	final Random random = new Random(System.currentTimeMillis());
	
	public SummerTheme() {}
	
	private Color generateColor() {
		float h = random.nextFloat();
        float s = random.nextFloat();
        float b = 0.8f + ((1f - 0.8f) * random.nextFloat());
        Color c = Color.getHSBColor(h, s, b);
        return c;
    }

	public Border getBorder() {
		return null;
	}

	public Color getColor() {
		return generateColor();
	}

	public Color getBackgroundColor() {
		return generateColor();
	}
}
