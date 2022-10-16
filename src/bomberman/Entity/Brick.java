package bomberman.Entity;

import bomberman.GamePanel;
import bomberman.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Brick extends Tile{
    public Brick(GamePanel gp) {
        gamePanel = gp;
        getImage();
    }

    public void getImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sprites/brick.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, 0, 48, gamePanel.tileSize, gamePanel.tileSize, null);
    }

}
