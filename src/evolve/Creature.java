package evolve;

/**
 * Created by Jason on 27/10/16.
 */
public abstract class Creature extends Entity {

    protected int health;
    private float maxSpeed = 3;

    public Creature(Game game, float x, float y, int height, int width) {
        super(game, x, y, height, width);
        health = 10;
    }

    public int getHealth() {
        return this.health;
    }
    public float getMaxSpeed() {
        return this.maxSpeed;
    }



}
