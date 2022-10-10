package bomberman;

import bomberman.Entity.Entity;

public class Screen {
    protected int width;
    protected int height;
    public int[] pixels;

    public Screen(int w, int h) {
        width = w;
        height = h;
        pixels = new int[w * h];
    }

    //Hiển thị entity ở tọa độ x, y.
    public void renderEntity(int x, int y, Entity entity) {
        int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
        pixels[x + y * width] = color;
    }
    

}
