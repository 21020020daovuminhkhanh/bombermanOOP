package bomberman.Entity;

import bomberman.KeyInput;
import bomberman.Screen;

public abstract class MovingEntity extends Entity{
    protected int speed;   //Toc do cua entity chuyen dong
    protected KeyInput keyInput;

    public void update() {

    }

    public boolean collide(Entity entity) {
        return false;
    }
}
