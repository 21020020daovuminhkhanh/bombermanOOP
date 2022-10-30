package bomberman.entity;

import bomberman.GamePanel;
import bomberman.entity.tile.Brick;
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
    public ExplosionFlame explosionFlame;
    BufferedImage[] bombImage = new BufferedImage[3];
    BufferedImage[] explosion = new BufferedImage[3];
    Grass g;
    public Bomb(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        g = new Grass(gamePanel);
        explosionFlame = new ExplosionFlame(gamePanel);
        bombTileX = (gamePanel.board.player.mapX + tileSize / 2) / tileSize;
        bombTileY = (gamePanel.board.player.mapY + tileSize / 2) / tileSize;
        explosionFlame.setCoordinate(bombTileX * tileSize, bombTileY * tileSize);
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

            explosion[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/bomb_exploded.png"));
            explosion[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/bomb_exploded1.png"));
            explosion[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/bomb_exploded2.png"));

            for (int i = 0; i <= 2; i++) {
                removeColor(bombImage[i]);
                removeColor(explosion[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = mapX - gamePanel.board.player.mapX + gamePanel.board.player.screenX;
        int screenY = mapY - gamePanel.board.player.mapY + gamePanel.board.player.screenY;
        if (bombAnimationCycle < maxBombAnimationCycle) {
            g2.drawImage(bombImage[bombAnimation], screenX, screenY, tileSize, tileSize, null);
        }
        else {
            g2.drawImage(explosion[bombAnimation], screenX, screenY, tileSize, tileSize, null);
            explosionFlame.draw(g2);
        }
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
        if (bombAnimationCycle == maxBombAnimationCycle) {
            explosionFlame.update();
        }
        for (int i = 0; i < 4; i++) {
            if (explosionFlame.isSomeBrickExploded[i]) {
                explosionFlame.brick[i].update();
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
