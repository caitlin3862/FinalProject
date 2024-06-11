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
    private BufferedImage gameOverScreen;


    private BufferedImage playButton;
    private BufferedImage bigPlayButton;
    private BufferedImage exitButton;
    private BufferedImage bigExitButton;
    private BufferedImage rulesButton;
    private BufferedImage bigRulesbutton;
    private BufferedImage backHomeButton;
    private BufferedImage backHomeButton2;

    private BufferedImage current;
    private BufferedImage playerCurrentPose;
    private Move move;

    private BufferedImage[] currentImages;
    private BufferedImage[] firstHalfImages;
    private BufferedImage[] secondHalfImages;
    private BufferedImage[] firstBig;
    private BufferedImage[] secondBig;
    private BufferedImage[] currentBig;

    private Rectangle exitRect;
    private Rectangle playRect;
    private Rectangle rulesRect;
    private Rectangle backHomeRect = new Rectangle(30, 20, 133, 70);


    private Clip song;
    private Timer gameTimer;
    private Timer millerTimer;
    private Timer comboTimer;

    private Boss miller;
    private PlayerMoves player;

    private boolean[] pressedKeys;
    private boolean isTitleScreen;
    private boolean playingGame;
    private boolean firstHalf;
    private boolean hitInTime;
    private boolean hitAlready;
    private boolean gameOver;

    private ArrayList<Integer> trueCombo;

    private int elapsedTime;
    private int millerElapsedTime;
    private int comboElapsedTime;
    private int playButtonX;
    private int trueComboIdx;
    private int correctCount;
    private int incorrectCount;
    private int comboStreak;
    private int maxCombo;
    private double speed;
    private double speedControl;





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
            gameOverScreen = ImageIO.read(new File("src/gameOverScreen.png"));

            current = ImageIO.read(new File("src/millerSprites/idol.png"));

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("boo");
        }
        background = homeScreen;

        playButtonX = 606;
        pressedKeys = new boolean[128];

        isTitleScreen = true;
        playingGame = false;
        firstHalf = true;
        hitInTime = false;
        hitAlready = false;
        gameOver = false;

        miller = new Boss();
        player = new PlayerMoves();
        move = new Move();


        firstHalfImages = new BufferedImage[9];
        firstBig = new BufferedImage[9];
        secondHalfImages = new BufferedImage[9];
        secondBig = new BufferedImage[9];
        currentImages = new BufferedImage[9];
        currentBig = new BufferedImage[9];

        trueCombo = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            firstHalfImages[i] = move.getMove(i);
            secondHalfImages[i] = move.getMove(i+9);
        }
        for (int i = 0; i < 9; i++) {
            firstBig[i] = move.getBigMove(i);
            secondBig[i] = move.getBigMove(i+9);
        }

        currentImages = firstHalfImages;
        currentBig = firstBig;

        gameTimer = new Timer(1000,this);
        millerTimer = new Timer(1000, this);
        comboTimer = new Timer(1500,this);
        elapsedTime = 0;
        millerElapsedTime = 0;
        trueComboIdx = 0;
        comboStreak = 0;
        maxCombo = 0;
        speed = 3.0;
        speedControl = 2.0;

        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
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
            //g.drawString("Time: " + elapsedTime, 20, 100);

            exitRect = new Rectangle(250, 550, exitButton.getWidth(), exitButton.getHeight());
            playRect = new Rectangle(playButtonX, 550, exitButton.getWidth(), exitButton.getHeight());
            rulesRect = new Rectangle(953, 550, exitButton.getWidth(), exitButton.getHeight());




        } else if (playingGame) {
            generateCombo();
            playerCurrentPose = currentBig[trueCombo.get(trueComboIdx)];

            // playing game screen
            g.drawImage(background,0,0,null);
            g.setColor(Color.GREEN);
            g.drawString("Time: " + elapsedTime, 20, 100);
            g.drawString("Time: " + comboElapsedTime, 20, 120);
            g.setFont(new Font("Arial", Font.BOLD, 70));
            g.setColor(Color.GREEN);
            g.drawString("Combo: " + comboStreak, 550, 140);
            if (elapsedTime == 1){
                g.setFont(new Font("Arial", Font.BOLD, 120));
                g.setColor(Color.GREEN);
                g.drawString("7", 700,400);
            }
            if (elapsedTime == 2){
                g.setFont(new Font("Arial", Font.BOLD, 120));
                g.setColor(Color.GREEN);
                g.drawString("6", 700,400);
            }
            if (elapsedTime == 3){
                g.setFont(new Font("Arial", Font.BOLD, 120));
                g.setColor(Color.GREEN);
                g.drawString("5", 700,400);
            }
            if (elapsedTime == 4){
                g.setFont(new Font("Arial", Font.BOLD, 120));
                g.setColor(Color.GREEN);
                g.drawString("4", 700,400);
            }
            if (elapsedTime == 5){
                g.setFont(new Font("Arial", Font.BOLD, 120));
                g.setColor(Color.GREEN);
                g.drawString("3", 700,400);
            }
            if (elapsedTime == 6){
                g.setFont(new Font("Arial", Font.BOLD, 120));
                g.setColor(Color.GREEN);
                g.drawString("2", 700,400);
            }
            if (elapsedTime == 7){
                g.setFont(new Font("Arial", Font.BOLD, 120));
                g.setColor(Color.GREEN);
                g.drawString("1", 700,400);
            }
            if (elapsedTime == 8){
                g.setFont(new Font("Arial", Font.BOLD, 120));
                g.setColor(Color.GREEN);
                g.drawString("DANCE!", 550,400);
            }


            g.drawImage(current, 975, 50, null);
            g.drawImage(playerCurrentPose, 200, 50, null);


            if (pressedKeys[49]) {
                System.out.println("pressed 1");
            }
            if (pressedKeys[50]) {
                System.out.println("pressed 2");
            }
            if(pressedKeys[51]){
                System.out.println("pressed 3");
            }
            if(pressedKeys[52]){
                System.out.println("pressed 4");;
            }
            if(pressedKeys[53]){
                System.out.println("pressed 9");
            }
            if(pressedKeys[54]){
                System.out.println("pressed 9");
            }
            if(pressedKeys[55]){
                System.out.println("pressed 9");
            }
            if(pressedKeys[56]){
                System.out.println("pressed 9");
            }
            if(pressedKeys[57]){
                System.out.println("pressed 9");
            }

//            if (comboStreak == 5) {
//                g.setFont(new Font("Arial", Font.BOLD, 90));
//                g.setColor(Color.WHITE);
//                g.drawString("Faster!", 550, 400);
//                speed -= 0.5;
//                speedControl += 0.25;
//                comboStreak++;
//            }

            if (elapsedTime >= 9) {
                if (switchPose()) {
                    current = miller.getCurrentPose();
                }
                if (switchPlayerPose()) {
                    if (!hitInTime || !hitAlready) {
                        if (maxCombo < comboStreak){
                            maxCombo = comboStreak;
                        }
                        comboStreak = 0;
                    }
                    System.out.println("SwitchPlayerPose: hitInTime: " + hitInTime + " comboStreak: " + comboStreak);
                    comboElapsedTime = 0;
                    setPlayerCurrentPose();
                }
            }






            int x = 20;
            if (elapsedTime <= 82) {
                for (int i = 0; i < currentImages.length; i++) {
                    g.drawImage(currentImages[i], x, 610, null);
                    if (i == 2) {
                        x += 70;
                    }
                    x += 145;
                }
                x = 20;
                //keyPresses();
            }
            // Switch to the second set of moves after 82 seconds
            if (elapsedTime > 82 && elapsedTime < 164) { // Switch to the second set of moves after 82 seconds
                currentImages = secondHalfImages;
                currentBig = secondBig;
                firstHalf = false;
                for (int i = 0; i < currentImages.length; i++) {
                    g.drawImage(currentImages[i], x, 610, null);
                    if (i == 1 || i == 6) {
                        x += 70;
                    }
                    x += 140;
                }
                x = 20;
                //keyPresses();
            }


            if (elapsedTime >= 164) {// End the game after the song duration
                gameOver = true;
//                try {
//                    miller.setCurrentPose(ImageIO.read(new File("src/millerSprites/shocked.png")));
//                } catch (IOException ex) {
//                    ex.getMessage();
//                    System.out.println("ahh");
//                }

                playingGame = false;
                //JOptionPane.showMessageDialog(GraphicsPanel.this, "Game Over! Your score: " + player.getScore());
                //isTitleScreen = true;
                //background = homeScreen;
                repaint();
            }

            repaint();

            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.setColor(Color.WHITE);
            g.drawString("Score: " + player.getScore(), 20, 50);

            //generateCombo();
            //g.drawImage(playerCurrentPose, 100,100,null);
            //int i = 0;
            //for (int m : currentCombo) {
            // if (m == playerCombo.get(i)) {
            //      player.addScore(10);
            //  }
            //  i++;
            // }


            for (int i = 0; i < player.getPlayerMoves().size(); i++) {
                if (trueCombo.get(i).equals(player.getPlayerMoves().get(i))) {
                    player.addScore(50);
                    correctCount++;
                    player.clearCombo();
                } else {
                    incorrectCount++;
                }
            }


        } else if (gameOver){
            background = gameOverScreen;
            g.drawImage(background, 0, 0, null);
            g.drawImage(backHomeButton, 30, 20, null);

            backHomeRect = new Rectangle(30, 20, backHomeButton.getWidth(), backHomeButton.getHeight());
//                try {
//                    miller.setCurrentPose(ImageIO.read(new File("src/millerSprites/shocked.png")));
//                } catch (IOException ex) {
//                    ex.getMessage();
//                    System.out.println("ahh");
//                }

            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.setColor(Color.WHITE);
            g.drawString("Final Score: " + player.getScore(), 550, 200);
            g.setFont(new Font("Arial", Font.BOLD, 38));
            g.setColor(Color.WHITE);
            g.drawString("Number of correct moves:  " + correctCount, 450, 350);
            g.setFont(new Font("Arial", Font.BOLD, 38));
            g.setColor(Color.WHITE);
            g.drawString("Number of incorrect moves: " + incorrectCount, 400, 450);
            g.setFont(new Font("Arial", Font.BOLD, 38));
            g.setColor(Color.WHITE);
            g.drawString("Max combo: " + maxCombo, 550, 550);
            repaint();
            gameTimer.stop();
            song.close();
            repaint();
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
            if (e.getSource().equals(gameTimer)){
                elapsedTime++;
            }
            comboElapsedTime++;
            if (e.getSource() == comboTimer){
                playerCurrentPose = move.pickRandPose(currentImages, firstHalf);
                repaint();
            }
            millerElapsedTime++;
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
                System.out.println("adfds");
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
                System.out.println("play");
            }
        }
        if (rulesRect.contains(e.getPoint())) {
            rulesButton = bigRulesbutton;
        } else {
            try {
                rulesButton = ImageIO.read(new File("src/homeScreenImgs/rules.png"));
            } catch (IOException exception){
                System.out.println(exception.getMessage());
                System.out.println("rules");
            }
        }
        if (backHomeRect.contains(e.getPoint())){
            backHomeButton = backHomeButton2;
        } else {
            try {
                backHomeButton = ImageIO.read(new File("src/rulesScreenImgs/backHome.png"));
            } catch (IOException exception){
                System.out.println(exception.getMessage());
                System.out.println("home");
            }
        }

    }

    /* KEY LISTENER METHODS */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        pressedKeys[key] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        pressedKeys[key] = false;
        player.getPlayerMoves().add(0);
        if (comboElapsedTime < 3 && !hitAlready) {
            comboStreak++;
            hitAlready = true;
            hitInTime = true;
        }
        System.out.println("KeyReleased: comboElapsedTime: " + comboElapsedTime + " hitInTime: " + hitInTime + " comboStreak: " + comboStreak);
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

    private boolean switchPose(){
        if (millerElapsedTime % 2 == 0 && millerElapsedTime > 10){
            return true;
        } else {
            return false;
        }
    }

    private boolean switchPlayerPose() {
        boolean result = (comboElapsedTime % speedControl == 0 &&  comboElapsedTime > speed);
        System.out.println("switchPlayerPose: " + result + " comboElapsedTime: " + comboElapsedTime);
        return result;
    }

    private void generateCombo(){
        for (int i = 0; i < 5; i++) {
            int rand = (int) (Math.random() * currentImages.length);
            if (rand == 0) {
                trueCombo.add(0);
            } else if (rand == 1) {
                trueCombo.add(1);
            } else if (rand == 2) {
                trueCombo.add(2);
            } else if (rand == 3) {
                trueCombo.add(3);
            } else if (rand == 4) {
                trueCombo.add(4);
            } else if (rand == 5) {
                trueCombo.add(5);
            } else if (rand == 6) {
                trueCombo.add(6);
            } else if (rand == 7) {
                trueCombo.add(7);
            } else {
                trueCombo.add(8);
            }
        }
    }

    private void setPlayerCurrentPose() {
        if (trueComboIdx + 1 < 9) {
            trueComboIdx++;
            playerCurrentPose = currentBig[trueCombo.get(trueComboIdx)];
            repaint();
        } else {
            trueComboIdx = 0;
            playerCurrentPose = currentBig[trueCombo.get(trueComboIdx)];
            repaint();
        }
        hitAlready = false;
        hitInTime = false;
    }




}