package bomberman;

import bomberman.entity.Bomb;
import bomberman.entity.movingEntity.Balloom;
import bomberman.entity.movingEntity.MovingEntity;
import bomberman.entity.movingEntity.Oneal;
import bomberman.entity.tile.*;

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
    MovingEntity balloom;
    MovingEntity oneal;
    //Bomb bomb;
    
    public Level(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        loadMapTile();

        wall = new Wall(gamePanel);
        grass = new Grass(gamePanel);
        brick = new Brick(gamePanel);
        portal = new Portal(gamePanel);

        balloom = new Balloom(gamePanel);
        oneal = new Oneal(gamePanel);
        //bomb = new Bomb(gamePanel);
    }

    public void loadMapTile() {
        try {
            InputStream input = getClass().getResourceAsStream("/levels/Level0.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(input));

            String line = new String();
            line = br.readLine();
            String levelInfo[] = line.split(" ");
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

    public void update() {
        balloom.update();
        oneal.update();
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                switch (mapTile[i][j]) {
                    case '#':
                        wall.setCoordinate(i * gamePanel.tileSize, j * gamePanel.tileSize);
                        wall.draw(g2);
                        break;

                    case ' ':
                        grass.setCoordinate(i * gamePanel.tileSize, j * gamePanel.tileSize);
                        grass.draw(g2);
                        break;

                    case '*':
                        brick.setCoordinate(i * gamePanel.tileSize, j * gamePanel.tileSize);
                        brick.draw(g2);
                        break;

                    case 'x':
                        portal.setCoordinate(i * gamePanel.tileSize, j * gamePanel.tileSize);
                        portal.draw(g2);
                        break;

                    case '1':
                        balloom.setCoordinate(i * gamePanel.tileSize, j * gamePanel.tileSize);
                        balloom.draw(g2);
                        break;

                    case '2':
                        oneal.setCoordinate(i * gamePanel.tileSize, j * gamePanel.tileSize);
                        oneal.draw(g2);
                        break;

                    case '3':
                        gamePanel.player.bomb.setCoordinate(i * gamePanel.tileSize, j * gamePanel.tileSize);
                        gamePanel.player.bomb.draw(g2);
                        break;
                }
            }
        }
    }
}
