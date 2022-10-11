package bomberman.Entity;

import bomberman.Screen;

public abstract class MovingEntity extends Entity{
    public void update() {

    }

    public boolean collide(Entity entity) {
        return false;
    }

    public void render(Screen screen) {

    }
}
