package bomberman;

import bomberman.Entity.Entity;
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
        int WIDTH = 31 * 48;
        int HEIGHT = 13 * 48;
        JFrame f = new JFrame();
        Screen screen = new Screen(31 * 48, 13 * 48);
        Board board = new Board();
        BufferedImage view = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) view.getGraphics();
        g2.setColor(new Color(56, 135, 0));
        g2.fillRect(0, 0, WIDTH, HEIGHT);
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
        g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
        f.setSize(31 * 48, 13 * 48);
        f.setVisible(true);
        f.setResizable(false);
        f.setTitle("Bomberman");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '#') board.addEntity(new Wall(0, 0, Sprite.wall));
        }
        board.render(screen);
    }
}
