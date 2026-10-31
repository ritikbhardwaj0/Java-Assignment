import java.awt.*;
import java.awt.event.*;

public class AWTButtonClick {

    public static void main(String[] args) {

        // Create a Frame
        Frame frame = new Frame("Button Example");

        // Create a Button
        Button button = new Button("Click Me");

        // Set button position
        button.setBounds(120, 100, 100, 40);

        // Add ActionListener to button
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Button Clicked");

            }
        });

        // Add button to frame
        frame.add(button);

        // Set frame size
        frame.setSize(350, 250);

        // Use null layout
        frame.setLayout(null);

        // Display frame
        frame.setVisible(true);
    }
}