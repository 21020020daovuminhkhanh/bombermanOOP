package bomberman.entity.tile;

import bomberman.entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Tile extends Entity {
    public BufferedImage image;

    public abstract void draw(Graphics2D g2);

    @Override
    public void update() {

    }
}
