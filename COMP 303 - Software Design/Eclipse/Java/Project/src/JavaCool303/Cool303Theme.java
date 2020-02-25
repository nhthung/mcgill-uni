package JavaCool303;

import java.awt.Color;
import javax.swing.border.Border;

public abstract class Cool303Theme {
    private Color backgroundColor, borderColor, buttonColor;

    private int borderThickness, cornerRadius;

    Border getBorder() { return new BubbleBorder(borderColor, borderThickness, cornerRadius); }

    Color getBackgroundColor() { return backgroundColor; }

    Color getBorderColor() { return borderColor; }

    Color getButtonColor() { return buttonColor; }

    void setBackgroundColor(Color backgroundColor) { this.backgroundColor = backgroundColor; }

    void setBorderColor(Color borderColor) { this.borderColor = borderColor; }

    void setButtonColor(Color buttonColor) { this.buttonColor = buttonColor; }

    void setBorderThickness(int borderThickness) { this.borderThickness = borderThickness; }

    void setCornerRadius(int cornerRadius) { this.cornerRadius = cornerRadius; }
}