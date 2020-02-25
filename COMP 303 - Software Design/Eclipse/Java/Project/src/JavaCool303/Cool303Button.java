package JavaCool303;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cool303Button implements Cool303Component {
    private JButton button;

    public Cool303Button() {
        button = new JButton(" ");

        button.setPreferredSize(new Dimension(55, 40));

        button.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("This button empty. YEET");
            }
        });
    }

    public Cool303Button(String text) {
        button = new JButton(text);

        button.setPreferredSize(new Dimension(55, 40));

        button.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(text);
            }
        });
    }

    @Override
    public Component getComponent() {
        return button;
    }

    @Override
    public void paint(Cool303Theme theme) {
        button.setBorder(theme.getBorder());
        button.setBackground(theme.getButtonColor());
        button.setForeground(theme.getBorderColor());
    }

    @Override
    public void setSize(int width, int height) {}

    @Override
    public int getWidth() { return button.getWidth(); }

    @Override
    public int getHeight() { return button.getHeight(); }
}
