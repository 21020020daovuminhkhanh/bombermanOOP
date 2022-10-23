package bomberman.Entity.Tile;

import bomberman.Entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Tile extends Entity {
    public BufferedImage image;

    public abstract void setCoordinate(int x, int y);

    public abstract void draw(Graphics2D g2);

    @Override
    public void update() {

    }
}
