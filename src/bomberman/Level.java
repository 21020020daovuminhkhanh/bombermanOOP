package bomberman;

import bomberman.Entity.Brick;
import bomberman.Entity.Grass;
import bomberman.Entity.Wall;
import bomberman.Game;
import bomberman.GamePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Level {
    GamePanel gamePanel;
    int level;
    int height;
    int width;
    char[][] mapTile;
    
    public Level(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        loadMapTile();
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

    public void draw(Graphics2D g2) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                if (mapTile[i][j] == '#') {
                    Wall wall = new Wall(gamePanel);
                    wall.setCoordinate(i * gamePanel.tileSize, j * gamePanel.tileSize);
                    wall.draw(g2);
                }

                if (mapTile[i][j] == ' ') {
                    Grass grass = new Grass(gamePanel);
                    grass.setCoordinate(i * gamePanel.tileSize, j * gamePanel.tileSize);
                    grass.draw(g2);
                }

                if (mapTile[i][j] == '*') {
                    Brick brick = new Brick(gamePanel);
                    brick.setCoordinate(i * gamePanel.tileSize, j * gamePanel.tileSize);
                    brick.draw(g2);
                }
            }
        }
    }
}
