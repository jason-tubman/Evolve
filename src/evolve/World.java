package evolve;

/**
 * Created by Jason on 27/10/2016.
 */
public class World {
    private int height;
    private int width;
    private Game game;
    public World(Game game, int width, int height) {
        this.height = height;
        this.width = width;
        this.game = game;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

}
