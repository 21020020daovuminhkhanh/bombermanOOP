package bomberman;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;

public class Main extends Canvas{
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(20 * 16 * 3, 13 * 16 * 3);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
