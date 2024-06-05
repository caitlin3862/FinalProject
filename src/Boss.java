import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Boss {
    private BufferedImage sprite;
    private BufferedImage shockedSprite;
    private BufferedImage ballerina;
    private BufferedImage cross1;
    private BufferedImage cross2;
    private BufferedImage heart;
    private BufferedImage marioJump;
    private BufferedImage punch;
    private BufferedImage peace;
    private BufferedImage shrug;
    private BufferedImage sprinkler;
    private BufferedImage superman;
    private BufferedImage currentPose;

    private int x;
    private int y;


    public Boss() {
        try {
            // insert proper images
            sprite = ImageIO.read(new File("src/millerSprites/idol.png"));
            shockedSprite = ImageIO.read(new File("src/millerSprites/shocked.png"));
            ballerina = ImageIO.read(new File("src//millerSprites/ballerina.png"));
            cross1 = ImageIO.read(new File("src/millerSprites/cross1.png"));
            cross2 = ImageIO.read(new File("src/millerSprites/cross2.png"));
            heart = ImageIO.read(new File("src/millerSprites/heart.png"));
            marioJump = ImageIO.read(new File("src/millerSprites/mariojump.png"));
            peace = ImageIO.read(new File("src/millerSprites/peace.png"));
            punch = ImageIO.read(new File("src/millerSprites/punch.png"));
            shrug = ImageIO.read(new File("src/millerSprites/shrug.png"));
            sprinkler  = ImageIO.read(new File("src/millerSprites/sprinkler.png"));
            superman  = ImageIO.read(new File("src/millerSprites/superman.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.x = x;
        this.y = y;
    }

    public Rectangle bossRect(BufferedImage img) {
        int imageHeight = img.getHeight();
        int imageWidth = img.getWidth();
        Rectangle rect = new Rectangle(x, (int) y, imageWidth, imageHeight);
        return rect;
    }

    private void chooseNextPose(){
        int pose = (int) (Math.random() *10)+1;
        if (pose == 1){
            currentPose = ballerina;
        }
        if (pose == 2){
            currentPose = cross1;
        }
        if (pose == 2){
            currentPose = cross2;
        }
        if (pose == 2){
            currentPose = heart;
        }
        if (pose == 2){
            currentPose = marioJump;
        }
        if (pose == 6){
            currentPose = superman;
        }
        if (pose == 7){
            currentPose = sprinkler;
        }
        if (pose == 8){
            currentPose = punch;
        }
        if (pose == 9) {
            currentPose = peace;
        }
        if (pose == 10) {
            currentPose = shrug;
        }
    }


}
