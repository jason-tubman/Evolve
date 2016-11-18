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
    private double digestionTime;
    private double anotherEgg;
    private double livingSeconds = 0;
    private int foodAdded = 0;
    private double timeBorn;

    double yDirection = 0;
    double xDirection = 0;

    public CreatureInstance(Game game, String type, double x, double y, double height,
                            double width, double maxSpeed,
                            double lifeTime, double eggTime, double foodAmount, int generation, double foodTime) {
        super(game, x, y, height, width);
        this.maxSpeed =  maxSpeed; // How fast it can move
        this.generation = generation;
        this.height = height;
        this.width = width;
        this.digestionTime = foodTime;
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
        checkPickupFood();
        checkEatAnother();
        checkFoodDown();
        checkStarvation();
        checkEgg();
        if (getLifeRemaining() <= 0) {
            killCreature();
        }
        findMove();
    }
    public void makeMove(){
        checkPickupFood();
        checkEatAnother();
        y += yDirection;
        x += xDirection;
    }

    public void findMove() {
        if (y >= world.getHeight()- height - 30) {
            yDirection = -yDirection;
        }
        if (x >= world.getWidth() - width) {
            xDirection= -xDirection;
        }
        if (y <= 50 + height) {
            yDirection = -yDirection;
        }
        if (x <= 250 + width) {
            xDirection= -xDirection;
        }
        if (Math.random() > 0.99) {
            getDirection();
        }
        checkPickupFood();
        makeMove();
    }

    public void getDirection() {
        yDirection = maxSpeed * (Math.random() > 0.5? 1 : -1);
        xDirection = maxSpeed * (Math.random() > 0.5? 1 : -1);
    }

    @Override
    public void render(Graphics g) {
        if (this.type == "Herbivore") {
            g.drawImage(Assets.herbivoreSprite, (int)x, (int)y, (int)height, (int)width, null);
        }
        else {
            g.drawImage(Assets.carnivoreSprite, (int)x, (int)y, (int)height, (int)width, null);
        }

    }
    public void checkPickupFood() {
        if (this.type.equals("Herbivore")) {

            if (game.getGameState().foodAtLocation(this.x, this.y, this.height, this.width) != null) {
                foodAmount++;
                foodInstance food = game.getGameState().foodAtLocation(this.x , this.y, this.height, this.width);
                game.getGameState().getFoods().remove(food);
            }

        }
    }
    public void checkEatAnother() {
        if (this.type.equals("Carnivore")) {
            if (game.getGameState().creatureAtLocation(this.x, this.y, this.height, this.width) != null) {
                if (this.height >=
                        game.getGameState().creatureAtLocation(this.x, this.y, this.height, this.width).height) {
                    CreatureInstance creature = game.getGameState().creatureAtLocation(this.x , this.y, this.height, this.width);
                    if (!creature.equals(this)) {
                        foodAmount++;
                        game.getGameState().getCreatures().remove(creature);
                    }
                }

            }
        }
    }
    public void renderSide(Graphics g, int x, int y, int height, int width) {
        if (this.getType() == "Herbivore") {
            g.drawImage(Assets.herbivoreSprite, x, y, height, width, null);
        }
        else {
            g.drawImage(Assets.carnivoreSprite, x, y, height, width, null);
        }
    }

    public void checkStarvation() {
        if (this.foodAmount <= 0) {
            killCreature();
        }
    }

    public void checkFoodDown() {
        double gameTime = 0;

        if (foodAdded == 0) {
            gameTime = this.game.getSeconds();
        } else {
            gameTime =  this.game.getSeconds() - (foodAdded * digestionTime);
        }

        livingSeconds = (gameTime - this.timeBorn);

        if (livingSeconds >= digestionTime) {
            foodAmount -= 1;
            livingSeconds = 0;
            foodAdded++;
        }

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
                        (int) this.startingHealth, (int) this.eggTime, this.generation, this.digestionTime));
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

    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public double getWidth() {
        return this.width;
    }
    public double getHeight() {
        return this.height;
    }
    public double getMaxSpeed() {
        return this.maxSpeed;
    }
    public int getGeneration() {
        return this.generation;
    }
    public double getDigestionTime() {
        return this.digestionTime;
    }
    public double getLifeTime() {
        return this.startingHealth;
    }
    public double getEggTime() {
        return eggTime;
    }
}
