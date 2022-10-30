package bomberman;

import bomberman.entity.Bomb;
import bomberman.entity.movingEntity.MovingEntity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
    GamePanel gamePanel;
    List<MovingEntity> enemies = new ArrayList<>();

    public Board(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void draw(Graphics2D g2) {
        for (MovingEntity enemy : enemies) {
            enemy.draw(g2);
        }
    }

    public void update() {
        for (MovingEntity enemy : enemies) {
            enemy.update();
        }
    }

    public void addEnemies(MovingEntity e) {
        enemies.add(e);
    }
}
