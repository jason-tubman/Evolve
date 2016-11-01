package evolve;

import java.awt.*;

/**
 * Created by Jason on 27/10/16.
 */
public class CreatureInstance extends Entity {
    private Game game;
    private World world;

    private int generation;

    private double height;
    private double width;
    private double startingHealth;
    private double maxSpeed;
    private double health;
    private double eggTime;
    private double foodAmount;
    private String type;
    private Boolean eggLayed = false;
    private double anotherEgg;

    private double timeBorn;

    double yDirection = 0;
    double xDirection = 0;

    public CreatureInstance(Game game, String type, double x, double y, double height,
                            double width, double maxSpeed,
                            double lifeTime, double eggTime, double foodAmount, int generation) {
        super(game, x, y, height, width);
        this.maxSpeed =  maxSpeed; // How fast it can move
        this.generation = generation;
        this.height = height;
        this.width = width;

        this.world = game.getWorld();
        this.game = game;
        this.health = lifeTime;
        this.startingHealth = lifeTime;
        this.type = type;
        this.eggTime = eggTime; //How long it takes to lay an egg
        this.anotherEgg = lifeTime;

        this.foodAmount = foodAmount; //How much food it's grabbed
        timeBorn = game.getSeconds();
        getDirection();
    }

    @Override
    public void tick() {
        checkEgg();
        if (getLifeRemaining() <= 0) {
            killCreature();
        }
        findMove();
    }
    public void makeMove(){
        y += yDirection;
        x += xDirection;
    }

    public void findMove() {
        if (y >= world.getHeight()- height) {
            yDirection = -yDirection;
        }
        if (x >= world.getWidth() - width) {
            xDirection= -xDirection;
        }
        if (y <= 50) {
            yDirection = -yDirection;
        }
        if (x <= 0) {
            xDirection= -xDirection;
        }
        if (Math.random() > 0.99) {
            getDirection();
        }

        makeMove();
    }

    public void getDirection() {
        yDirection = maxSpeed * (Math.random() > 0.5? 1 : -1);
        xDirection = maxSpeed * (Math.random() > 0.5? 1 : -1);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.herbivoreSprite, (int)x, (int)y, (int)height, (int)width, null);
    }

    public double getLifeRemaining() {
        return (health - (this.game.getSeconds() - timeBorn));
    }
    public void killCreature() {
        this.game.getGameState().getCreatures().remove(this);

    }
    public void checkEgg() {
        if ((this.game.getSeconds() - timeBorn) > eggTime && eggLayed == false && health > ((int)startingHealth / 3)) {
            if (Math.random() < 0.005) {
                game.getGameState().getEggs().add(new eggInstance(game, this.type, this.x,
                        this.y, this.height, this.width, (int) this.maxSpeed,
                        (int) this.startingHealth, (int) this.eggTime, this.generation));
                eggLayed = true;
            }

        }
        if (foodAmount >= anotherEgg) {
            anotherEgg*=1.5;
            eggLayed = false;
        }

    }
    public String getType() {
        return this.type;
    }


}
