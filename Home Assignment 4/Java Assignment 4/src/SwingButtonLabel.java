import javax.swing.*;
import java.awt.event.*;

public class SwingButtonLabel {

    public static void main(String[] args) {

        // Create JFrame
        JFrame frame = new JFrame("Swing Button Example");

        // Create JLabel
        JLabel label = new JLabel("Click the button");
        label.setBounds(120, 50, 150, 30);

        // Create JButton
        JButton button = new JButton("Click Me");
        button.setBounds(110, 100, 120, 40);

        // Add ActionListener
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Button Pressed");
            }
        });

        // Add components
        frame.add(label);
        frame.add(button);

        // Frame settings
        frame.setSize(350, 250);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}