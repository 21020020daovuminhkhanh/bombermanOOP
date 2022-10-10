package bomberman;

import bomberman.Entity.Wall;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Main extends Canvas{
    public static void main(String[] args) {
        String s = new String();
        s = "###############################"
          + "#                             #"
          + "#                             #"
          + "#                             #"
          + "#                             #"
          + "#                             #"
          + "#                             #"
          + "#                             #"
          + "#                             #"
          + "#                             #"
          + "#                             #"
          + "#                             #"
          + "###############################";
        JFrame f = new JFrame();
        Screen screen = new Screen(13 * 48, 31 * 48);
        Board board = new Board();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '#') board.addEntity(new Wall(i % 31, i - i % 31, Sprite.wall));
        }
        BufferedImage image = new BufferedImage(48 * 31, 48 * 13, BufferedImage.TYPE_INT_RGB);
        int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
        f.setSize(48 * 31, 48 * 13);
        f.setVisible(true);
    }
}
