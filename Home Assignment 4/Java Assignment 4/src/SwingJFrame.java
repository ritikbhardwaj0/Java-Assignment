import javax.swing.JFrame;

public class SwingJFrame {

    public static void main(String[] args) {

        // Create JFrame
        JFrame frame = new JFrame("My Swing App");

        // Set frame size
        frame.setSize(400, 300);

        // Close application when window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display frame
        frame.setVisible(true);
    }
}