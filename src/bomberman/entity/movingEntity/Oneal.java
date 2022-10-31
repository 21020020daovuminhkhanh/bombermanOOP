package bomberman.entity.movingEntity;

import bomberman.GamePanel;
import bomberman.entity.Bomb;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Oneal extends MovingEntity{
    BufferedImage[] onealRightImage = new BufferedImage[3];
    BufferedImage[] onealLeftImage = new BufferedImage[3];
    BufferedImage[] onealDead = new BufferedImage[4];
    public int onealAnimation = 0;
    public int onealDeadAnimation = 0;
    Random random = new Random();
    public Oneal(GamePanel gp) {
        gamePanel = gp;
        speed = 3;
        direction = "right";
        hitbox = new Rectangle(3, 3, 42, 42);
        getImage();
    }

    public void setCoordinate(int x, int y) {
        this.mapX = x;
        this.mapY = y;
    }

    public void getImage() {
        try {
            onealRightImage[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/oneal_right1.png"));
            onealRightImage[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/oneal_right2.png"));
            onealRightImage[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/oneal_right3.png"));
            onealLeftImage[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/oneal_left1.png"));
            onealLeftImage[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/oneal_left2.png"));
            onealLeftImage[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/oneal_left3.png"));
            onealDead[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/oneal_dead.png"));
            onealDead[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/mob_dead1.png"));
            onealDead[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/mob_dead2.png"));
            onealDead[3] = ImageIO.read(getClass().getResourceAsStream("/sprites/mob_dead3.png"));
            for (int i = 0; i < 3; i++) {
                removeColor(onealRightImage[i]);
                removeColor(onealLeftImage[i]);
                removeColor(onealDead[i]);
            }
            removeColor(onealDead[3]);
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
                onealDeadAnimation++;
                if (onealDeadAnimation > 3) {
                    onealDeadAnimation = 3;
                }
            }
            return;
        }

        frame++;
        if (frame > 8) {
            frame = 0;
            onealAnimation++;
            if (onealAnimation > 2) onealAnimation = 0;
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



    public void draw(Graphics2D g2) {
        BufferedImage image = onealRightImage[onealAnimation];
        int screenX = mapX - gamePanel.board.player.mapX + gamePanel.board.player.screenX;
        int screenY = mapY - gamePanel.board.player.mapY + gamePanel.board.player.screenY;
        if (!isLiving) image = onealDead[onealDeadAnimation];
        else if (direction.equals("right")) image = onealRightImage[onealAnimation];
        else if (direction.equals("left")) image = onealLeftImage[onealAnimation];
        g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);
    }

    public boolean collideWithExplosion(Bomb b) {
        int leftEnemyMapX = mapX + hitbox.x;
        int rightEnemyMapX = mapX + hitbox.x + hitbox.width;
        int topEnemyMapY = mapY + hitbox.y;
        int bottomEnemyMapY = mapY + hitbox.y + hitbox.height;

        int leftVerticalMapX = b.mapX;
        int rightVerticalMapX = b.mapX + tileSize;
        int topVerticalMapY = b.mapY - b.explosionFlame.topFlameLength * tileSize;
        int bottomVerticalMapY = b.mapY + (b.explosionFlame.downFlameLength + 1) * tileSize;

        int leftHorizontalMapX = b.mapX - b.explosionFlame.leftFlameLength * tileSize;
        int rightHorizontalMapX = b.mapX + (b.explosionFlame.rightFlameLength + 1) * tileSize;
        int topHorizontalMapY = b.mapY;
        int bottomHorizontalMapY = b.mapY + tileSize;

        if (leftEnemyMapX <= rightVerticalMapX && rightEnemyMapX >= leftVerticalMapX &&
                topEnemyMapY <= bottomVerticalMapY && bottomEnemyMapY >= topVerticalMapY) return true;
        if (leftEnemyMapX <= rightHorizontalMapX && rightEnemyMapX >= leftHorizontalMapX &&
                topEnemyMapY <= bottomHorizontalMapY && bottomEnemyMapY >= topHorizontalMapY) return true;
        return false;
    }

    @Override
    public int getAnimationIndex() {
        return onealDeadAnimation;
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
