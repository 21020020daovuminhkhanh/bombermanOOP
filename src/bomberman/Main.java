package bomberman;


import javax.swing.JFrame;


public class Main{
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(15 * 48, 13 * 48);
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
