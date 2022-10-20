package bomberman;

import bomberman.Entity.Brick;
import bomberman.Entity.Grass;
import bomberman.Entity.Player;
import bomberman.Entity.Wall;
import bomberman.Level;

import javax.swing.JPanel;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable{
    public final int size = 16;
    public final int scale = 3;
    public final int tileSize = size * scale;
    final int cols = 31;
    final int rows = 13;
    public final int MAP_WIDTH = cols * tileSize;
    public final int MAP_HEIGHT = rows * tileSize;
    public final int SCREEN_WIDTH = cols * tileSize / 2;
    public final int SCREEN_HEIGHT = rows * tileSize;
    KeyInput keyInput = new KeyInput();
    Thread thread;
    public Player player = new Player(this, keyInput);
    public CheckCollision checkCollision = new CheckCollision(this);
    public Level level = new Level(this);

    int x1 = 100;
    int y1 = 100;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyInput);
        this.setFocusable(true);
    }

    public void startGameThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (thread != null) {
            try {
                update();
                repaint();
                thread.sleep(1000/60);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        level.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}
