package bomberman.entity.movingEntity;

import bomberman.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Minvo extends Enemy {
    BufferedImage[] minvoRightImage = new BufferedImage[3];
    BufferedImage[] minvoLeftImage = new BufferedImage[3];
    BufferedImage[] minvoDead = new BufferedImage[4];
    public int minvoAnimation = 0;
    public int minvoDeadAnimation = 0;
    public Minvo(GamePanel gp) {
        gamePanel = gp;
        speed = 5;
        hitbox = new Rectangle(3, 3, 42, 42);
        direction = "right";
        getImage();
    }

    public void getImage() {
        try {
            minvoRightImage[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/minvo_right1.png"));
            minvoRightImage[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/minvo_right2.png"));
            minvoRightImage[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/minvo_right3.png"));
            minvoLeftImage[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/minvo_left1.png"));
            minvoLeftImage[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/minvo_left2.png"));
            minvoLeftImage[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/minvo_left3.png"));
            minvoDead[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/minvo_dead.png"));
            minvoDead[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/mob_dead1.png"));
            minvoDead[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/mob_dead2.png"));
            minvoDead[3] = ImageIO.read(getClass().getResourceAsStream("/sprites/mob_dead3.png"));
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
                minvoDeadAnimation++;
            }
            return;
        } else {
            frame++;
            if (frame > 8) {
                frame = 0;
                minvoAnimation++;
                if (minvoAnimation > 2) minvoAnimation = 0;
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
        return minvoDeadAnimation;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = minvoRightImage[minvoAnimation];
        int screenX = mapX - gamePanel.board.player.mapX + gamePanel.board.player.screenX;
        int screenY = mapY - gamePanel.board.player.mapY + gamePanel.board.player.screenY;
        if (!isLiving) image = minvoDead[minvoDeadAnimation];
        else if (direction.equals("right")) {
            image = minvoRightImage[minvoAnimation];
        } else if (direction.equals("left")) {
            image = minvoLeftImage[minvoAnimation];
        }
        g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);
    }
}
