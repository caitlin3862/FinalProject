import org.w3c.dom.css.Rect;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel implements MouseListener, ActionListener, MouseMotionListener, KeyListener {
    private BufferedImage background;
    private BufferedImage homeScreen;
    private BufferedImage danceStage;
    private BufferedImage rulesScreen;


    private BufferedImage playButton;
    private BufferedImage bigPlayButton;
    private BufferedImage exitButton;
    private BufferedImage bigExitButton;
    private BufferedImage rulesButton;
    private BufferedImage bigRulesbutton;
    private BufferedImage backHomeButton;
    private BufferedImage backHomeButton2;

    private BufferedImage current;
    private Move move;

    private BufferedImage[] currentImages;
    private BufferedImage[] firstHalfImages;
    private BufferedImage[] secondHalfImages;

    private Rectangle exitRect;
    private Rectangle playRect;
    private Rectangle rulesRect;
    private Rectangle backHomeRect;

    // sprites rectangles
    private Rectangle oneRect;
    private Rectangle twoRect;
    private Rectangle threeRect;
    private Rectangle fourRect;
    private Rectangle fiveRect;
    private Rectangle sixRect;
    private Rectangle sevenRect;
    private Rectangle eightRect;
    private Rectangle nineRect;

    private Clip song;
    private Timer gameTimer;
    private Timer millerTimer;

    private Boss miller;
    private PlayerMoves player;

    private boolean[] pressedKeys;
    private boolean isTitleScreen;
    private boolean playingGame;

    private ArrayList<Integer> currentCombo;
    private ArrayList<Integer> playerCombo;

    private int elapsedTime;
    private int playButtonX;




    public GraphicsPanel(){
        try {
            homeScreen = ImageIO.read(new File("src/homeScreenImgs/homeScreen.png"));
            playButton = ImageIO.read(new File("src/homeScreenImgs/play.png"));
            bigPlayButton = ImageIO.read(new File("src/homeScreenImgs/bigPlay.png"));
            exitButton = ImageIO.read(new File("src/homeScreenImgs/exit.png"));
            bigExitButton = ImageIO.read(new File("src/homeScreenImgs/bigExit.png"));
            rulesButton = ImageIO.read(new File("src/homeScreenImgs/rules.png"));
            bigRulesbutton = ImageIO.read(new File("src/homeScreenImgs/bigRules.png"));

            danceStage = ImageIO.read(new File("src/danceStage.png"));
            rulesScreen = ImageIO.read(new File("src/rulesScreenImgs/rulesScreen.png"));
            backHomeButton = ImageIO.read(new File("src/rulesScreenImgs/backHome.png"));
            backHomeButton2 = ImageIO.read(new File("src/rulesScreenImgs/backHome2.png"));

            current = ImageIO.read(new File("src/millerSprites/idol.png"));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        background = homeScreen;

        playButtonX = 606;
        pressedKeys = new boolean[128];

        isTitleScreen = true;
        playingGame = false;

        miller = new Boss();
        player = new PlayerMoves();
        move = new Move();

        firstHalfImages = new BufferedImage[9];
        secondHalfImages = new BufferedImage[9];
        for (int i = 0; i < 9; i++) {
            firstHalfImages[i] = move.getMove(i);
            secondHalfImages[i] = move.getMove(i+9);
        }
        currentImages = firstHalfImages;

        gameTimer = new Timer(1000,this);
        millerTimer = new Timer(5000, this);
        elapsedTime = 0;

        addMouseListener(this);
        addMouseMotionListener(this);
    }


    @Override
    // controls the images on the window
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // add js cuz
        if (isTitleScreen) {
            // title screen
            g.drawImage(background,0,0,null);
            g.drawImage(exitButton, 250, 550, null);
            g.drawImage(playButton, playButtonX, 550, null);
            g.drawImage(rulesButton, 953, 550, null);
            g.drawString("Time: " + elapsedTime, 20, 100);

            exitRect = new Rectangle(250, 550, exitButton.getWidth(), exitButton.getHeight());
            playRect = new Rectangle(playButtonX, 550, exitButton.getWidth(), exitButton.getHeight());
            rulesRect = new Rectangle(953, 550, exitButton.getWidth(), exitButton.getHeight());
        } else if (playingGame){
            // playing game screen
            g.drawImage(background,0,0,null);
            g.drawString("Time: " + elapsedTime, 20, 100);

            g.drawImage(current, 975, 50, null);

            int x = 20;
            if (elapsedTime <= 30) {
                for (int i = 0; i < currentImages.length; i++) {
                    g.drawImage(currentImages[i], x, 610, null);
                    if (i == 2){
                        x += 60;
                    }
                    x += 145;
                }
                x = 20;
            }
            // Switch to the second set of moves after 82 seconds
            if (elapsedTime > 30 && elapsedTime < 60) { // Switch to the second set of moves after 82 seconds
                currentImages = secondHalfImages;
                for (int i = 0; i < currentImages.length; i++) {
                    g.drawImage(currentImages[i], x, 610, null);
                    if (i == 1 || i == 6){
                        x += 70;
                    }
                    x += 140;
                }
                x = 20;
            }



            if (elapsedTime >= 60) { // End the game after the song duration
                gameTimer.stop();
                song.close();
                playingGame = false;
                //JOptionPane.showMessageDialog(GraphicsPanel.this, "Game Over! Your score: " + player.getScore());
                try {
                    miller.setCurrentPose(ImageIO.read(new File("src/millerSprites/shocked.png")));
                } catch (IOException ex) {
                    ex.getMessage();
                }
                isTitleScreen = true;
                background = homeScreen;
                repaint();
            }

            repaint();

            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.setColor(Color.WHITE);
            g.drawString("Score: " + player.getScore(), 20, 50);

            if(pressedKeys[49]){

            }
            if(pressedKeys[50]){

            }
            if(pressedKeys[51]){

            }
            if(pressedKeys[52]){

            }
            if(pressedKeys[53]){

            }
            if(pressedKeys[54]){

            }
            if(pressedKeys[55]){

            }
            if(pressedKeys[56]){

            }
            if(pressedKeys[57]){

            }

        } else {
            // rules screen
            g.drawImage(background,0,0,null);
            g.drawImage(backHomeButton, 30,20,null);

            backHomeRect = new Rectangle(30, 20, backHomeButton.getWidth(), backHomeButton.getHeight());
        }



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
                isTitleScreen = false;
                playingGame = true;
                gameTimer.start();
                millerTimer.start();
                startMusic();
                repaint();
            }
            if (rulesRect.contains(e.getPoint())){
                background = rulesScreen;
                isTitleScreen = false;
                repaint();
                //somehow remove the buttons and then replace them after you exit the rules page
            }
            if (backHomeRect.contains(e.getPoint())){
                background = homeScreen;
                isTitleScreen = true;
                repaint();
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
        if (e.getSource() instanceof Timer) {
            elapsedTime++;
            if (e.getSource() == millerTimer) {
                // Change Miller's pose every 5 seconds
                miller.chooseNextPose();
                repaint();
            }
        }
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
        if (backHomeRect.contains(e.getPoint())){
            backHomeButton = backHomeButton2;
        } else {
            try {
                backHomeButton = ImageIO.read(new File("src/rulesScreenImgs/backHome.png"));
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