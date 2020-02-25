// File name: BubbleBorder.java

package JavaCool303;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;

/**
 * Bubble border implementation. Extends Java AbstractBorder.
 *
 * @author Le Nhat Hung
 * @version 1.0
 * @since April 9 2018
 */

class BubbleBorder extends AbstractBorder {
    private int thickness, radius, strokePad;

    private Color color;
    private Insets insets;
    private BasicStroke stroke;
    private RenderingHints hints;

    /**
     * Constructor.
     *
     * @param color
     * @param thickness
     * @param radius
     */
    BubbleBorder(Color color, int thickness, int radius) {
        this.thickness = thickness;
        this.radius = radius;
        this.color = color;

        stroke = new BasicStroke(thickness);
        strokePad = thickness / 2;

        hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int pad = radius + strokePad;
        int bottomPad = pad + strokePad;
        insets = new Insets(pad, pad, bottomPad, pad);
    }

    /**
     * Parent method override.
     *
     * @param c
     * @return insets
     */
    @Override
    public Insets getBorderInsets(Component c) { return insets; }

    /**
     * Parent method override.
     *
     * @param c
     * @param insets
     * @return getBorderInsets(c)
     */
    @Override
    public Insets getBorderInsets(Component c, Insets insets) { return getBorderInsets(c); }

    /**
     * Rounded corner implementation.
     *
     * @param c
     * @param g
     * @param x
     * @param y
     * @param width
     * @param height
     */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        int bottomLineY = height - thickness;

        RoundRectangle2D.Double bubble = new RoundRectangle2D.Double(
                0 + strokePad,
                0 + strokePad,
                width - thickness,
                bottomLineY,
                radius,
                radius);


        Area area = new Area(bubble);
        g2.setRenderingHints(hints);

        // Paint the parent background color, everywhere outside the clip of the text bubble.
        Component parent  = c.getParent();
        if (parent != null) {
            Color bg = parent.getBackground();
            Rectangle rect = new Rectangle(0,0,width, height);
            Area borderRegion = new Area(rect);
            borderRegion.subtract(area);
            g2.setClip(borderRegion);
            g2.setColor(bg);
            g2.fillRect(0, 0, width, height);
            g2.setClip(null);
        }

        g2.setColor(color);
        g2.setStroke(stroke);
        g2.draw(area);
    }
}