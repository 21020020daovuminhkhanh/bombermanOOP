package bomberman.entity.movingEntity;

import bomberman.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Doll extends Enemy {
    BufferedImage[] dollRightImage = new BufferedImage[3];
    BufferedImage[] dollLeftImage = new BufferedImage[3];
    BufferedImage[] dollDead = new BufferedImage[4];
    public int dollAnimation = 0;
    public int dollDeadAnimation = 0;
    public Doll(GamePanel gp) {
        gamePanel = gp;
        speed = 4;
        hitbox = new Rectangle(3, 3, 42, 42);
        direction = "right";
        getImage();
    }

    public void getImage() {
        try {
            dollRightImage[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/doll_right1.png"));
            dollRightImage[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/doll_right2.png"));
            dollRightImage[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/doll_right3.png"));
            dollLeftImage[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/doll_left1.png"));
            dollLeftImage[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/doll_left2.png"));
            dollLeftImage[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/doll_left3.png"));
            dollDead[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/doll_dead.png"));
            dollDead[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/mob_dead1.png"));
            dollDead[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/mob_dead2.png"));
            dollDead[3] = ImageIO.read(getClass().getResourceAsStream("/sprites/mob_dead3.png"));
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
                dollDeadAnimation++;
            }
            return;
        } else {
            frame++;
            if (frame > 8) {
                frame = 0;
                dollAnimation++;
                if (dollAnimation > 2) dollAnimation = 0;
            }
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
        return dollDeadAnimation;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = dollRightImage[dollAnimation];
        int screenX = mapX - gamePanel.board.player.mapX + gamePanel.board.player.screenX;
        int screenY = mapY - gamePanel.board.player.mapY + gamePanel.board.player.screenY;
        if (!isLiving) image = dollDead[dollDeadAnimation];
        else if (direction.equals("right")) {
            image = dollRightImage[dollAnimation];
        } else if (direction.equals("left")) {
            image = dollLeftImage[dollAnimation];
        }
        g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);
    }
}
