package Entity;
import bombermanOOP.src.Sprite;

public abstract class Entity {
    private double x, y;
    protected Sprite sprite;

    public abstract void update();
}
