package bomberman.entity.movingEntity;

import bomberman.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Balloom extends MovingEntity{
    BufferedImage[] BalloomImage = new BufferedImage[3];
    public int balloomAnimation = 0;
    public Balloom(GamePanel gp) {
        gamePanel = gp;
        getImage();
    }

    public void setCoordinate(int x, int y) {
        this.mapX = x;
        this.mapY = y;
    }

    public void getImage() {
        try {
            BalloomImage[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/balloom_right1.png"));
            BalloomImage[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/balloom_right2.png"));
            BalloomImage[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/balloom_right3.png"));
            removeColor(BalloomImage[0]);
            removeColor(BalloomImage[1]);
            removeColor(BalloomImage[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        frame++;
        if (frame > 8) {
            frame = 0;
            balloomAnimation++;
            if (balloomAnimation > 2) balloomAnimation = 0;
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = mapX - gamePanel.player.mapX + gamePanel.player.screenX;
        int screenY = mapY - gamePanel.player.mapY + gamePanel.player.screenY;
        g2.drawImage(BalloomImage[balloomAnimation], screenX, screenY, tileSize, tileSize, null);
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
