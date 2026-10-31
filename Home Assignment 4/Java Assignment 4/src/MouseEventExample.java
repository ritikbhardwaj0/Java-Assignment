import javax.swing.*;
import java.awt.event.*;

public class MouseEventExample {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Mouse Event Example");

        JLabel label = new JLabel("Move the Mouse");
        label.setBounds(100, 50, 200, 30);

        frame.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseMoved(MouseEvent e) {
                label.setText("Mouse Moving");
            }

            @Override
            public void mouseDragged(MouseEvent e) {

            }
        });

        frame.add(label);

        frame.setSize(350, 250);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}