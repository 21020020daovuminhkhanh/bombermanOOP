package bomberman.Entity;

import bomberman.Screen;
import bomberman.Sprite;

public abstract class Entity {
    protected double x, y;
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
