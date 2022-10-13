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

        if (key == KeyEvent.VK_S) {
            goDown = true;
        }

        if (key == KeyEvent.VK_A) {
            goLeft = true;
        }

        if (key == KeyEvent.VK_D) {
            goRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            goUp = false;
        }

        if (key == KeyEvent.VK_S) {
            goDown = false;
        }

        if (key == KeyEvent.VK_A) {
            goLeft = false;
        }

        if (key == KeyEvent.VK_D) {
            goRight = false;
        }
    }
}
