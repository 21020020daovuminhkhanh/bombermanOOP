package bomberman.entity;

import bomberman.GamePanel;
import bomberman.entity.tile.Grass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bomb extends Entity {
    boolean exploded;
    public int bombTileX;
    public int bombTileY;
    public int countToExplode;
    public int timeToExplode = 4;
    int bombAnimation;
    int frame;
    BufferedImage[] image = new BufferedImage[3];

    Grass g;
    public Bomb(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        g = new Grass(gamePanel);
        bombTileX = (gamePanel.player.mapX + gamePanel.tileSize / 2) / gamePanel.tileSize;
        bombTileY = (gamePanel.player.mapY + gamePanel.tileSize / 2) / gamePanel.tileSize;
        getImage();
    }

    public void setCoordinate(int x, int y) {
        mapX = x;
        mapY = y;
    }

    public void getImage() {
        try {
            image[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/bomb.png"));
            image[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/bomb_1.png"));
            image[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/bomb_2.png"));
            removeColor(image[0]);
            removeColor(image[1]);
            removeColor(image[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = mapX - gamePanel.player.mapX + gamePanel.player.screenX;
        int screenY = mapY - gamePanel.player.mapY + gamePanel.player.screenY;
        g2.drawImage(image[bombAnimation], screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    @Override
    public void update() {
        if (this != null) {
            countToExplode++;
            if (countToExplode > 120) {
                gamePanel.level.mapTile[bombTileX][bombTileY] = ' ';
            }
            frame++;
            if (frame > 12) {
                bombAnimation++;
                if (bombAnimation > 2) bombAnimation = 0;
                frame = 0;
            }

        }
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
