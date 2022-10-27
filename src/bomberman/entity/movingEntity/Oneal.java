package bomberman.entity.movingEntity;

import bomberman.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Oneal extends MovingEntity{
    BufferedImage[] onealImage = new BufferedImage[3];
    public int onealAnimation = 0;
    public Oneal(GamePanel gp) {
        gamePanel = gp;
        getImage();
    }

    public void setCoordinate(int x, int y) {
        this.mapX = x;
        this.mapY = y;
    }

    public void getImage() {
        try {
            onealImage[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/oneal_right1.png"));
            onealImage[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/oneal_right2.png"));
            onealImage[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/oneal_right3.png"));
            removeColor(onealImage[0]);
            removeColor(onealImage[1]);
            removeColor(onealImage[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        frame++;
        if (frame > 8) {
            frame = 0;
            onealAnimation++;
            if (onealAnimation > 2) onealAnimation = 0;
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = mapX - gamePanel.player.mapX + gamePanel.player.screenX;
        int screenY = mapY - gamePanel.player.mapY + gamePanel.player.screenY;
        g2.drawImage(onealImage[onealAnimation], screenX, screenY, tileSize, tileSize, null);
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
