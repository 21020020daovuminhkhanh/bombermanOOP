package bomberman.entity.movingEntity;

import bomberman.entity.Bomb;
import bomberman.entity.Entity;
import bomberman.entity.tile.Grass;
import bomberman.KeyInput;

import java.awt.*;

public abstract class MovingEntity extends Entity {
    public int speed;
    protected KeyInput keyInput;
    public String direction;
    public Rectangle hitbox;
    public boolean isCollide;
    public boolean isLiving = true;
    public int frame = 0;

    public abstract void draw(Graphics2D g2);

    public abstract void update();
    public abstract boolean collideWithExplosion(Bomb b);

    public int getAnimationIndex() {
        return 0;
    }
}
