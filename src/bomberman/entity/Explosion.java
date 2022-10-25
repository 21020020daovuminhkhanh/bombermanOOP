package bomberman.entity;

import bomberman.GamePanel;

import java.awt.image.BufferedImage;

public class Explosion extends Entity {
    BufferedImage upFlame, downFlame, rightFlame, leftFlame;

    public Explosion(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void getImage() {

    }

    @Override
    public void update() {

    }
}
