package bomberman.Entity;

import bomberman.Screen;
import bomberman.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Tile extends Entity{
    protected BufferedImage image;

    public abstract void setCoordinate(int x, int y);

    public abstract void draw(Graphics2D g2);

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity entity) {
        return false;
    }
}
