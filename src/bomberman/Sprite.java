package bomberman;

public class Sprite {
    public static final int DEFAULT_SIZE = 16;
    public final int size;
    private int x, y;
    protected int width;
    protected int height;
    public int[] pixels;
    private SpriteSheet spriteSheet;


    public static Sprite grass = new Sprite(6, 0, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite brick = new Sprite(7, 0, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite wall = new Sprite(5, 0, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite portal = new Sprite(4, 0, DEFAULT_SIZE, 16, 14, SpriteSheet.tiles);

    /*
    |--------------------------------------------------------------------------
    | Bomber Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite player_up = new Sprite(0, 0, DEFAULT_SIZE, 12, 16, SpriteSheet.tiles);
    public static Sprite player_down = new Sprite(2, 0, DEFAULT_SIZE, 12, 15, SpriteSheet.tiles);
    public static Sprite player_left = new Sprite(3, 0, DEFAULT_SIZE, 10, 15, SpriteSheet.tiles);
    public static Sprite player_right = new Sprite(1, 0, DEFAULT_SIZE, 10, 16, SpriteSheet.tiles);

    public static Sprite player_up_1 = new Sprite(0, 1, DEFAULT_SIZE, 12, 16, SpriteSheet.tiles);
    public static Sprite player_up_2 = new Sprite(0, 2, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);

    public static Sprite player_down_1 = new Sprite(2, 1, DEFAULT_SIZE, 12, 15, SpriteSheet.tiles);
    public static Sprite player_down_2 = new Sprite(2, 2, DEFAULT_SIZE, 12, 16, SpriteSheet.tiles);

    public static Sprite player_left_1 = new Sprite(3, 1, DEFAULT_SIZE, 11, 16, SpriteSheet.tiles);
    public static Sprite player_left_2 = new Sprite(3, 2, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);

    public static Sprite player_right_1 = new Sprite(1, 1, DEFAULT_SIZE, 11, 16, SpriteSheet.tiles);
    public static Sprite player_right_2 = new Sprite(1, 2, DEFAULT_SIZE, 12, 16, SpriteSheet.tiles);

    public static Sprite player_dead1 = new Sprite(4, 2, DEFAULT_SIZE, 14, 16, SpriteSheet.tiles);
    public static Sprite player_dead2 = new Sprite(5, 2, DEFAULT_SIZE, 13, 16, SpriteSheet.tiles);
    public static Sprite player_dead3 = new Sprite(6, 2, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);

    /*
    |--------------------------------------------------------------------------
    | Character
    |--------------------------------------------------------------------------
     */
    //BALLOM
    public static Sprite balloom_left1 = new Sprite(9, 0, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite balloom_left2 = new Sprite(9, 1, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite balloom_left3 = new Sprite(9, 2, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);

    public static Sprite balloom_right1 = new Sprite(10, 0, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite balloom_right2 = new Sprite(10, 1, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite balloom_right3 = new Sprite(10, 2, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);

    public static Sprite balloom_dead = new Sprite(9, 3, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);

    //ONEAL
    public static Sprite oneal_left1 = new Sprite(11, 0, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite oneal_left2 = new Sprite(11, 1, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite oneal_left3 = new Sprite(11, 2, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);

    public static Sprite oneal_right1 = new Sprite(12, 0, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite oneal_right2 = new Sprite(12, 1, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite oneal_right3 = new Sprite(12, 2, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);

    public static Sprite oneal_dead = new Sprite(11, 3, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);

//    //Doll
//    public static Sprite doll_left1 = new Sprite(DEFAULT_SIZE, 13, 0, SpriteSheet.tiles, 16, 16);
//    public static Sprite doll_left2 = new Sprite(DEFAULT_SIZE, 13, 1, SpriteSheet.tiles, 16, 16);
//    public static Sprite doll_left3 = new Sprite(DEFAULT_SIZE, 13, 2, SpriteSheet.tiles, 16, 16);
//
//    public static Sprite doll_right1 = new Sprite(DEFAULT_SIZE, 14, 0, SpriteSheet.tiles, 16, 16);
//    public static Sprite doll_right2 = new Sprite(DEFAULT_SIZE, 14, 1, SpriteSheet.tiles, 16, 16);
//    public static Sprite doll_right3 = new Sprite(DEFAULT_SIZE, 14, 2, SpriteSheet.tiles, 16, 16);
//
//    public static Sprite doll_dead = new Sprite(DEFAULT_SIZE, 13, 3, SpriteSheet.tiles, 16, 16);
//
//    //Minvo
//    public static Sprite minvo_left1 = new Sprite(DEFAULT_SIZE, 8, 5, SpriteSheet.tiles, 16, 16);
//    public static Sprite minvo_left2 = new Sprite(DEFAULT_SIZE, 8, 6, SpriteSheet.tiles, 16, 16);
//    public static Sprite minvo_left3 = new Sprite(DEFAULT_SIZE, 8, 7, SpriteSheet.tiles, 16, 16);
//
//    public static Sprite minvo_right1 = new Sprite(DEFAULT_SIZE, 9, 5, SpriteSheet.tiles, 16, 16);
//    public static Sprite minvo_right2 = new Sprite(DEFAULT_SIZE, 9, 6, SpriteSheet.tiles, 16, 16);
//    public static Sprite minvo_right3 = new Sprite(DEFAULT_SIZE, 9, 7, SpriteSheet.tiles, 16, 16);
//
//    public static Sprite minvo_dead = new Sprite(DEFAULT_SIZE, 8, 8, SpriteSheet.tiles, 16, 16);
//
//    //Kondoria
//    public static Sprite kondoria_left1 = new Sprite(DEFAULT_SIZE, 10, 5, SpriteSheet.tiles, 16, 16);
//    public static Sprite kondoria_left2 = new Sprite(DEFAULT_SIZE, 10, 6, SpriteSheet.tiles, 16, 16);
//    public static Sprite kondoria_left3 = new Sprite(DEFAULT_SIZE, 10, 7, SpriteSheet.tiles, 16, 16);
//
//    public static Sprite kondoria_right1 = new Sprite(DEFAULT_SIZE, 11, 5, SpriteSheet.tiles, 16, 16);
//    public static Sprite kondoria_right2 = new Sprite(DEFAULT_SIZE, 11, 6, SpriteSheet.tiles, 16, 16);
//    public static Sprite kondoria_right3 = new Sprite(DEFAULT_SIZE, 11, 7, SpriteSheet.tiles, 16, 16);
//
//    public static Sprite kondoria_dead = new Sprite(DEFAULT_SIZE, 10, 8, SpriteSheet.tiles, 16, 16);

    //ALL
    public static Sprite mob_dead1 = new Sprite(15, 0, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite mob_dead2 = new Sprite(15, 1, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite mob_dead3 = new Sprite(15, 2, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);

    /*
    |--------------------------------------------------------------------------
    | Bomb Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb = new Sprite(0, 3, DEFAULT_SIZE, 15, 15, SpriteSheet.tiles);
    public static Sprite bomb_1 = new Sprite(1, 3, DEFAULT_SIZE, 13, 15, SpriteSheet.tiles);
    public static Sprite bomb_2 = new Sprite(2, 3, DEFAULT_SIZE, 12, 14, SpriteSheet.tiles);

    /*
    |--------------------------------------------------------------------------
    | FlameSegment Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb_exploded = new Sprite(0, 4, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite bomb_exploded1 = new Sprite(0, 5, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite bomb_exploded2 = new Sprite(0, 6, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);

    public static Sprite explosion_vertical = new Sprite(1, 5, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite explosion_vertical1 = new Sprite(2, 5, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite explosion_vertical2 = new Sprite(3, 5, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);

    public static Sprite explosion_horizontal = new Sprite(1, 7, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite explosion_horizontal1 = new Sprite(1, 8, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite explosion_horizontal2 = new Sprite(1, 9, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);

    public static Sprite explosion_horizontal_left_last = new Sprite(0, 7, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite explosion_horizontal_left_last1 = new Sprite(0, 8, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite explosion_horizontal_left_last2 = new Sprite(0, 9, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);

    public static Sprite explosion_horizontal_right_last = new Sprite(2, 7, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite explosion_horizontal_right_last1 = new Sprite(2, 8, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite explosion_horizontal_right_last2 = new Sprite(2, 9, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);

    public static Sprite explosion_vertical_top_last = new Sprite(1, 4, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite explosion_vertical_top_last1 = new Sprite(2, 4, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite explosion_vertical_top_last2 = new Sprite(3, 4, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);

    public static Sprite explosion_vertical_down_last = new Sprite(1, 6, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite explosion_vertical_down_last1 = new Sprite(2, 6, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite explosion_vertical_down_last2 = new Sprite(3, 6, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);

    /*
    |--------------------------------------------------------------------------
    | Brick FlameSegment
    |--------------------------------------------------------------------------
     */
    public static Sprite brick_exploded = new Sprite(7, 1, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite brick_exploded1 = new Sprite(7, 2, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite brick_exploded2 = new Sprite(7, 3, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);

    /*
    |--------------------------------------------------------------------------
    | Powerups
    |--------------------------------------------------------------------------
     */
    public static Sprite powerup_bombs = new Sprite(0, 10, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite powerup_flames = new Sprite(1, 10, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
    public static Sprite powerup_speed = new Sprite(2, 10, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
//    public static Sprite powerup_wallpass = new Sprite(3, 10, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
//    public static Sprite powerup_detonator = new Sprite(4, 10, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
//    public static Sprite powerup_bombpass = new Sprite(5, 10, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);
//    public static Sprite powerup_flamepass = new Sprite(6, 10, DEFAULT_SIZE, 16, 16, SpriteSheet.tiles);

    public Sprite(int x, int y, int size, int width, int height, SpriteSheet spriteSheet) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.width = width;
        this.height = height;
        this.spriteSheet = spriteSheet;
        pixels = new int[size * size];
        load();
    }

    private void load() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                pixels[x + y * size] = spriteSheet.pixels[(x + this.x) + (y + this.y) * spriteSheet.size];
            }
        }
    }

    public Sprite(int size, int color) {
        this.size = size;
        pixels = new int[size * size];
        setColor(color);
    }

    public void setColor(int color) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = color;
        }
    }

    public int getSize() {
        return size;
    }

    public int getPixel(int i) {
        return pixels[i];
    }
}
