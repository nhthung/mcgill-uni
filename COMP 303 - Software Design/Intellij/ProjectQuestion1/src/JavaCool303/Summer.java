// File name: Summer.java

package JavaCool303;

import java.awt.Color;

/**
 * Defines summer theme. <p>
 * To create a new theme, make a new class extending Cool303Theme and set colors as desired.
 *
 * @author Le Nhat Hung
 * @version 1.0
 * @since April 9 2018
 */

public class Summer extends Cool303Theme {

    /**
     * Constructor. Set all the colors, border thickness and corner radius here. The higher the thicker and rounder.
     */
    public Summer() {
        setBackgroundColor(new Color(255, 224, 181));
        setBorderColor(new Color(255, 255, 255));
        setButtonColor(new Color(67, 146, 241));

        setBorderThickness(4);
        setCornerRadius(3);
    }
}
