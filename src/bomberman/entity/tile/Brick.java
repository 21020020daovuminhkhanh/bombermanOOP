package bomberman.entity.tile;

import bomberman.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Brick extends Tile{
    BufferedImage[] brickExploded = new BufferedImage[3];

    public Brick(GamePanel gp) {
        gamePanel = gp;
        getImage();
    }

    public void setCoordinate(int x, int y) {
        this.mapX = x;
        this.mapY = y;
    }

    public void getImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sprites/brick.png"));
            brickExploded[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/brick_exploded.png"));
            brickExploded[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/brick_exploded1.png"));
            brickExploded[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/brick_exploded2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = mapX - gamePanel.player.mapX + gamePanel.player.screenX;
        int screenY = mapY - gamePanel.player.mapY + gamePanel.player.screenY;
        g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);
    }

}
