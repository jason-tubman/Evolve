package evolve;

import java.awt.*;

/**
 * Created by Jason on 27/10/16.
 */
public class eggInstance extends Entity {
    private Game game;
    private World world;

    private double height = 3;
    private double width = 3;

    private double maxSpeed;
    private double health;
    private double eggTime;
    private double foodAmount;
    private String type;


    public eggInstance(Game game, String type, double x, double y, double height,
                       double width, float maxSpeed,
                       int lifeTime, int eggTime) {
        super(game, x, y, height, width);
        this.maxSpeed =  maxSpeed; // How fast it can move

        this.world = game.getWorld();
        this.game = game;

        this.health = lifeTime;

        this.type = type;
        this.eggTime = eggTime; //How long it takes to lay an egg

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.eggSprite, (int)x, (int)y, (int)height, (int)width, null);
    }





}
