package bomberman.Entity;

import bomberman.GamePanel;
import bomberman.KeyInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends MovingEntity {
    BufferedImage classic;
    public BufferedImage[] up = new BufferedImage[3];
    public BufferedImage[] down = new BufferedImage[3];
    public BufferedImage[] left = new BufferedImage[3];
    public BufferedImage[] right = new BufferedImage[3];
    public String direction;
    public boolean moving;
    public int frame = 0;
    public int playerAnimation = 0;

    GamePanel gamePanel;
    KeyInput keyInput;

    public Player(GamePanel g, KeyInput k) {
        gamePanel = g;
        keyInput = k;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Xuat hien o goc tren cung ben trai ban do
    public void setStartPosition() {
        x = gamePanel.tileSize;
        y = gamePanel.tileSize;
        speed = 4;
        direction = "down";
        moving = false;
    }

    public void update() {
        moving = false;
        if (keyInput.goUp) {
            moving = true;
            direction = "up";
            y -= speed;
        }
        if (keyInput.goDown) {
            moving = true;
            direction = "down";
            y += speed;
        }
        if (keyInput.goLeft) {
            moving = true;
            direction = "left";
            x -= speed;
        }
        if (keyInput.goRight) {
            moving = true;
            direction = "right";
            x += speed;
        }

        if (moving) {
            frame++;
            if (frame > 5) {
                frame = 0;
                playerAnimation++;
                if (playerAnimation > 2) playerAnimation = 0;
            }
        } else playerAnimation = 0;
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
        g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
