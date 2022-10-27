package bomberman.entity;

import bomberman.GamePanel;
import bomberman.entity.tile.Brick;
import bomberman.entity.tile.Grass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ExplosionFlame extends Entity {
    BufferedImage[] topFlame = new BufferedImage[3];
    BufferedImage[] downFlame = new BufferedImage[3];
    BufferedImage[] rightFlame = new BufferedImage[3];
    BufferedImage[] leftFlame = new BufferedImage[3];
    BufferedImage[] horizontalFlame = new BufferedImage[3];
    BufferedImage[] verticalFlame = new BufferedImage[3];

    public int explosionLength = 1;
    int frame;
    int flameAnimation;

    boolean[] isSomeBrickExploded = new boolean[4];
    Brick[] brick = new Brick[4];
    Grass g;
    public ExplosionFlame(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        g = new Grass(gamePanel);
        for (int i = 0; i < 4; i++) brick[i] = new Brick(gamePanel);
        getImage();
    }

    public void getImage() {
        try {
            topFlame[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/explosion_vertical_top_last.png"));
            topFlame[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/explosion_vertical_top_last1.png"));
            topFlame[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/explosion_vertical_top_last2.png"));
            downFlame[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/explosion_vertical_down_last.png"));
            downFlame[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/explosion_vertical_down_last1.png"));
            downFlame[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/explosion_vertical_down_last2.png"));
            rightFlame[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/explosion_horizontal_right_last.png"));
            rightFlame[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/explosion_horizontal_right_last1.png"));
            rightFlame[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/explosion_horizontal_right_last2.png"));
            leftFlame[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/explosion_horizontal_left_last.png"));
            leftFlame[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/explosion_horizontal_left_last1.png"));
            leftFlame[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/explosion_horizontal_left_last2.png"));
            verticalFlame[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/explosion_vertical.png"));
            verticalFlame[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/explosion_vertical1.png"));
            verticalFlame[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/explosion_vertical2.png"));
            horizontalFlame[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/explosion_horizontal.png"));
            horizontalFlame[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/explosion_horizontal1.png"));
            horizontalFlame[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/explosion_horizontal2.png"));

            for (int i = 0; i < 3; i++) {
                removeColor(topFlame[i]);
                removeColor(downFlame[i]);
                removeColor(rightFlame[i]);
                removeColor(leftFlame[i]);
                removeColor(horizontalFlame[i]);
                removeColor(verticalFlame[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCoordinate(int x, int y) {
        mapX = x;
        mapY = y;
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < 4; i++) isSomeBrickExploded[i] = false;
        int screenX = mapX - gamePanel.player.mapX + gamePanel.player.screenX;
        int screenY = mapY - gamePanel.player.mapY + gamePanel.player.screenY;
        for (int i = 1; i <= explosionLength; i++) {
            if (gamePanel.level.mapTile[(mapX + i * tileSize) / tileSize][mapY / tileSize] == ' ') {
                if (i != explosionLength) {
                    g2.drawImage(horizontalFlame[flameAnimation], screenX + i * tileSize, screenY, tileSize, tileSize, null);
                } else g2.drawImage(rightFlame[flameAnimation], screenX + i * tileSize, screenY, tileSize, tileSize, null);
            } else if (gamePanel.level.mapTile[(mapX + i * tileSize) / tileSize][mapY / tileSize] == '*') {
                isSomeBrickExploded[0] = true;
                brick[0].setCoordinate(mapX + i * tileSize, mapY);
                brick[0].draw(g2);
                break;
            } else break;
        }

        for (int i = 1; i <= explosionLength; i++) {
            if (gamePanel.level.mapTile[(mapX - i * tileSize) / tileSize][mapY / tileSize] == ' ') {
                if (i != explosionLength) {
                    g2.drawImage(horizontalFlame[flameAnimation], screenX - i * tileSize, screenY, tileSize, tileSize, null);
                } else g2.drawImage(leftFlame[flameAnimation], screenX - i * tileSize, screenY, tileSize, tileSize, null);
            } else if (gamePanel.level.mapTile[(mapX - i * tileSize) / tileSize][mapY / tileSize] == '*') {
                isSomeBrickExploded[1] = true;
                brick[1].setCoordinate(mapX - i * tileSize, mapY);
                brick[1].draw(g2);
                break;
            } else break;
        }

        for (int i = 1; i <= explosionLength; i++) {
            if (gamePanel.level.mapTile[mapX / tileSize][(mapY + i * tileSize)/ tileSize] == ' ') {
                if (i != explosionLength) {
                    g2.drawImage(verticalFlame[flameAnimation], screenX, screenY + i * tileSize, tileSize, tileSize, null);
                } else g2.drawImage(downFlame[flameAnimation], screenX, screenY + i * tileSize, tileSize, tileSize, null);
            } else if (gamePanel.level.mapTile[mapX / tileSize][(mapY + i * tileSize) / tileSize] == '*') {
                isSomeBrickExploded[2] = true;
                brick[2].setCoordinate(mapX, mapY + i * tileSize);
                brick[2].draw(g2);
                break;
            } else break;
        }

        for (int i = 1; i <= explosionLength; i++) {
            if (gamePanel.level.mapTile[mapX / tileSize][(mapY - i * tileSize)/ tileSize] == ' ') {
                if (i != explosionLength) {
                    g2.drawImage(verticalFlame[flameAnimation], screenX, screenY - i * tileSize, tileSize, tileSize, null);
                } else g2.drawImage(topFlame[flameAnimation], screenX, screenY - i * tileSize, tileSize, tileSize, null);
            } else if (gamePanel.level.mapTile[mapX / tileSize][(mapY - i * tileSize) / tileSize] == '*') {
                isSomeBrickExploded[3] = true;
                brick[3].setCoordinate(mapX, mapY - i * tileSize);
                brick[3].draw(g2);
                break;
            } else break;
        }
    }

    @Override
    public void update() {
        frame++;
        if (frame > 12) {
            flameAnimation++;
            if (flameAnimation > 2) {
                flameAnimation = 0;
            }
            frame = 0;
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
