package bomberman.entity.movingEntity;

import bomberman.GamePanel;
import bomberman.KeyInput;
import bomberman.entity.Bomb;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends MovingEntity {
    public BufferedImage[] up = new BufferedImage[3];
    public BufferedImage[] down = new BufferedImage[3];
    public BufferedImage[] left = new BufferedImage[3];
    public BufferedImage[] right = new BufferedImage[3];
    public boolean isMoving;
    public int playerAnimation = 0;
    public Bomb bomb;

    //Toa do tren man hinh.
    public int screenX;
    public int screenY;

    public Player(GamePanel g, KeyInput k) {
        gamePanel = g;
        keyInput = k;

        hitbox = new Rectangle(6, 21, 24, 24);

        setStartPosition();
        setPlayerImage();
    }

    public void setPlayerImage() {
        try {
            up[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/player_up.png"));
            up[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/player_up_1.png"));
            up[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/player_up_2.png"));
            down[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/player_down.png"));
            down[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/player_down_1.png"));
            down[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/player_down_2.png"));
            left[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/player_left.png"));
            left[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/player_left_1.png"));
            left[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/player_left_2.png"));
            right[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/player_right.png"));
            right[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/player_right_1.png"));
            right[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/player_right_2.png"));
            for (int i = 0; i < 3; i++) {
                up[i] = up[i].getSubimage(0, 0, 12, 16);
                down[i] = down[i].getSubimage(0, 0, 12, 16);
                right[i] = right[i].getSubimage(0, 0, 12, 16);
                left[i] = left[i].getSubimage(0, 0, 12, 16);
                removeColor(up[i]);
                removeColor(down[i]);
                removeColor(left[i]);
                removeColor(right[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStartPosition() {
        mapX = tileSize;
        mapY = tileSize;
        speed = 4;
        direction = "down";
        isMoving = false;
    }

    public void update() {
        if (mapX <= 0) mapX = 0;
        if (mapX >= gamePanel.MAP_WIDTH - 36) mapX = gamePanel.MAP_WIDTH - 36;
        if (mapY <= 0) mapY = 0;
        if (mapY >= gamePanel.MAP_HEIGHT - 48) mapY = gamePanel.MAP_HEIGHT - 48;
        isMoving = false;

        if (keyInput.goUp) {
            isMoving = true;
            direction = "up";
        }
        if (keyInput.goDown) {
            isMoving = true;
            direction = "down";
        }
        if (keyInput.goLeft) {
            isMoving = true;
            direction = "left";
        }
        if (keyInput.goRight) {
            isMoving = true;
            direction = "right";
        }

        isCollide = false;
        gamePanel.checkCollision.checkTile(this);

        if (!isCollide && isMoving) {
            if (direction.equals("up")) mapY -= speed;
            if (direction.equals("down")) mapY += speed;
            if (direction.equals("left")) mapX -= speed;
            if (direction.equals("right")) mapX += speed;
        }

        if (keyInput.putBomb) {
            if (bomb == null) {
                bomb = new Bomb(gamePanel);
                bomb.setCoordinate(bomb.bombTileX * tileSize, bomb.bombTileY * tileSize);
            }
        }
        if (bomb != null) {
            bomb.update();
        }
        if (bomb != null && bomb.bombAnimationCycle > bomb.maxBombAnimationCycle) {
            bomb = null;
        }

        if (isMoving) {
            frame++;
            if (frame > 8) {
                frame = 0;
                playerAnimation++;
                if (playerAnimation > 2) playerAnimation = 0;
            }
        } else playerAnimation = 0;

        screenX = mapX;
        screenY = mapY;
        if (mapX > gamePanel.SCREEN_WIDTH / 2 && mapX < gamePanel.MAP_WIDTH - gamePanel.SCREEN_WIDTH / 2) screenX = gamePanel.SCREEN_WIDTH / 2;
        if (mapX >= gamePanel.MAP_WIDTH - gamePanel.SCREEN_WIDTH / 2) screenX = mapX - gamePanel.SCREEN_WIDTH;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = down[0];

        switch (direction) {
            case "up":
                image = up[playerAnimation];
                break;
            case "down":
                image = down[playerAnimation];
                break;
            case "left":
                image = left[playerAnimation];
                break;
            case "right":
                image = right[playerAnimation];
                break;
        }

        if (bomb != null) {
            bomb.draw(g2);
        }
        g2.drawImage(image, screenX, screenY, image.getWidth() * gamePanel.scale, image.getHeight() * gamePanel.scale, null);
    }

    public void setCoordinate(int x, int y) {
        this.mapX = x;
        this.mapY = y;
    }

    //xoa mau hong thay bang mau cua grass.
    public void removeColor(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (image.getRGB(i, j) == Color.MAGENTA.getRGB()) {
                    image.setRGB(i, j, g.image.getRGB(i, j));
                }
            }
        }
    }

}
