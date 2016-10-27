package evolve;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

/**
 * Created by Jason on 27/10/16.
 */
public class CreatureInstance extends Creature {

    private World world;
    private float maxSpeed;
    private float health;
    float yDirection = 0;
    float xDirection = 0;
    double angle;

    public CreatureInstance(Game game, float x, float y, int height, int width) {
        super(game, x, y, height, width);
        this.maxSpeed = this.getMaxSpeed();
        this.health = this.getHealth();
        this.world = game.getWorld();
        getDirection();
    }

    @Override
    public void tick() {
        move();
    }

    public void move() {
        if (y > 0 && y < 768-height*2 && x > 0 && x < 1024 - width) {
            y += yDirection;
            x += xDirection;
        } else {
            getDirection();
            if (y + yDirection > 0 && y + yDirection < 768-height
                    && x + xDirection > 0 && x + xDirection < 1024 - width) {
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

        angle = Math.atan2(yDirection, xDirection);


    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.rotate(angle, x, y);
        g2d.drawImage(imageLoader.loadImage("/resources/Placeholder.png"), (int)x, (int)y, height, width, null);
        g2d.rotate(Math.toRadians(angle), x, y);
        g2d.dispose();
    }
}
