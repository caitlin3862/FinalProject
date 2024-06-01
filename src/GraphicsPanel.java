import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class GraphicsPanel extends JPanel implements MouseListener, ActionListener {
    private BufferedImage titleBg;
    private BufferedImage playButton;
    private BufferedImage bigPlayButton;
    private BufferedImage exitButton;
    private BufferedImage bigExitButton;
    private BufferedImage rulesButton;
    private BufferedImage bigRulesbutton;
    private Clip song;





    public GraphicsPanel(){
        try {
            titleBg = ImageIO.read(new File("src/homeScreenImgs/homeScreen.png"));
            playButton = ImageIO.read(new File("src/homeScreenImgs/play.png"));
            bigPlayButton = ImageIO.read(new File("src/homeScreenImgs/bigPlay.png"));
            exitButton = ImageIO.read(new File("src/homeScreenImgs/holder.png"));
            bigExitButton = ImageIO.read(new File("src/homeScreenImgs/holder.png"));
            rulesButton = ImageIO.read(new File("src/homeScreenImgs/holder.png"));
            bigRulesbutton = ImageIO.read(new File("src/homeScreenImgs/holder.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        startMusic();
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

    private void startMusic(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/gummyBearSong.wav").getAbsoluteFile());
            song = AudioSystem.getClip();
            song.open(audioInputStream);
            song.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


