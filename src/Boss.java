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
    private BufferedImage[] poses = new BufferedImage[12];

    private int x;
    private int y;


    public Boss() {
        try {
            // insert proper images
            poses[0] = ImageIO.read(new File("src/millerSprites/idol.png"));
            poses[1] = ImageIO.read(new File("src/millerSprites/shocked.png"));
            poses[2] = ImageIO.read(new File("src/millerSprites/ballerina.png"));
            poses[3] = ImageIO.read(new File("src/millerSprites/cross1.png"));
            poses[4] = ImageIO.read(new File("src/millerSprites/cross2.png"));
            poses[5] = ImageIO.read(new File("src/millerSprites/heart.png"));
            poses[6] = ImageIO.read(new File("src/millerSprites/mariojump.png"));
            poses[7] = ImageIO.read(new File("src/millerSprites/punch.png"));
            poses[8] = ImageIO.read(new File("src/millerSprites/peace.png"));
            poses[9] = ImageIO.read(new File("src/millerSprites/shrug.png"));
            poses[10] = ImageIO.read(new File("src/millerSprites/sprinkler.png"));
            poses[11] = ImageIO.read(new File("src/millerSprites/superman.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        currentPose = sprite;
    }

    public BufferedImage getCurrentPose(){
        return currentPose;
    }

    public void setCurrentPose(BufferedImage i) {
        currentPose = i;
    }

    public Rectangle bossRect(BufferedImage img) {
        int imageHeight = img.getHeight();
        int imageWidth = img.getWidth();
        Rectangle rect = new Rectangle(x, (int) y, imageWidth, imageHeight);
        return rect;
    }

    public void chooseNextPose() {
        int pose = (int) (Math.random() * poses.length);
        currentPose = poses[pose];
    }


}
