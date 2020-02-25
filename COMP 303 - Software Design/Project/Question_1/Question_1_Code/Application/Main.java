// File name: Main.java

package Application;

import JavaCool303.*;

/**
 * Application with 20 integer labeled buttons which print out their respective label to the console when clicked.
 *
 * @author Le Nhat Hung
 * @version 1.0
 * @since April 9 2018
 */

public class Main {
    public static void main(String[] args) {
        // Creating new frame.
        Cool303Frame frame = new Cool303Frame("Big treats for good dogs!");

        // Setting root's theme to Pastel.
        Cool303Root root = new Cool303Root(new Pastel());

        // Creating new container.
        Cool303Container container = new Cool303Container("The water rises Mr Hot Dog Lady");

        // Enabling container's background color.
        container.enableBgColor(true);

        // Adding 20 Cool303Buttons to container.
        for ( int i = 1; i <= 20; i++ )
            container.add(new Cool303Button(Integer.toString(i)));

        // Adding container to root.
        root.add(container);

        // Adding root to frame.
        frame.add(root);

        // Making frame visible.
        frame.setVisible(true);
    }
}