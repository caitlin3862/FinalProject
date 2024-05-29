import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerMoves {
    private int score;
    private int x;
    private int y;

    public PlayerMoves(int x, int y) {
        this.x = x;
        this.y = y;
        score = 0;
    }

    public int getScore() {
        return score;
    }


    public Rectangle spriteRect(BufferedImage img) {
        int imageHeight = img.getHeight();
        int imageWidth = img.getWidth();
        Rectangle rect = new Rectangle(x, (int) y, imageWidth, imageHeight);
        return rect;
    }


}
