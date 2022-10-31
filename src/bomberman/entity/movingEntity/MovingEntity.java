package bomberman.entity.movingEntity;

import bomberman.entity.Bomb;
import bomberman.entity.Entity;
import bomberman.entity.tile.Grass;
import bomberman.KeyInput;

import java.awt.*;

public abstract class MovingEntity extends Entity {
    public int speed;   //Toc do cua entity chuyen dong
    protected KeyInput keyInput;
    public String direction;
    public Grass g = new Grass(gamePanel);
    public Rectangle hitbox;
    public boolean isCollide;
    public boolean isLiving = true;
    public int frame = 0;

    public abstract void setCoordinate(int x, int y);

    public abstract void draw(Graphics2D g2);

    public void update() {

    }
    public abstract boolean collideWithExplosion(Bomb b);

    public int getAnimationIndex() {
        return 0;
    }
}
