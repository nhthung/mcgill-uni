// File name: Cool303Theme.java

package JavaCool303;

import java.awt.Color;
import javax.swing.border.Border;

/**
 * Abstract class to be extended by any theme file.
 *
 * @author Le Nhat Hung
 * @version 1.0
 * @since April 9 2018
 */

public abstract class Cool303Theme {
    private Color backgroundColor, borderColor, buttonColor;
    private int borderThickness, cornerRadius;

    Border getBorder() { return new BubbleBorder(borderColor, borderThickness, cornerRadius); }
    Color getBackgroundColor() { return backgroundColor; }
    Color getBorderColor() { return borderColor; }
    Color getButtonColor() { return buttonColor; }

    public void setBackgroundColor(Color backgroundColor) { this.backgroundColor = backgroundColor; }
    public void setBorderColor(Color borderColor) { this.borderColor = borderColor; }
    public void setButtonColor(Color buttonColor) { this.buttonColor = buttonColor; }

    public void setBorderThickness(int borderThickness) {
        if (borderThickness < 0)
            throw new IllegalArgumentException("Thickness must be non negative.");

        this.borderThickness = borderThickness;
    }

    public void setCornerRadius(int cornerRadius) throws IllegalArgumentException {
        if (cornerRadius < 0)
            throw new IllegalArgumentException("Radius must be non negative.");

        this.cornerRadius = cornerRadius;
    }
}