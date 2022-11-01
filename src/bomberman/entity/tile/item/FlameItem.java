package bomberman.entity.tile.item;

import bomberman.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class FlameItem extends Item {
    public FlameItem(GamePanel gp) {
        gamePanel = gp;
        getImage();
    }

    public void getImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sprites/powerup_flames.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = mapX - gamePanel.board.player.mapX + gamePanel.board.player.screenX;
        int screenY = mapY - gamePanel.board.player.mapY + gamePanel.board.player.screenY;
        g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);
    }
}
