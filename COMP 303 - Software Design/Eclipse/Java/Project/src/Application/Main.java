package Application;

import JavaCool303.*;

public class Main {
    public static void main(String[] args) {
        Cool303Frame frame = new Cool303Frame("Big treats for good dogs!");
        Cool303Root root = new Cool303Root(new Pastel());
        Cool303Container container = new Cool303Container("The water rises mr hot dog lady");

        container.enableBgColor(true);

        for ( int i = 1; i <= 20; i++ ) {
            container.add(new Cool303Button(Integer.toString(i)));
        }

        root.add(container);

        root.paint();

        frame.add(root);
        frame.setVisible(true);
    }
}