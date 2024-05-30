import java.awt.image.BufferedImage;

public class Move {
    private BufferedImage move;
    private int x;
    private int y;

    public Move(BufferedImage img, int x, int y){
        this.x = x;
        this.y = y;
        move = img;
    }

    public BufferedImage getMove(){
        return move;
    }

}
