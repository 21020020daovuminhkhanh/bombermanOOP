package bomberman.entity;


import bomberman.GamePanel;

public abstract class Entity {
    public int mapX, mapY;  //Toa do entity
    protected GamePanel gamePanel;

    public Entity() {

    }

    public abstract void update();
}