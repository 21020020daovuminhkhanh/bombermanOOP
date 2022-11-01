package bomberman;

import bomberman.entity.movingEntity.*;
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
    Player player;
    
    public Level(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        loadMapTile();

        player = new Player(gamePanel, gamePanel.keyInput);
        gamePanel.board.addPlayer(player);

        wall = new Wall(gamePanel);
        grass = new Grass(gamePanel);
        brick = new Brick(gamePanel);
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
        gamePanel.board.clearBoard();
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
                        mapTile[i][j] = '*';
                        Portal portal = new Portal(gamePanel);
                        portal.setCoordinate(i * GamePanel.tileSize, j * GamePanel.tileSize);
                        gamePanel.board.addPortal(portal);
                        break;

                    case '1':
                        mapTile[i][j] = ' ';
                        Enemy balloom = new Balloom(gamePanel);
                        balloom.setCoordinate(i * GamePanel.tileSize, j * GamePanel.tileSize);
                        gamePanel.board.addEnemies(balloom);
                        break;

                    case '2':
                        mapTile[i][j] = ' ';
                        Enemy oneal = new Oneal(gamePanel);
                        oneal.setCoordinate(i * GamePanel.tileSize, j * GamePanel.tileSize);
                        gamePanel.board.addEnemies(oneal);
                        break;

                    case '3':
                        mapTile[i][j] = ' ';
                        Enemy doll = new Doll(gamePanel);
                        doll.setCoordinate(i * GamePanel.tileSize, j * GamePanel.tileSize);
                        gamePanel.board.addEnemies(doll);
                        break;

                    case '4':
                        mapTile[i][j] = ' ';
                        Enemy minvo = new Minvo(gamePanel);
                        minvo.setCoordinate(i * GamePanel.tileSize, j * GamePanel.tileSize);
                        gamePanel.board.addEnemies(minvo);
                        break;

                    case 'b':
                        mapTile[i][j] = '*';
                        Item bombItem = new BombItem(gamePanel);
                        bombItem.setCoordinate(i * GamePanel.tileSize, j * GamePanel.tileSize);
                        gamePanel.board.addItem(bombItem);
                        break;


                    case 'f':
                        mapTile[i][j] = '*';
                        Item flameItem = new FlameItem(gamePanel);
                        flameItem.setCoordinate(i * GamePanel.tileSize, j * GamePanel.tileSize);
                        gamePanel.board.addItem(flameItem);
                        break;

                    case 's':
                        mapTile[i][j] = '*';
                        Item speedItem = new SpeedItem(gamePanel);
                        speedItem.setCoordinate(i * GamePanel.tileSize, j * GamePanel.tileSize);
                        gamePanel.board.addItem(speedItem);
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
