package evolve;

import java.awt.*;

/**
 * Created by Jason on 27/10/16.
 */
public class CreatureInstance extends Entity {
    private Game game;
    private World world;
    private float maxSpeed;
    private int lifeTime;
    private int eggTime;
    private int eggRequirement;
    private int foodAmount;


    private int timeBorn;

    float yDirection = 0;
    float xDirection = 0;
    double angle;

    public CreatureInstance(Game game, float x, float y, int height,
                            int width, float maxSpeed,
                            int lifeTime, int eggTime, int foodAmount) {
        super(game, x, y, height, width);
        this.maxSpeed =  maxSpeed; // How fast it can move
        this.world = game.getWorld();
        this.game = game;
        this.lifeTime = lifeTime; //Health starts here and falls to 0
        this.eggTime = eggTime; //How long it takes to lay an egg
        this.eggRequirement = lifeTime/5; //How much food it requires to lay an egg

        this.lifeTime = lifeTime; //Health starts here and falls to 0

        this.foodAmount = foodAmount; //How much food it's grabbed
        timeBorn = game.getSeconds();
        getDirection();
    }

    @Override
    public void tick() {
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
        if (y >= world.getWidth() - height+1) {
            yDirection = -yDirection;
            angle = Math.atan2(yDirection, xDirection);
            angle = angle + 1.57;
        }
        if (x >= world.getHeight() - width+1) {
            xDirection= -xDirection;
            angle = Math.atan2(yDirection, xDirection);
            angle = angle + 1.57;
        }
        if (y <= height+1) {
            yDirection = -yDirection;
            angle = Math.atan2(yDirection, xDirection);
            angle = angle + 1.57;
        }
        if (x <= width+1) {
            xDirection= -xDirection;
            angle = Math.atan2(yDirection, xDirection);
            angle = angle + 1.57;
        }

        makeMove();
    }

    public void getDirection() {
        yDirection = maxSpeed * (Math.random() > 0.5? 1 : -1);
        xDirection = maxSpeed * (Math.random() > 0.5? 1 : -1);
        angle = Math.atan2(yDirection, xDirection);
        angle = angle + 1.57;
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.rotate(angle, x, y);
        g2d.drawImage(Assets.creatureSprite, (int)x, (int)y, height, width, null);
        g2d.rotate(-angle, x, y);
        g2d.dispose();
    }

    public int getLifeRemaining() {
        return (lifeTime - (timeBorn + this.game.getSeconds()));
    }
    public void killCreature() {
        this.game.getGameState().getCreatures().remove(this);

    }


}
