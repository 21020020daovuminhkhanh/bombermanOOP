package bomberman.Entity;

import bomberman.GamePanel;
import bomberman.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Portal extends Tile{
    public Portal(GamePanel gp) {
        gamePanel = gp;
        getImage();
    }

    public void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void getImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sprites/portal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }

}
