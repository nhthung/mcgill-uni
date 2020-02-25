// File name: Cool303Button.java

package JavaCool303;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Wraps Swing JButton, implements Cool303Component.
 *
 * @author Le Nhat Hung
 * @version 1.0
 * @since April 9 2018
 */

public class Cool303Button implements Cool303Component {
    private JButton button;

    /**
     * Instantiates blank JButton with a set dimension. Defines action listener.
     */
    public Cool303Button() {
        button = new JButton(" ");

        button.setPreferredSize(new Dimension(55, 40));

        button.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("This button empty. YEET");
            }
        });
    }

    /**
     * Instantiates JButton with 'text' as label with a set dimension. Defines action listener as printing label to console.
     *
     * @param text
     */
    public Cool303Button(String text) {
        button = new JButton(text);

        button.setPreferredSize(new Dimension(55, 40));

        button.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(text);
            }
        });
    }

    /**
     * Paints button's border, background and label according to theme argument.
     *
     * @param theme
     */
    @Override
    public void paint(Cool303Theme theme) {
        button.setBorder(theme.getBorder());
        button.setBackground(theme.getButtonColor());
        button.setForeground(theme.getBorderColor());
    }

    /**
     * Returns the JButton wrapped by the current class. Serves to abstract Cool303Container.add(Cool303Component).
     *
     * @return button
     */
    @Override
    public Component getComponent() {
        return button;
    }

    /**
     * Parent method override. Cool303Button's size is fixed.
     *
     * @param width
     * @param height
     */
    @Override
    public void setSize(int width, int height) {}

    /**
     * Return button's width.
     *
     * @return button.getWidth
     */
    @Override
    public int getWidth() { return button.getWidth(); }

    /**
     * Return button's height.
     *
     * @return button.getHeight()
     */
    @Override
    public int getHeight() { return button.getHeight(); }

    /**
     * Register override. Button never needs to notify anything.
     *
     * @param parentContainer
     */
    @Override
    public void register(Cool303Container parentContainer) {}
}
