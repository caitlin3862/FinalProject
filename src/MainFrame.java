import javax.swing.*;
import java.awt.*;


public class MainFrame implements Runnable {
    private GraphicsPanel panel;
    public MainFrame() {
        JFrame frame = new JFrame("Dance Game"); // title of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes it so you can exit the window
        frame.setSize(1400,805); // sets the size of the window
        frame.setLocation(10, 10); // sets where the window first appears on the screen


        panel = new GraphicsPanel();
        frame.add(panel);


        frame.setVisible(true); // makes it so you can see the graphics

        //animation code
        Thread thread = new Thread(this);
        thread.start();


    }

    // keeps running the panel over n over
    public void run(){
        while (true){
            panel.repaint();
        }
    }
}
