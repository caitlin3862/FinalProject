import org.w3c.dom.css.Rect;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class GraphicsPanel extends JPanel implements MouseListener, ActionListener, MouseMotionListener, KeyListener {
    private BufferedImage background;
    private BufferedImage danceStage;
    private BufferedImage playButton;
    private BufferedImage bigPlayButton;
    private BufferedImage exitButton;
    private BufferedImage bigExitButton;
    private BufferedImage rulesButton;
    private BufferedImage bigRulesbutton;
    private Move one;
    private Move two;
    private Move three;
    private Move four;
    private Move five;
    private Move six;
    private Move seven;
    private Move eight;
    private Move nine;

    private Boss miller;
    private PlayerMoves player;
    private Rectangle exitRect;
    private Rectangle playRect;
    private Rectangle rulesRect;
    private Clip song;
    private int playButtonX;
    private boolean[] pressedKeys;
    private boolean isTitleScreen;
    private boolean playingGame;



    public GraphicsPanel(){
        try {
            background = ImageIO.read(new File("src/homeScreenImgs/homeScreen.png"));
            playButton = ImageIO.read(new File("src/homeScreenImgs/play.png"));
            bigPlayButton = ImageIO.read(new File("src/homeScreenImgs/bigPlay.png"));
            exitButton = ImageIO.read(new File("src/homeScreenImgs/exit.png"));
            bigExitButton = ImageIO.read(new File("src/homeScreenImgs/bigExit.png"));
            rulesButton = ImageIO.read(new File("src/homeScreenImgs/rules.png"));
            bigRulesbutton = ImageIO.read(new File("src/homeScreenImgs/bigRules.png"));

            danceStage = ImageIO.read(new File("src/danceStage.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        addMouseListener(this);
        addMouseMotionListener(this);
        playButtonX = 606;
        pressedKeys = new boolean[128];
        isTitleScreen = true;
        playingGame = false;
        miller = new Boss();
        player = new PlayerMoves();

    }


    @Override
    // controls the images on the window
    public void paintComponent(Graphics g){
        super.paintComponent(g); // add js cuz
        g.drawImage(background,0,0,null);
        g.drawImage(exitButton, 250, 550,null);
        g.drawImage(playButton,playButtonX,550,null);
        g.drawImage(rulesButton, 953, 550,null);

        //g.drawImage(bigExitButton, 250,550,null);
        //g.drawImage(bigPlayButton, 595, 546, null);
        //g.drawImage(bigRulesbutton, 952, 550,null);

        exitRect = new Rectangle(250,550, exitButton.getWidth(), exitButton.getHeight());
        playRect = new Rectangle(playButtonX,550, exitButton.getWidth(), exitButton.getHeight());
        rulesRect = new Rectangle(953,550, exitButton.getWidth(), exitButton.getHeight());

        //Graphics2D g2 = (Graphics2D) g;
        //g2.setStroke(new BasicStroke(10)); // changes the weight of the line


    }


    /* MOUSE LISTENER METHODS */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {  // left mouse click
            if (exitRect.contains(e.getPoint())){
                System.exit(0);
            }
            if (playRect.contains(e.getPoint())){
                background = danceStage;

                repaint();
            }
            if (rulesRect.contains(e.getPoint())){
                //background = img;
                repaint();
                //somehow remove the buttons and then replace them after you exit the rules page
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if ((e.getX() > 249 && e.getX() < 447) && (e.getY() > 550 && e.getY() < 661)){

            repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /* ACTION LISTENER METHODS */

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /* MOUSE MOTION LISTENER METHODS */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    //https://stackoverflow.com/questions/34461186/how-to-detect-mouse-hover-on-an-image-drawn-from-paintcomponents-drawimage-m
    @Override
    public void mouseMoved(MouseEvent e) {
        if (exitRect.contains(e.getPoint())){
            exitButton = bigExitButton;
        } else {
            try {
                exitButton = ImageIO.read(new File("src/homeScreenImgs/exit.png"));
            } catch (IOException exception){
                System.out.println(exception.getMessage());
            }
        }
        if (playRect.contains(e.getPoint())){
            playButton = bigPlayButton;
            playButtonX = 595;
        } else {
            try {
                playButton = ImageIO.read(new File("src/homeScreenImgs/play.png"));
                playButtonX = 606;
            } catch (IOException exception){
                System.out.println(exception.getMessage());
            }
        }
        if (rulesRect.contains(e.getPoint())) {
            rulesButton = bigRulesbutton;
        } else {
            try {
                rulesButton = ImageIO.read(new File("src/homeScreenImgs/rules.png"));
            } catch (IOException exception){
                System.out.println(exception.getMessage());
            }
        }
    }

    /* KEY LISTENER METHODS */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /* PRIVATE HELPER METHODS */

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

}