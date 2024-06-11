import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Move {
    //private BufferedImage move;
    //private int x;
    //private int y;
    private BufferedImage[] images;
    private BufferedImage[] bigImages;
    private ArrayList<Integer> trueCombo;
    //private int num;



    public Move(){
        //num = moveNum;
        images = new BufferedImage[18];
        bigImages = new BufferedImage[18];
        trueCombo = new ArrayList<>();
        try {
            images[0] = ImageIO.read(new File("src/studentSprites/arms.png"));
            images[1] = ImageIO.read(new File("src/studentSprites/caitlin.png"));
            images[2] = ImageIO.read(new File("src/studentSprites/car.png"));
            images[3] = ImageIO.read(new File("src/studentSprites/disco.png"));
            images[4] = ImageIO.read(new File("src/studentSprites/duo.png"));
            images[5] = ImageIO.read(new File("src/studentSprites/gangnam.png"));
            images[6] = ImageIO.read(new File("src/studentSprites/gun.png"));
            images[7] = ImageIO.read(new File("src/studentSprites/handHip.png"));
            images[8] = ImageIO.read(new File("src/studentSprites/hovering.png"));
            images[9] = ImageIO.read(new File("src/studentSprites/kick.png"));
            images[10] = ImageIO.read(new File("src/studentSprites/kristen.png"));
            images[11] = ImageIO.read(new File("src/studentSprites/maracas.png"));
            images[12] = ImageIO.read(new File("src/studentSprites/MJ.png"));
            images[13] = ImageIO.read(new File("src/studentSprites/point.png"));
            images[14] = ImageIO.read(new File("src/studentSprites/punch.png"));
            images[15] = ImageIO.read(new File("src/studentSprites/slide.png"));
            images[16] = ImageIO.read(new File("src/studentSprites/spiderman.png"));
            images[17] = ImageIO.read(new File("src/studentSprites/webShooter.png"));

            bigImages[0] = ImageIO.read(new File("src/studentSprites/arms2.png"));
            bigImages[1] = ImageIO.read(new File("src/studentSprites/caitlin2.png"));
            bigImages[2] = ImageIO.read(new File("src/studentSprites/car2.png"));
            bigImages[3] = ImageIO.read(new File("src/studentSprites/disco2.png"));
            bigImages[4] = ImageIO.read(new File("src/studentSprites/duo2.png"));
            bigImages[5] = ImageIO.read(new File("src/studentSprites/gangnam2.png"));
            bigImages[6] = ImageIO.read(new File("src/studentSprites/gun2.png"));
            bigImages[7] = ImageIO.read(new File("src/studentSprites/handHip2.png"));
            bigImages[8] = ImageIO.read(new File("src/studentSprites/hovering2.png"));
            bigImages[9] = ImageIO.read(new File("src/studentSprites/kick2.png"));
            bigImages[10] = ImageIO.read(new File("src/studentSprites/kristen2.png"));
            bigImages[11] = ImageIO.read(new File("src/studentSprites/maracas2.png"));
            bigImages[12] = ImageIO.read(new File("src/studentSprites/MJ2.png"));
            bigImages[13] = ImageIO.read(new File("src/studentSprites/point2.png"));
            bigImages[14] = ImageIO.read(new File("src/studentSprites/punch2.png"));
            bigImages[15] = ImageIO.read(new File("src/studentSprites/slide2.png"));
            bigImages[16] = ImageIO.read(new File("src/studentSprites/spiderman2.png"));
            bigImages[17] = ImageIO.read(new File("src/studentSprites/webShooter2.png"));

        } catch (IOException e) {
            e.getMessage();
            System.out.println("moves");
        }
    }

    public BufferedImage getMove(int index){
        return images[index];
    }

    public BufferedImage getBigMove(int index){
        return bigImages[index];
    }

    public BufferedImage[] getImages() {
        return images;
    }

    public BufferedImage pickRandPose(BufferedImage[] images, boolean firstHalf){
        int rand = (int) (Math.random() * 9);
        if (firstHalf) {
            if (rand == 0) {
                return bigImages[0];
            } else if (rand == 1) {
                return bigImages[1];
            } else if (rand == 2) {
                return bigImages[2];
            } else if (rand == 3) {
                return bigImages[3];
            } else if (rand == 4) {
                return bigImages[4];
            } else if (rand == 5) {
                return bigImages[5];
            } else if (rand == 6) {
                return bigImages[6];
            } else if (rand == 7) {
                return bigImages[7];
            } else {
                return bigImages[8];
            }
        } else {
            if (rand == 0) {
                return bigImages[9];
            } else if (rand == 1) {
                return bigImages[10];
            } else if (rand == 2) {
                return bigImages[11];
            } else if (rand == 3) {
                return bigImages[12];
            } else if (rand == 4) {
                return bigImages[13];
            } else if (rand == 5) {
                return bigImages[14];
            } else if (rand == 6) {
                return bigImages[15];
            } else if (rand == 7) {
                return bigImages[16];
            } else  {
                return bigImages[17];
            }
        }
    }

    public Rectangle moveRect(BufferedImage img){
        int imageHeight = img.getHeight();
        int imageWidth = img.getWidth();
        Rectangle rect = new Rectangle(img.getWidth(), img.getHeight(), imageWidth, imageHeight);
        return rect;
    }

//    public void chooseNextPose() {
//        int pose = (int) (Math.random() * 10) + 2;
//        currentPose = bigImages[pose];
//    }



}
