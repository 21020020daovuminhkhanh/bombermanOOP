package bomberman.Entity;

import bomberman.Game;
import bomberman.GamePanel;
import bomberman.Screen;
import bomberman.Sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity {
    protected int x, y;  //Toa do entity
    protected GamePanel gamePanel;

    public Entity() {

    }

    public abstract void update();

    public abstract boolean collide(Entity entity);
}
