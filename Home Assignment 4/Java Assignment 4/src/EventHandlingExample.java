import javax.swing.*;
import java.awt.event.*;

public class EventHandlingExample {

    public static void main(String[] args) {

        // Create JFrame
        JFrame frame = new JFrame("Event Handling");

        // Create Button
        JButton button = new JButton("Click Me");
        button.setBounds(100, 80, 120, 40);

        // Add ActionListener
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(frame, "Hello User");

            }
        });

        // Add Button to Frame
        frame.add(button);

        // Frame Settings
        frame.setSize(350, 250);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}