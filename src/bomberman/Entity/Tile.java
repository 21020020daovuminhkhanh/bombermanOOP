package bomberman.Entity;

import bomberman.Screen;
import bomberman.Sprite;

public abstract class Tile extends Entity{
    public Tile(int x, int y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity entity) {
        return false;
    }

    @Override
    public void render(Screen screen) {
        screen.renderEntity((int) this.x, (int) this.y, this);
    }
}
