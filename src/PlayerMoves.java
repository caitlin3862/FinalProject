import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerMoves {
    private ArrayList<Move> moves;
    private int score;

    public PlayerMoves() {
        score = 0;
        moves = new ArrayList<>();
    }

    public ArrayList<Move> getPlayerMoves(){
        return moves;
    }

    public int getScore() {
        return score;
    }

    public void addToCombo(Move m){
        moves.add(m);
    }

    public Rectangle spriteRect(BufferedImage img) {
        int imageHeight = img.getHeight();
        int imageWidth = img.getWidth();
        Rectangle rect = new Rectangle(img.getWidth(), img.getHeight(), imageWidth, imageHeight);
        return rect;
    }


}
