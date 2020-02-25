// File name: Cool303Root

package JavaCool303;

/**
 * Extends Cool303Container. <p>
 * In essence, a Cool303Container with no title and that can only add other Cool303Containers.
 *
 * @author Le Nhat Hung
 * @version 1.0
 * @since April 9 2018
 */

public class Cool303Root extends Cool303Container {
    private Cool303Theme theme;
    private int desiredWidth, desiredHeight;

    /**
     * Constructor. Sets minimum size for all aggregated containers. <p>
     * Calls parent constructor but throws exception if no theme is specified.
     *
     * @param theme
     */
    public Cool303Root(Cool303Theme theme) {
        super();

        if (theme == null)
            throw new IllegalArgumentException("Need to specify a theme.");

        this.theme = theme;

        // setPreferredSize(null) makes JPanel automatically adjust to minimum size for all aggregated components.
        getComponent().setPreferredSize(null);
    }

    /**
     * Constructor. Sets desired size. Calls parents constructor but throws exception if no theme is specified.
     *
     * @param width
     * @param height
     * @param theme
     */
    public Cool303Root(int width, int height, Cool303Theme theme) {
        this(theme);

        desiredWidth = width;
        desiredHeight = height;

        setSize(width, height);
    }

    /**
     * Parent method override. Instead of being able to add any Cool303Component, can only add Cool303Container. <p>
     * Readjusts for minimum size if necessary once container's added.
     *
     * @param c Cool303Component
     */
    @Override
    public void add(Cool303Component c) {

        // Only add if component is an instance of Cool303Container.
        if (c instanceof Cool303Container) {
            super.add(c);
            c.register(this);
            c.paint(theme);
        }

        // Readjust for minimum size if necessary.
        if (desiredWidth < getWidth() || desiredHeight < getHeight())
            getComponent().setPreferredSize(null);
    }

    /**
     * Method for Cool303Container to notify the root when a new <p>
     * Cool303Component (button or another container) is added.
     *
     * @param c
     */
    public void notify(Cool303Container c) {
        c.paint(theme);
    }

    /**
     * Iterates through all aggregated containers and calculates their total width.
     * Returns this width. Used in above add method to check if size readjustment is necessary.
     *
     * @return combined width of all aggregated containers
     */
    @Override
    public int getWidth() {
        int width = 0;

        for (Cool303Component c: getChildren())
            width += c.getWidth();

        return width;
    }

    /**
     * Iterates through all aggregated containers and calculates their total height.
     * Returns this height. Used in above add method to check if size readjustment is necessary.
     *
     * @return combined height of all aggregated containers
     */
    @Override
    public int getHeight() {
        int height = 0;

        for (Cool303Component c: getChildren())
            height += c.getHeight();

        return height;
    }

    /**
     * Register override. Cool303Root doesn't need to register to anything <p>
     * as it itself holds the theme field and applies it.
     *
     * @param c
     */
    @Override
    public void register(Cool303Container c) {}
}
