package bomberman.Entity;

import bomberman.GamePanel;
import bomberman.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Grass extends Tile{
    public Grass(GamePanel gp) {
        gamePanel = gp;
        getImage();
    }

    public void getImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sprites/grass.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
