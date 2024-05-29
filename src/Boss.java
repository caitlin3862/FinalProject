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
    private BufferedImage crissCross;
    private BufferedImage handsUp;
    private BufferedImage punch;

    private int x;
    private int y;


    public Boss(String img, String angryImg) {
        try {
            sprite = ImageIO.read(new File(img));
            angrySprite = ImageIO.read(new File(angryImg));
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
