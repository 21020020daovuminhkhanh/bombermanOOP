package bomberman;

import bomberman.Entity.Player;

import javax.swing.JPanel;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable{
    public final int size = 16;
    public final int scale = 3;
    public final int tileSize = size * scale;
    final int cols = 15;
    final int rows = 13;
    final int SCREEN_WIDTH = cols * tileSize;
    final int SCREEN_HEIGHT = rows * tileSize;
    KeyInput keyInput = new KeyInput();
    Thread thread;
    Player player = new Player(this, keyInput);

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

        player.draw(g2);

        g2.dispose();
    }
}
