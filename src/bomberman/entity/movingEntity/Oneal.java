package bomberman.entity.movingEntity;

import bomberman.GamePanel;
import bomberman.entity.Bomb;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Oneal extends Enemy {
    BufferedImage[] onealRightImage = new BufferedImage[3];
    BufferedImage[] onealLeftImage = new BufferedImage[3];
    BufferedImage[] onealDead = new BufferedImage[4];
    public int onealAnimation = 0;
    public int onealDeadAnimation = 0;

    public Oneal(GamePanel gp) {
        gamePanel = gp;
        speed = 3;
        direction = "right";
        hitbox = new Rectangle(3, 3, 42, 42);
        getImage();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nextDirection() {
        int horizontalDistance = Math.abs(gamePanel.board.player.mapX - mapX);
        int verticalDistance = Math.abs(gamePanel.board.player.mapY - mapY);
        if (horizontalDistance < verticalDistance) {
            if (horizontalDistance <= 6) {
                if (gamePanel.board.player.mapY - mapY > 0) direction = "down";
                else if (gamePanel.board.player.mapY - mapY < 0) direction = "up";
            }
            else if (gamePanel.board.player.mapX - mapX > 0) direction = "right";
            else if (gamePanel.board.player.mapX - mapX < 0) direction = "left";
        } else {
            if (verticalDistance <= 6) {
                if (gamePanel.board.player.mapX - mapX > 0) direction = "right";
                else if (gamePanel.board.player.mapX - mapX < 0) direction = "left";
            }
            else if (gamePanel.board.player.mapY - mapY > 0) direction = "down";
            else if (gamePanel.board.player.mapY - mapY < 0) direction = "up";
        }
    }

    public void update() {
        if (!isLiving) {
            frame++;
            if (frame > 12) {
                frame = 0;
                onealDeadAnimation++;
            }
            return;
        } else {
            frame++;
            if (frame > 8) {
                frame = 0;
                onealAnimation++;
                if (onealAnimation > 2) onealAnimation = 0;
            }
        }

        isCollide = false;
        gamePanel.checkCollision.checkTile(this);
        if (mapX % tileSize <= 3 || mapY % tileSize <= 3 || isCollide) nextDirection();
        if (!isCollide) {
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

    @Override
    public int getAnimationIndex() {
        return onealDeadAnimation;
    }
}
