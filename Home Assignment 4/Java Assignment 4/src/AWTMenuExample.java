import java.awt.*;

public class AWTMenuExample {

    public static void main(String[] args) {

        // Create Frame
        Frame frame = new Frame("AWT Menu Example");

        // Create MenuBar
        MenuBar menuBar = new MenuBar();

        // Create Menus
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");

        // Add Menus to MenuBar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        // Set MenuBar to Frame
        frame.setMenuBar(menuBar);

        // Set Frame Size
        frame.setSize(400, 300);

        // Display Frame
        frame.setVisible(true);
    }
}