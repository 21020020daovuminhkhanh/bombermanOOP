package bomberman;

import bomberman.entity.CheckCollision;
import bomberman.entity.movingEntity.Player;
import bomberman.entity.tile.item.Item;

import javax.swing.JPanel;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable{
    public static final int size = 16;
    public static final int scale = 3;
    public static final int tileSize = size * scale;
    public static final int cols = 31;
    public static final int rows = 13;
    public final int MAP_WIDTH = cols * tileSize;
    public final int MAP_HEIGHT = rows * tileSize;
    public static final int SCREEN_WIDTH = cols * tileSize / 2;
    public static final int SCREEN_HEIGHT = rows * tileSize;
    KeyInput keyInput = new KeyInput();
    Thread thread;
    public Sound sound = new Sound();
    public CheckCollision checkCollision = new CheckCollision(this);
    public Board board = new Board(this);
    public Level level = new Level(this);

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
        playMusic(0);
    }

    @Override
    public void run() {
        while (thread != null) {
            try {
                if (board.player.reset) {
                    Thread.sleep(1000);
                    level.resetGame();
                    board.player.setStartPosition();
                    board.player.reset = false;
                }
                update();
                repaint();
                Thread.sleep(1000/60);  // 60fps
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        board.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (Item item: board.items) {
            if (level.mapTile[item.mapX / tileSize][item.mapY / tileSize] == '*') {
                item.draw(g2);
            }
        }
        if (board.portal != null && level.mapTile[board.portal.mapX / GamePanel.tileSize][board.portal.mapY / GamePanel.tileSize] == '*') {
            board.portal.draw(g2);
        }
        level.draw(g2);
        board.draw(g2);

        g2.dispose();
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSoundEffect(int i) {
        sound.setFile(i);
        sound.play();
    }
}
