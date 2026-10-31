import javax.swing.*;
import java.awt.event.*;

public class KeyListenerExample {

    public static void main(String[] args) {

        JFrame frame = new JFrame("KeyListener Example");

        JLabel label = new JLabel("Press Any Key");
        label.setBounds(100, 50, 200, 30);

        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {

                label.setText("Key Pressed: " + e.getKeyChar());

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

            @Override
            public void keyTyped(KeyEvent e) {

            }

        });

        frame.add(label);

        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make frame receive keyboard input
        frame.setFocusable(true);

        frame.setVisible(true);
    }
}