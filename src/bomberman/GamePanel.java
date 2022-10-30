package bomberman;

import bomberman.entity.CheckCollision;
import bomberman.entity.movingEntity.Player;

import javax.swing.JPanel;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable{
    public static final int size = 16;
    public static final int scale = 3;
    public static final int tileSize = size * scale;
    public final int cols = 31;
    public final int rows = 13;
    public final int MAP_WIDTH = cols * tileSize;
    public final int MAP_HEIGHT = rows * tileSize;
    public final int SCREEN_WIDTH = cols * tileSize / 2;
    public final int SCREEN_HEIGHT = rows * tileSize;
    KeyInput keyInput = new KeyInput();
    Thread thread;
    //public Player player = new Player(this, keyInput);
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
    }

    @Override
    public void run() {
        while (thread != null) {
            try {
                if (keyInput.reset || board.player.reset) {
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
        level.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        level.draw(g2);
        board.draw(g2);
        //board.player.draw(g2);

        g2.dispose();
    }
}
