package bomberman;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyInput implements KeyListener {
    public boolean goUp, goDown, goLeft, goRight, putBomb;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            goUp = true;
        }

        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            goDown = true;
        }

        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            goLeft = true;
        }

        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            goRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            goUp = false;
        }

        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            goDown = false;
        }

        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            goLeft = false;
        }

        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            goRight = false;
        }
    }
}
