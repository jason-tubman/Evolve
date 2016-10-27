package evolve;

import java.awt.*;

/**
 * Created by Jason on 27/10/16.
 */
public abstract class Entity {
    protected Game game;
    protected float x, y;
    protected int width, height;

    public Entity (Game game, float x, float y, int height, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
