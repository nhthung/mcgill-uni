// File name: Cool303Component.java

package JavaCool303;

import java.awt.Component;

/**
 * Interface implemented by Cool303Container, Cool303Button and Cool303.
 *
 * @author Le Nhat Hung
 * @version 1.0
 * @since April 9 2018
 */

public interface Cool303Component {
    void register(Cool303Container parentContainer);

    void paint(Cool303Theme theme);

    void setSize(int width, int height);

    Component getComponent();
    int getWidth();
    int getHeight();
}
