import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Move {
    private BufferedImage move;
    private int x;
    private int y;
    private BufferedImage[] images = new BufferedImage[19];



    public Move(BufferedImage img, int x, int y){
        this.x = x;
        this.y = y;
        move = img;
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
            images[12] = ImageIO.read(new File("src/studentSprites/mj.png"));
            images[13] = ImageIO.read(new File("src/studentSprites/point.png"));
            images[14] = ImageIO.read(new File("src/studentSprites/punch.png"));
            images[15] = ImageIO.read(new File("src/studentSprites/slide.png"));
            images[16] = ImageIO.read(new File("src/studentSprites/spiderman.png"));
            images[17] = ImageIO.read(new File("src/studentSprites/webshooter.png"));
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public BufferedImage getMove(){
        return move;
    }

    public BufferedImage[] getImages() {
        return images;
    }

}
