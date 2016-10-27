package evolve;

/**
 * Created by Jason on 27/10/16.
 */
public abstract class Creature extends Entity {

    protected int health;

    public Creature(float x, float y) {
        super(x, y);
        health = 10;
    }



}
