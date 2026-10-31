import javax.swing.*;
import java.awt.*;

public class BorderLayoutExample {

    public static void main(String[] args) {

        // Create JFrame
        JFrame frame = new JFrame("BorderLayout Example");

        // Set BorderLayout
        frame.setLayout(new BorderLayout());

        // Add Buttons
        frame.add(new JButton("North"), BorderLayout.NORTH);
        frame.add(new JButton("South"), BorderLayout.SOUTH);
        frame.add(new JButton("East"), BorderLayout.EAST);
        frame.add(new JButton("West"), BorderLayout.WEST);
        frame.add(new JButton("Center"), BorderLayout.CENTER);

        // Frame settings
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}