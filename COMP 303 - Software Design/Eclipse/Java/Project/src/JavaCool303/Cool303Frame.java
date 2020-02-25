package JavaCool303;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.util.ArrayList;

public class Cool303Frame {
    JFrame frame;
    ArrayList<Cool303Root> roots = new ArrayList<>();

    public Cool303Frame() {
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Cool303Frame(String title) {
        frame = new JFrame(title);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void add(Cool303Root root) {
        frame.add(root.getComponent());
        roots.add(root);

        frame.pack();
        frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
    }

    public void remove(int i) {
        if (i < 0 || i >= roots.size())
            throw new IllegalArgumentException("Index out of bounds.");

        frame.remove(roots.get(i).getComponent());
        roots.remove(i);

        frame.pack();
    }

    public void removeAll() {
        frame.removeAll();
        roots.clear();
    }

    public void setVisible(boolean bool) {
        frame.setVisible(bool);
    }
}
