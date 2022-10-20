package bomberman.Entity;

import bomberman.KeyInput;
import bomberman.Screen;

import java.awt.*;

public abstract class MovingEntity extends Entity{
    protected int speed;   //Toc do cua entity chuyen dong
    protected KeyInput keyInput;
    public Grass g = new Grass(gamePanel);

    public abstract void setCoordinate(int x, int y);

    public abstract void draw(Graphics2D g2);

    public void update() {

    }

    public boolean collide(Entity entity) {
        return false;
    }
}
