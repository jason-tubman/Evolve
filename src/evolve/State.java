package evolve;

import java.awt.*;

/**
 * Created by Jason on 27/10/16.
 */
public abstract class State {

    public static State currentState = null;

    public static void setState(State state) {
        currentState = state;
    }

    public static State getState() {
        return currentState;
    }

    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public abstract void tick();

    public abstract void render(Graphics g);


}
