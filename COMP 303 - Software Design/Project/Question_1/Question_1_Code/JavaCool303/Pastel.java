// File name: Pastel.java

package JavaCool303;

import java.awt.Color;

/**
 * Constructor. Defines pastel theme with pastelly colors and appropriate button roundness and thickness. <p>
 * To create a new theme, make a new class extending Cool303Theme and set colors as desired.
 *
 * @author Le Nhat Hung
 * @version 1.0
 * @since April 9 2018
 */

public class Pastel extends Cool303Theme {

    /**
     * Constructor. Set all the colors, border thickness and corner radius here. The higher the thicker and rounder.
     */
    public Pastel() {
        setBackgroundColor(new Color(218, 241, 255));
        setBorderColor(new Color(255, 255, 255));
        setButtonColor(new Color(255, 156, 238));

        setBorderThickness(6);
        setCornerRadius(15);
    }
}