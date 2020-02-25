package JavaCool303;

import java.awt.Component;

public interface Cool303Component {
    Component getComponent();
    void paint(Cool303Theme theme);
    void setSize(int width, int height);
    int getWidth();
    int getHeight();
}
