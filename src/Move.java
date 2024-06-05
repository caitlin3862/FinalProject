import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Move {
    private BufferedImage move;
    private int x;
    private int y;

    public Move(BufferedImage img, int x, int y){
        this.x = x;
        this.y = y;
        move = img;
        try {
            arms = ImageIO.read(new File("src/studentSprites/arms.png"));
            caitlin = ImageIO.read(new File("src/studentSprites/caitlin.png"));
            car = ImageIO.read(new File("src/studentSprites/car.png"));
            disco = ImageIO.read(new File("src/studentSprites/disco.png"));
            duo = ImageIO.read(new File("src/studentSprites/duo.png"));
            gangnam = ImageIO.read(new File("src/studentSprites/gangnam.png"));
            gun = ImageIO.read(new File("src/studentSprites/gun.png"));
            handHip = ImageIO.read(new File("src/studentSprites/handHip.png"));
            hovering = ImageIO.read(new File("src/studentSprites/hovering.png"));
            kick = ImageIO.read(new File("src/studentSprites/kick.png"));
            kristen = ImageIO.read(new File("src/studentSprites/kristen.png"));
            maracas = ImageIO.read(new File("src/studentSprites/maracas.png"));
            mj = ImageIO.read(new File("src/studentSprites/mj.png"));
            point = ImageIO.read(new File("src/studentSprites/point.png"));
            punch = ImageIO.read(new File("src/studentSprites/punch.png"));
            slide = ImageIO.read(new File("src/studentSprites/slide.png"));
            spiderman = ImageIO.read(new File("src/studentSprites/spiderman.png"));
            webshooter = ImageIO.read(new File("src/studentSprites/webshooter.png"));
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public BufferedImage getMove(){
        return move;
    }

}
