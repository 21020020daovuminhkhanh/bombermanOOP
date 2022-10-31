package bomberman;

import bomberman.entity.Bomb;
import bomberman.entity.movingEntity.MovingEntity;
import bomberman.entity.movingEntity.Player;
import bomberman.entity.tile.Portal;
import bomberman.entity.tile.item.BombItem;
import bomberman.entity.tile.item.FlameItem;
import bomberman.entity.tile.item.Item;
import bomberman.entity.tile.item.SpeedItem;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
    GamePanel gamePanel;
    List<MovingEntity> enemies = new ArrayList<>();
    public List<Bomb> bombs = new ArrayList<>();
    List<Item> items = new ArrayList<>();

    public Player player;
    public Portal portal;

    public Board(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void draw(Graphics2D g2) {
        for(Bomb bomb: bombs) {
            bomb.draw(g2);
        }
        for (MovingEntity enemy : enemies) {
            enemy.draw(g2);
        }
        for (Item item: items) {
            if (gamePanel.level.mapTile[item.mapX / GamePanel.tileSize][item.mapY / GamePanel.tileSize] == ' ') {
                item.draw(g2);
            }
        }
        if (gamePanel.level.mapTile[portal.mapX / GamePanel.tileSize][portal.mapY / GamePanel.tileSize] == ' ') {
            portal.draw(g2);
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
                }
                for (int j = 0; j < enemies.size(); j++) {
                    if (enemies.get(j).collideWithExplosion(bombs.get(i))) {
                        enemies.get(j).isLiving = false;
                        if (enemies.get(j).getAnimationIndex() == 3) {
                            removeEnemies(enemies.get(j));
                            j--;
                        }
                    }
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
            }
        }
        for (int i = 0; i < items.size(); i++) {
            if (player.collideWithItem(items.get(i))) {
                gamePanel.playSoundEffect(1);
                if (items.get(i) instanceof BombItem) player.bombAmount++;
                else if (items.get(i) instanceof FlameItem) player.flameLength++;
                else if (items.get(i) instanceof SpeedItem) player.speed++;
                removeItem(items.get(i));
                i--;
            }
        }
        player.update();
    }

    public void addItem(Item i) {
        items.add(i);
    }

    public void removeItem(Item i) {
        items.remove(i);
    }

    public void addBomb(Bomb b) {
        bombs.add(b);
    }

    public Bomb getBombAt(int x, int y) {
        for(Bomb bomb: bombs) {
            if (bomb.mapX == x && bomb.mapY == y) return bomb;
        }
        return null;
    }

    public void addPortal(Portal p) {
        portal = p;
    }
    public void addPlayer(Player p) {
        player = p;
    }

    public void addEnemies(MovingEntity e) {
        enemies.add(e);
    }

    public void removeEnemies(MovingEntity e) {
        enemies.remove(e);
    }

    public int countEnemies() {
        return enemies.size();
    }
}
