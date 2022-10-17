package bomberman.Entity;

import bomberman.Screen;
import bomberman.Sprite;

import java.awt.image.BufferedImage;

public abstract class Tile extends Entity{
    protected BufferedImage image;

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity entity) {
        return false;
    }
}
