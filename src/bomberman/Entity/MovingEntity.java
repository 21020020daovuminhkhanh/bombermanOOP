package bomberman.Entity;

import bomberman.Screen;

public abstract class MovingEntity extends Entity{
    protected int speed;   //Toc do cua entity chuyen dong

    public void update() {

    }

    public boolean collide(Entity entity) {
        return false;
    }

    public void render(Screen screen) {

    }
}
