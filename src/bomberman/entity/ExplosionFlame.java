package bomberman.entity;

import bomberman.GamePanel;
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

    Grass g;
    public ExplosionFlame(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        g = new Grass(gamePanel);
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

    @Override
    public void update() {

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
