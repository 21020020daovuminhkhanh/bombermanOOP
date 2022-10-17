package bomberman.Entity;

import bomberman.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Oneal extends MovingEntity{
    BufferedImage image;
    public Oneal(GamePanel gp) {
        gamePanel = gp;
        getImage();
    }

    public void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void getImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sprites/oneal_right1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }

}
