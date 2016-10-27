package evolve;

import java.awt.*;
import java.util.Random;

/**
 * Created by Jason on 27/10/16.
 */
public class CreatureInstance extends Creature {

    private Game game;
    private float maxSpeed = 3;
    float yDirection = 0;
    float xDirection = 0;

    public CreatureInstance(Game game, float x, float y) {
        super(x, y);
        this.game = game;
        getDirection();
    }

    @Override
    public void tick() {
        if (y > 20 && y < 737 && x > 20 && x < 990) {
            y += yDirection;
            x += xDirection;
        } else {
            getDirection();
            if (y + yDirection > 20 && y + yDirection < 737
                    && x + xDirection > 20 && x + xDirection < 990) {
                y += yDirection;
                x += xDirection;
            } else {
                getDirection();
            }


        }
    }

    public void getDirection() {
        yDirection = maxSpeed * (Math.random() > 0.5? 1 : -1);
        xDirection = maxSpeed * (Math.random() > 0.5? 1 : -1);

    }

    @Override
    public void render(Graphics g) {
        g.drawString("CREATURE", (int)x, (int)y);

    }
}
