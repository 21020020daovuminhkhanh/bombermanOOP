package bomberman;


import javax.swing.JFrame;


public class Bomberman {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT);
        frame.setTitle("Bomberman");
        GamePanel g = new GamePanel();
        frame.add(g);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g.startGameThread();
    }
}
