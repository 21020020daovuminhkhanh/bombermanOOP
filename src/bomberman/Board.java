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
    List<Bomb> bombs = new ArrayList<>();

    public Player player;

    public Board(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void draw(Graphics2D g2) {
        for (MovingEntity enemy : enemies) {
            enemy.draw(g2);
        }
        for(Bomb bomb: bombs) {
            bomb.draw(g2);
        }
        player.draw(g2);
    }

    public void update() {
        for (MovingEntity enemy : enemies) {
            enemy.update();
        }
        for (int i = 0; i < bombs.size(); i++) {
            bombs.get(i).update();
            if (bombs.get(i).bombAnimationCycle == bombs.get(i).maxBombAnimationCycle) {
                if (player.collideWithExplosion(bombs.get(i))) {
                    player.kill();
                    System.out.println("die");
                }
            }
            if (bombs.get(i).bombAnimationCycle > bombs.get(i).maxBombAnimationCycle) {
                bombs.remove(i);
                i--;
            }
        }
        for (MovingEntity enemy : enemies) {
            if (player.collideWithEnemy(enemy)) {
                player.kill();
                System.out.println("die");
            }
        }
        player.update();
    }

    public void addBomb(Bomb b) {
        if (bombs.size() < player.bombAmount) bombs.add(b);
    }

    public Bomb getBombAt(int x, int y) {
        for(Bomb bomb: bombs) {
            if (bomb.mapX == x && bomb.mapY == y) return bomb;
        }
        return null;
    }

    public void addPlayer(Player p) {
        player = p;
    }

    public void addEnemies(MovingEntity e) {
        enemies.add(e);
    }
}
