import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Boss {
    private BufferedImage sprite;
    private BufferedImage angrySprite;
    private BufferedImage kick;
    private BufferedImage wave;
    private BufferedImage twist;
    private BufferedImage crissCross1;
    private BufferedImage crissCross2;
    private BufferedImage handsUp;
    private BufferedImage punch;

    private int x;
    private int y;


    public Boss() {
        try {
            // insert proper images
            sprite = ImageIO.read(new File(""));
            angrySprite = ImageIO.read(new File(""));
            kick = ImageIO.read(new File(""));
            wave = ImageIO.read(new File(""));
            twist = ImageIO.read(new File(""));
            crissCross1 = ImageIO.read(new File(""));
            crissCross2 = ImageIO.read(new File(""));
            handsUp = ImageIO.read(new File(""));
           punch = ImageIO.read(new File(""));
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
}
