import java.awt.*;
import java.awt.event.*;

public class WindowAdapterExample {

    public static void main(String[] args) {

        Frame frame = new Frame("WindowAdapter Example");

        // Add WindowAdapter
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                frame.dispose();     // Close the window
                System.exit(0);      // Terminate the application

            }

        });

        frame.setSize(400, 300);
        frame.setVisible(true);

    }
}