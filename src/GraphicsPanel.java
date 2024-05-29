import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class GraphicsPanel extends JPanel implements MouseListener {
    private BufferedImage titleBg;




    public GraphicsPanel(){
        try {
            titleBg = ImageIO.read(new File("src/backgroundImages/disco.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }




    @Override
    // controls the images on the window
    public void paintComponent(Graphics g){
        super.paintComponent(g); // add js cuz
        g.drawImage(titleBg,0,0,null);


        //Graphics2D g2 = (Graphics2D) g;
        //g2.setStroke(new BasicStroke(10)); // changes the weight of the line
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}


