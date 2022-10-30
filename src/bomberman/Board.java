package bomberman;

import bomberman.entity.Bomb;
import bomberman.entity.movingEntity.MovingEntity;
import bomberman.entity.movingEntity.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
    GamePanel gamePanel;
    List<MovingEntity> enemies = new ArrayList<>();

    public Player player;

    public Board(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void draw(Graphics2D g2) {
        for (MovingEntity enemy : enemies) {
            enemy.draw(g2);
        }
        player.draw(g2);
    }

    public void update() {
        for (MovingEntity enemy : enemies) {
            enemy.update();
        }
        player.update();
    }

    public boolean collide(Player p, MovingEntity e) {
        return false;
    }

    public void addPlayer(Player p) {
        player = p;
    }

    public void addEnemies(MovingEntity e) {
        enemies.add(e);
    }
}
