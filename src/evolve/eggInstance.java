package evolve;

import java.awt.*;

/**
 * Created by Jason on 27/10/16.
 */
public class eggInstance extends Entity {
    private Game game;
    private World world;
    private int generation;

    private double creatureHeight;
    private double creatureWidth;

    private double height;
    private double width;
    private double digestionTime;
    private int timeLayed;
    private double maxSpeed;
    private double health;
    private double eggTime;
    private String type;
    private double x;
    private double y;


    public eggInstance(Game game, String type, double x, double y, double height,
                       double width, double maxSpeed,
                       double lifeTime, double eggTime, int generation, double foodTime) {
        super(game, x, y, height, width);
        this.maxSpeed =  maxSpeed; // How fast it can move
        this.digestionTime = foodTime;
        this.world = game.getWorld();
        this.game = game;
        this.generation = generation;
        this.health = lifeTime;
        this.creatureHeight = height;
        this.creatureWidth = width;
        this.width = (int) width/2;
        this.height = (int) height/2;

        if (this.width == creatureWidth) {
            this.width = 2;
        } else if (this.height == creatureHeight) {
            this.height = 2;
        }

        this.type = type;
        this.eggTime = eggTime; //How long it takes to lay an egg
        this.timeLayed = game.getSeconds();
        this.x = x;
        this.y = y;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.eggSprite, (int)x, (int)y, (int)height, (int)width, null);
    }


    public double getMaxSpeed() {
        return this.maxSpeed;
    }
    public int getGeneration() {
        return this.generation;
    }
    public int getTimeLayed() {
        return this.timeLayed;
    }
    public double getHeight() {
        return this.creatureHeight;
    }
    public double getWidth() {
        return this.creatureWidth;
    }
    public double getHealth() {
        return this.health;
    }
    public double getEggTime() {
        return this.eggTime;
    }
    public String getEggType() {
        return this.type;
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public double getDigestionTime() {
        return this.digestionTime;
    }


}
