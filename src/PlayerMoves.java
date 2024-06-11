import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerMoves {
    private ArrayList<Integer> moves;
    private int score;


    public PlayerMoves() {
        score = 0;
        moves = new ArrayList<>();
    }

    public ArrayList<Integer> getPlayerMoves(){
        return moves;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int x) {
        score += x;
    }

    public void addToCombo(int m){
        moves.add(m);
    }

    public void clearCombo() { moves = new ArrayList<>(); }

    public Rectangle spriteRect(BufferedImage img) {
        int imageHeight = img.getHeight();
        int imageWidth = img.getWidth();
        Rectangle rect = new Rectangle(img.getWidth(), img.getHeight(), imageWidth, imageHeight);
        return rect;
    }


}
