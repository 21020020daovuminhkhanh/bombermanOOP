package bomberman.Entity;

import bomberman.Sprite;

public abstract class Entity {
    private double x, y;
    protected Sprite sprite;

    public abstract void update();
}
