package bomberman.entity.tile;

import bomberman.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Brick extends Tile{
    BufferedImage[] brickExploded = new BufferedImage[3];
    int brickAnimation;
    int frame;
    Grass g;

    public Brick(GamePanel gp) {
        gamePanel = gp;
        g = new Grass(gp);
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
            for (int i = 0; i < 3; i++) {
                removeColor(brickExploded[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        frame++;
        if (frame > 12) {
            frame = 0;
            brickAnimation++;
            if (brickAnimation > 2) {
                gamePanel.level.mapTile[mapX / tileSize][mapY / tileSize] = ' ';
            }
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = mapX - gamePanel.player.mapX + gamePanel.player.screenX;
        int screenY = mapY - gamePanel.player.mapY + gamePanel.player.screenY;
        if (frame > 0) {
            g2.drawImage(brickExploded[brickAnimation], screenX, screenY, tileSize, tileSize, null);
        } else g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);
    }

    public void removeColor(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (image.getRGB(i, j) == Color.MAGENTA.getRGB()) {
                    image.setRGB(i, j, g.image.getRGB(i,j));
                }
            }
        }
    }
}
