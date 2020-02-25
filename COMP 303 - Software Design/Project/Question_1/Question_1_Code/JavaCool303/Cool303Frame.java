// File name: Cool303Frame.java

package JavaCool303;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 * Wraps Swing JFrame. In essence, a JFrame in flow layout only able to hold Cool303Roots.
 *
 * @author Le Nhat Hung
 * @version 1.0
 * @since April 9 2018
 */

public class Cool303Frame {
    JFrame frame;
    ArrayList<Cool303Root> roots = new ArrayList<>();

    /**
     * Constructor. Gives JFrame flow layout and sets to exit on close.
     */
    public Cool303Frame() {
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Constructor. A title can be specified.
     *
     * @param title
     */
    public Cool303Frame(String title) {
        this();

        frame.setTitle(title);
    }

    /**
     * Limit addable components to only Cool303Root objects. <p>
     * Also adds to roots array list.
     *
     * @param root
     */
    public void add(Cool303Root root) {
        frame.add(root.getComponent());
        roots.add(root);

        frame.pack();
        frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
    }

    /**
     * Removes a Cool303Root from JFrame and roots array list.
     *
     * @param i
     */
    public void remove(int i) {
        if (i < 0 || i >= roots.size())
            throw new IllegalArgumentException("Index out of bounds.");

        frame.remove(roots.get(i).getComponent());
        roots.remove(i);

        frame.pack();
    }

    /**
     * Remove all Cool303Roots from JFrame and roots array list.
     */
    public void removeAll() {
        frame.removeAll();
        roots.clear();
    }

    /**
     * Sets JFrame visible.
     *
     * @param bool
     */
    public void setVisible(boolean bool) {
        frame.setVisible(bool);
    }
}
