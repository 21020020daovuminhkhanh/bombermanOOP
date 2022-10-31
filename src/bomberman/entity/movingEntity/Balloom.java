package bomberman.entity.movingEntity;

import bomberman.GamePanel;
import bomberman.entity.Bomb;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Balloom extends MovingEntity{
    BufferedImage[] balloomRightImage = new BufferedImage[3];
    BufferedImage[] balloomLeftImage = new BufferedImage[3];
    BufferedImage[] balloomDead = new BufferedImage[4];
    public int balloomAnimation = 0;
    public int balloomDeadAnimation = 0;
    Random random = new Random();
    public Balloom(GamePanel gp) {
        gamePanel = gp;
        speed = 2;
        hitbox = new Rectangle(3, 3, 42, 42);
        direction = "right";
        getImage();
    }

    public void setCoordinate(int x, int y) {
        this.mapX = x;
        this.mapY = y;
    }

    public void getImage() {
        try {
            balloomRightImage[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/balloom_right1.png"));
            balloomRightImage[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/balloom_right2.png"));
            balloomRightImage[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/balloom_right3.png"));
            balloomLeftImage[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/balloom_left1.png"));
            balloomLeftImage[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/balloom_left2.png"));
            balloomLeftImage[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/balloom_left3.png"));
            balloomDead[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/balloom_dead.png"));
            balloomDead[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/mob_dead1.png"));
            balloomDead[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/mob_dead2.png"));
            balloomDead[3] = ImageIO.read(getClass().getResourceAsStream("/sprites/mob_dead3.png"));
            for (int i = 0; i < 3; i++) {
                removeColor(balloomRightImage[i]);
                removeColor(balloomLeftImage[i]);
                removeColor(balloomDead[i]);
            }
            removeColor(balloomDead[3]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nextDirection() {
        int r = random.nextInt(4);
        switch (r) {
            case 0:
                direction = "up";
                break;
            case 1:
                direction = "down";
                break;
            case 2:
                direction = "left";
                break;
            case 3:
                direction = "right";
                break;
        };
    }

    public void update() {
        if (!isLiving) {
            frame++;
            if (frame > 12) {
                frame = 0;
                balloomDeadAnimation++;
                if (balloomDeadAnimation > 3) {
                    balloomDeadAnimation = 3;
                }
            }
            return;
        }

        frame++;
        if (frame > 8) {
            frame = 0;
            balloomAnimation++;
            if (balloomAnimation > 2) balloomAnimation = 0;
        }

        isCollide = false;
        gamePanel.checkCollision.checkTile(this);
        if (isCollide) nextDirection();
        else {
            if (direction.equals("up")) mapY -= speed;
            if (direction.equals("down")) mapY += speed;
            if (direction.equals("left")) mapX -= speed;
            if (direction.equals("right")) mapX += speed;
        }
    }

    @Override
    public int getAnimationIndex() {
        return balloomDeadAnimation;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = balloomRightImage[balloomAnimation];
        int screenX = mapX - gamePanel.board.player.mapX + gamePanel.board.player.screenX;
        int screenY = mapY - gamePanel.board.player.mapY + gamePanel.board.player.screenY;
        if (!isLiving) image = balloomDead[balloomDeadAnimation];
        else if (direction.equals("right")) {
            image = balloomRightImage[balloomAnimation];
        } else if (direction.equals("left")) {
            image = balloomLeftImage[balloomAnimation];
        }
        g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);
    }

    public boolean collideWithExplosion(Bomb b) {
        int leftEnemyMapX = mapX + hitbox.x;
        int rightEnemyMapX = mapX + hitbox.x + hitbox.width;
        int topEnemyMapY = mapY + hitbox.y;
        int bottomEnemyMapY = mapY + hitbox.y + hitbox.height;

        int leftVerticalMapX = b.mapX;
        int rightVerticalMapX = b.mapX + tileSize;
        int topVerticalMapY = b.mapY - gamePanel.board.player.flameLength * tileSize;
        int bottomVerticalMapY = b.mapY + (gamePanel.board.player.flameLength + 1) * tileSize;

        int leftHorizontalMapX = b.mapX - gamePanel.board.player.flameLength * tileSize;
        int rightHorizontalMapX = b.mapX + (gamePanel.board.player.flameLength + 1) * tileSize;
        int topHorizontalMapY = b.mapY;
        int bottomHorizontalMapY = b.mapY + tileSize;

        if (leftEnemyMapX <= rightVerticalMapX && rightEnemyMapX >= leftVerticalMapX &&
                topEnemyMapY <= bottomVerticalMapY && bottomEnemyMapY >= topVerticalMapY) return true;
        if (leftEnemyMapX <= rightHorizontalMapX && rightEnemyMapX >= leftHorizontalMapX &&
                topEnemyMapY <= bottomHorizontalMapY && bottomEnemyMapY >= topHorizontalMapY) return true;
        return false;
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
