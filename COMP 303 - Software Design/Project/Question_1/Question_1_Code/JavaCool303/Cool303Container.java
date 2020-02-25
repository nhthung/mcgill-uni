// File name: Cool303Container.java

package JavaCool303;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import java.util.ArrayList;

/**
 * Wraps Swing JPanel, implements Cool303Component.
 *
 * @author Le Nhat Hung
 * @version 1.0
 * @since April 9 2018
 */

public class Cool303Container implements Cool303Component {
    private JPanel panel, content;
    private boolean enableBgColor = false;
    private int width, height;
    private ArrayList<Cool303Component> components = new ArrayList<>();
    private Cool303Container parentContainer;

    /**
     * Constructor. Sets default size. Object without a title is just one JPanel.
     */
    public Cool303Container() {
        panel = new JPanel(new FlowLayout());
        content = panel;

        setSize(325, 325);
    }

    /**
     * Constructor. Sets default size. Object with title is one big JPanel in border layout. <p>
     * The North holds the title and the Center holds another content JPanel in flow layout. <p>
     * Adding to object will be adding into the content JPanel
     *
     * @param title
     * @throws IllegalArgumentException
     */
    public Cool303Container(String title) throws IllegalArgumentException {
        if (title == null || title.isEmpty())
            throw new IllegalArgumentException("Title can't be blank.");

        panel = new JPanel(new BorderLayout());
        content = new JPanel(new FlowLayout());

        // Adding content JPanel into main panel's center.
        panel.add(new JLabel(title, (int)JLabel.CENTER_ALIGNMENT), BorderLayout.NORTH);
        panel.add(content, BorderLayout.CENTER);

        setSize(325, 325);
    }

    /**
     * Constructor with custom size.
     *
     * @param width
     * @param height
     */
    public Cool303Container(int width, int height){
        this();

        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Put in a real dimension.");

        setSize(width, height);
    }

    /**
     * Constructor with custom title and size.
     *
     * @param title
     * @param width
     * @param height
     */
    public Cool303Container(String title, int width, int height){
        this(title);

        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Put in a real dimension.");

        setSize(width, height);
    }

    /**
     * If set to true, Container's background will have a theme associated color. <p>
     * If false, Container's background will be transparent / default Java grey.
     *
     * @param bool
     */
    public void enableBgColor(boolean bool) { enableBgColor = bool; }

    /**
     * Adds a component into content JPanel and components array list. <p>
     * If a new Cool303Component is added into this current Cool303Container, <p>
     * then the method notifies the parent Cool303Root which will then repaint everything.
     *
     * @param c Cool303Component
     */
    public void add(Cool303Component c) {
        if (c == null)
            throw new IllegalArgumentException("Can't add null.");

        else if(c instanceof Cool303Root)
            throw new IllegalArgumentException("Can't add a root.");

        components.add(c);

        content.add( c.getComponent() );

        if (parentContainer != null && parentContainer instanceof Cool303Root)
            ((Cool303Root)parentContainer).notify(this);
    }

    /**
     * Remove a component from content JPanel and components array list.
     *
     * @param i index
     * @throws IllegalArgumentException
     */
    public void remove(int i) throws IllegalArgumentException {
        if (i < 0 || i >= components.size())
            throw new IllegalArgumentException("Index out of bounds.");

        content.remove(components.get(i).getComponent());

        components.remove(i);
    }

    /**
     * Remove all components from content JPanel and components array list.
     */
    public void removeAll() {
        content.removeAll();

        components.clear();
    }

    /**
     * Called when Cool303Container is added into a Cool303Root. <p>
     * Registers Cool303Container to its Cool303Root.
     *
     * @param parentContainer
     */
    @Override
    public void register(Cool303Container parentContainer) {
        this.parentContainer = parentContainer;
    }

    /**
     * Paints JPanel's background with theme associated color. <p>
     * Iterates through components array list and calls each component's paint(theme).
     *
     * @param theme
     */
    @Override
    public void paint(Cool303Theme theme) {
        if (enableBgColor) {
            panel.setBackground(theme.getBackgroundColor());
            content.setBackground(theme.getBackgroundColor());
        }

        for (Cool303Component c: components) {
            c.paint(theme);
        }
    }

    /**
     * Set container's size.
     *
     * @param width
     * @param height
     */
    @Override
    public void setSize(int width, int height) {
        panel.setPreferredSize(new Dimension(width, height));
        this.width = width;
        this.height = height;
    }

    /**
     * Return the components array list. Serves Cool303Root's ability to adjust for minimum size for every aggregated Cool303Containers.
     *
     * @return components array list
     */
    ArrayList<Cool303Component> getChildren() {
        return components;
    }

    /**
     * Return the main JPanel wrapped by the current class. Serves to abstract Cool303Root.add(Cool303Component).
     *
     * @return panel JPanel
     */
    @Override
    public Component getComponent() { return panel; }

    /**
     * Return container's width.
     *
     * @return width
     */
    @Override
    public int getWidth() { return width; }

    /**
     * Return container's height.
     *
     * @return height
     */
    @Override
    public int getHeight() { return height; }
}
