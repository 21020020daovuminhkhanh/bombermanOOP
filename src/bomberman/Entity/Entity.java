package bomberman.Entity;

import bomberman.Screen;
import bomberman.Sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity {
    protected int x, y;  //Toa do entity
    protected Sprite sprite;

    public Entity() {

    }

    public Sprite getSprite() {
        return sprite;
    }

    public abstract void update();

    public abstract void render(Screen screen);

    public abstract boolean collide(Entity entity);
}
