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
    

}
