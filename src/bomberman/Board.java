package bomberman;

import bomberman.entity.Bomb;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
    GamePanel gamePanel;
    List<Bomb> bombs = new ArrayList<Bomb>();

    public void draw(Graphics2D g2) {
        for (Bomb bomb : bombs) {
            if (bomb != null) bomb.draw(g2);
        }
    }

    public void update() {
        for (Bomb bomb : bombs) {
            if (bomb != null) bomb.update();
        }
    }

    public Bomb getBombAt(int x, int y) {
        for (Bomb bomb : bombs) {
            if (bomb.mapX == x && bomb.mapY == y) {
                return bomb;
            }
        }
        return null;
    }
}
