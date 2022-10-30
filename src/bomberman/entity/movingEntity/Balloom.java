package bomberman.entity.movingEntity;

import bomberman.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Balloom extends MovingEntity{
    BufferedImage[] BalloomRightImage = new BufferedImage[3];
    BufferedImage[] BalloomLeftImage = new BufferedImage[3];
    public int balloomAnimation = 0;
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
            BalloomRightImage[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/balloom_right1.png"));
            BalloomRightImage[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/balloom_right2.png"));
            BalloomRightImage[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/balloom_right3.png"));
            BalloomLeftImage[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/balloom_left1.png"));
            BalloomLeftImage[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/balloom_left2.png"));
            BalloomLeftImage[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/balloom_left3.png"));
            for (int i = 0; i < 3; i++) {
                removeColor(BalloomRightImage[i]);
                removeColor(BalloomLeftImage[i]);
            }
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

    public void draw(Graphics2D g2) {
        BufferedImage image = BalloomRightImage[balloomAnimation];
        int screenX = mapX - gamePanel.board.player.mapX + gamePanel.board.player.screenX;
        int screenY = mapY - gamePanel.board.player.mapY + gamePanel.board.player.screenY;
        if (direction.equals("right")) {
            image = BalloomRightImage[balloomAnimation];
        } else if (direction.equals("left")) {
            image = BalloomLeftImage[balloomAnimation];
        }
        g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);
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
