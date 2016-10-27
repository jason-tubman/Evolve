package evolve;

import java.awt.*;

/**
 * Created by Jason on 27/10/16.
 */
public class GameState extends State{

    private CreatureInstance creatureInstance;

    public GameState(Game game) {
        super(game);
        float y =  1 + (int)(Math.random() * 747);
        float x =  1 + (int)(Math.random() * 990);
        creatureInstance = new CreatureInstance(game, x, y);

    }

    @Override
    public void tick() {
        creatureInstance.tick();

    }

    @Override
    public void render(Graphics g) {
        creatureInstance.render(g);

    }
}
