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
    public int bombAnimationCycle = 0;
    public int maxBombAnimationCycle = 4;
    int bombAnimation;
    int frame;
    BufferedImage[] bombImage = new BufferedImage[3];
    BufferedImage[] explosion = new BufferedImage[3];

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
            bombImage[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/bomb.png"));
            bombImage[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/bomb_1.png"));
            bombImage[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/bomb_2.png"));
            removeColor(bombImage[0]);
            removeColor(bombImage[1]);
            removeColor(bombImage[2]);
            explosion[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/bomb_exploded.png"));
            explosion[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/bomb_exploded1.png"));
            explosion[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/bomb_exploded2.png"));
            removeColor(explosion[0]);
            removeColor(explosion[1]);
            removeColor(explosion[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = mapX - gamePanel.player.mapX + gamePanel.player.screenX;
        int screenY = mapY - gamePanel.player.mapY + gamePanel.player.screenY;
        if (bombAnimationCycle < maxBombAnimationCycle) {
            g2.drawImage(bombImage[bombAnimation], screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
        else g2.drawImage(explosion[bombAnimation], screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    @Override
    public void update() {
        frame++;
        if (frame > 12) {
            bombAnimation++;
            if (bombAnimation > 2) {
                bombAnimation = 0;
                bombAnimationCycle++;
            }
            frame = 0;
        }
        if (bombAnimationCycle > maxBombAnimationCycle) {
            gamePanel.level.mapTile[bombTileX][bombTileY] = ' ';
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
