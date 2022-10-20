package bomberman.Entity;


import bomberman.GamePanel;

public abstract class Entity {
    public int mapX, mapY;  //Toa do entity
    protected GamePanel gamePanel;


    public Entity() {

    }

    public abstract void update();

    public abstract boolean collide(Entity entity);
}
