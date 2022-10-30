package bomberman;

import bomberman.entity.movingEntity.Balloom;
import bomberman.entity.movingEntity.MovingEntity;
import bomberman.entity.movingEntity.Oneal;
import bomberman.entity.movingEntity.Player;
import bomberman.entity.tile.*;
import bomberman.entity.tile.item.BombItem;
import bomberman.entity.tile.item.FlameItem;
import bomberman.entity.tile.item.Item;
import bomberman.entity.tile.item.SpeedItem;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Level {
    GamePanel gamePanel;
    int level;
    int height;
    int width;
    public char[][] mapTile;
    public Tile wall;
    public Tile grass;
    public Tile brick;
    public Tile portal;

    Item bombItem;
    Item speedItem;
    Item flameItem;
    Player player;
    
    public Level(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        loadMapTile();

        player = new Player(gamePanel, gamePanel.keyInput);
        gamePanel.board.addPlayer(player);

        wall = new Wall(gamePanel);
        grass = new Grass(gamePanel);
        brick = new Brick(gamePanel);
        portal = new Portal(gamePanel);

        bombItem = new BombItem(gamePanel);
        flameItem = new FlameItem(gamePanel);
        speedItem = new SpeedItem(gamePanel);
    }

    public void loadMapTile() {
        try {
            InputStream input = getClass().getResourceAsStream("/levels/Level0.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(input));

            String line = new String();
            line = br.readLine();
            String[] levelInfo = line.split(" ");
            level = Integer.parseInt(levelInfo[0]);
            height = Integer.parseInt(levelInfo[1]);
            width = Integer.parseInt(levelInfo[2]);


            mapTile = new char[width][height];
            for (int i = 0; i < height; i++) {
                line = br.readLine();
                for (int j = 0; j < width; j++) {
                    mapTile[j][i] = line.charAt(j);
                }
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetGame() {
        gamePanel.board.enemies.clear();
        loadMapTile();
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                switch (mapTile[i][j]) {
                    case '#':
                        wall.setCoordinate(i * GamePanel.tileSize, j * GamePanel.tileSize);
                        wall.draw(g2);
                        break;

                    case ' ':
                        grass.setCoordinate(i * GamePanel.tileSize, j * GamePanel.tileSize);
                        grass.draw(g2);
                        break;

                    case '*':
                        brick.setCoordinate(i * GamePanel.tileSize, j * GamePanel.tileSize);
                        brick.draw(g2);
                        break;

                    case 'x':
                        portal.setCoordinate(i * GamePanel.tileSize, j * GamePanel.tileSize);
                        portal.draw(g2);
                        break;

                    case '1':
                        mapTile[i][j] = ' ';
                        MovingEntity balloom = new Balloom(gamePanel);
                        balloom.setCoordinate(i * GamePanel.tileSize, j * GamePanel.tileSize);
                        gamePanel.board.addEnemies(balloom);
                        break;

                    case '2':
                        mapTile[i][j] = ' ';
                        MovingEntity oneal = new Oneal(gamePanel);
                        oneal.setCoordinate(i * GamePanel.tileSize, j * GamePanel.tileSize);
                        gamePanel.board.addEnemies(oneal);
                        break;

                    case 'b':
                        bombItem.setCoordinate(i * GamePanel.tileSize, j * GamePanel.tileSize);
                        bombItem.draw(g2);
                        break;

                    case 'f':
                        flameItem.setCoordinate(i * GamePanel.tileSize, j * GamePanel.tileSize);
                        flameItem.draw(g2);
                        break;

                    case 's':
                        speedItem.setCoordinate(i * GamePanel.tileSize, j * GamePanel.tileSize);
                        speedItem.draw(g2);
                        break;

                    case 'p':
                        mapTile[i][j] = ' ';
                        gamePanel.board.player.setCoordinate(i * GamePanel.tileSize, j * GamePanel.tileSize);
                        break;
                }
            }
        }
    }
}
