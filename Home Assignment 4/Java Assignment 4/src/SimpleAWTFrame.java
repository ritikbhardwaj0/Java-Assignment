import java.awt.*;

public class SimpleAWTFrame {

    public static void main(String[] args) {

        // Create a Frame
        Frame frame = new Frame("My First AWT Frame");

        // Create a Label
        Label label = new Label("Welcome to Java GUI");

        // Set label position
        label.setBounds(100, 100, 150, 30);

        // Add label to frame
        frame.add(label);

        // Set frame size
        frame.setSize(400, 300);

        // Use null layout
        frame.setLayout(null);

        // Make frame visible
        frame.setVisible(true);
    }
}