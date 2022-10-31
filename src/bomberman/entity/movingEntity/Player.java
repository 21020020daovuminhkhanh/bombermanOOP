package bomberman.entity.movingEntity;

import bomberman.GamePanel;
import bomberman.KeyInput;
import bomberman.entity.Bomb;
import bomberman.entity.tile.item.Item;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends MovingEntity {
    public BufferedImage[] up = new BufferedImage[3];
    public BufferedImage[] down = new BufferedImage[3];
    public BufferedImage[] left = new BufferedImage[3];
    public BufferedImage[] right = new BufferedImage[3];
    public BufferedImage[] dead = new BufferedImage[3];
    public boolean isMoving;
    public int playerAnimation = 0;
    public int bombAmount;
    public int flameLength;
    public boolean reset;

    //Board board;

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
            dead[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/player_dead1.png"));
            dead[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/player_dead2.png"));
            dead[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/player_dead3.png"));
            for (int i = 0; i < 3; i++) {
                up[i] = up[i].getSubimage(0, 0, 12, 16);
                down[i] = down[i].getSubimage(0, 0, 12, 16);
                right[i] = right[i].getSubimage(0, 0, 12, 16);
                left[i] = left[i].getSubimage(0, 0, 12, 16);
                dead[i] = dead[i].getSubimage(0, 0,12, 16);
                removeColor(up[i]);
                removeColor(down[i]);
                removeColor(left[i]);
                removeColor(right[i]);
                removeColor(dead[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStartPosition() {
        bombAmount = 1;
        flameLength = 1;
        speed = 3;
        direction = "down";
        isMoving = false;
        isLiving = true;
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
            createBombAt(tileSize * getPlayerTileX(), tileSize * getPlayerTileY());
        }

        if (!isLiving) {
            frame++;
            if (frame > 8) {
                frame = 0;
                playerAnimation++;
                if (playerAnimation > 2) {
                    reset = true;
                    playerAnimation = 2;
                }
            }
            return;
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
        if (!isLiving) {
            image = dead[playerAnimation];
        }

        g2.drawImage(image, screenX, screenY, image.getWidth() * GamePanel.scale, image.getHeight() * GamePanel.scale, null);
    }

    public void createBombAt(int x, int y) {
        if (gamePanel.board.getBombAt(x, y) == null && gamePanel.board.bombs.size() < bombAmount) {
            Bomb bomb = new Bomb(gamePanel);
            bomb.setCoordinate(x, y);
            gamePanel.board.addBomb(bomb);
        }
    }

    public void setCoordinate(int x, int y) {
        this.mapX = x;
        this.mapY = y;
    }

    public int getPlayerTileX() {
        return (mapX + tileSize / 2) / tileSize;
    }
    public int getPlayerTileY() {
        return (mapY + tileSize / 2) / tileSize;
    }

    public void kill() {
        if (isLiving) gamePanel.playSoundEffect(2);
        isLiving = false;
    }

    public boolean collideWithEnemy(MovingEntity e) {
        int leftPlayerMapX = mapX + hitbox.x;
        int rightPlayerMapX = mapX + hitbox.x + hitbox.width;
        int topPlayerMapY = mapY + hitbox.y;
        int bottomPlayerMapY = mapY + hitbox.y + hitbox.height;

        int leftEnemyMapX = e.mapX + e.hitbox.x;
        int rightEnemyMapX = e.mapX + e.hitbox.x + e.hitbox.width;
        int topEnemyMapY = e.mapY + e.hitbox.y;
        int bottomEnemyMapY = e.mapY + e.hitbox.y + e.hitbox.height;

        if (leftPlayerMapX <= rightEnemyMapX && rightPlayerMapX >= leftEnemyMapX &&
            topPlayerMapY <= bottomEnemyMapY && bottomPlayerMapY >= topEnemyMapY) return true;
        else return false;
    }

    public boolean collideWithExplosion(Bomb b) {
        int leftPlayerMapX = mapX + hitbox.x;
        int rightPlayerMapX = mapX + hitbox.x + hitbox.width;
        int topPlayerMapY = mapY + hitbox.y;
        int bottomPlayerMapY = mapY + hitbox.y + hitbox.height;

        int leftVerticalMapX = b.mapX;
        int rightVerticalMapX = b.mapX + tileSize;
        int topVerticalMapY = b.mapY - b.explosionFlame.topFlameLength * tileSize;
        int bottomVerticalMapY = b.mapY + (b.explosionFlame.downFlameLength + 1) * tileSize;

        int leftHorizontalMapX = b.mapX - b.explosionFlame.leftFlameLength * tileSize;
        int rightHorizontalMapX = b.mapX + (b.explosionFlame.rightFlameLength + 1) * tileSize;
        int topHorizontalMapY = b.mapY;
        int bottomHorizontalMapY = b.mapY + tileSize;

        if (leftPlayerMapX <= rightVerticalMapX && rightPlayerMapX >= leftVerticalMapX &&
                topPlayerMapY <= bottomVerticalMapY && bottomPlayerMapY >= topVerticalMapY) return true;
        if (leftPlayerMapX <= rightHorizontalMapX && rightPlayerMapX >= leftHorizontalMapX &&
                topPlayerMapY <= bottomHorizontalMapY && bottomPlayerMapY >= topHorizontalMapY) return true;
        return false;
    }

    public boolean collideWithItem(Item item) {
        int leftPlayerMapX = mapX + hitbox.x;
        int rightPlayerMapX = mapX + hitbox.x + hitbox.width;
        int topPlayerMapY = mapY + hitbox.y;
        int bottomPlayerMapY = mapY + hitbox.y + hitbox.height;

        int leftItemMapX = item.mapX;
        int rightItemMapX = item.mapX + tileSize;
        int topItemMapY = item.mapY;
        int bottomItemMapY = item.mapY + tileSize;

        if (leftPlayerMapX < rightItemMapX && rightPlayerMapX > leftItemMapX &&
                topPlayerMapY < bottomItemMapY && bottomPlayerMapY > topItemMapY) return true;
        else return false;
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
