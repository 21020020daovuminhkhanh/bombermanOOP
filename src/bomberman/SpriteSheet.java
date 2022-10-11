package bomberman;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SpriteSheet {
    private String path;
    public int[] pixels;
    public final int size;
    public BufferedImage image;
    public static SpriteSheet tiles = new SpriteSheet("res/textures/classic.png", 256);

    public SpriteSheet(String path, int size) {
        this.path = path;
        this.size = size;
        this.pixels = new int[size * size];
        load();
    }

    private void load() {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            int width = image.getWidth();
            int height = image.getHeight();
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
