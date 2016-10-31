package evolve;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by Jason on 27/10/16.
 */
public class GameState extends State{

    private ArrayList<CreatureInstance> creatureInstances = new ArrayList<>();
    private ArrayList<foodInstance> foodInstances = new ArrayList<>();
    private ArrayList<eggInstance> eggInstances = new ArrayList<>();
    private World world;
    public GameState(Game game, World world) {
        super(game);
        createCreature();
        this.world = world;
    }

    public void createCreature() {
        for (int i = 0; i < 50; i++) {
            float y = 50 + (int) (Math.random() * 768 - 100);
            float x = 51 + (int) (Math.random() * 1024 - 101);
            creatureInstances.add(new CreatureInstance(this.game, "Herbivore", x, y, 20, 20, 1, 25, 5, 0, 0));
            //game, type, x, y, width, height, speed, lifetime, eggtime, foodAmount
        }
        for (int i = 0; i < 50; i++) {
            float y = 50 + (int) (Math.random() * 768 - 100);
            float x = 51 + (int) (Math.random() * 1024 - 101);
            foodInstances.add(new foodInstance(this.game, x, y, 4, 4));
            //game, type, x, y, width, height, speed, lifetime, eggtime, foodAmount
        }
    }

    public ArrayList<CreatureInstance> getCreatures() {
        return creatureInstances;
    }
    public ArrayList<foodInstance> getFoods() {
        return foodInstances;
    }
    public ArrayList<eggInstance> getEggs() {
        return eggInstances;
    }
    public void addNewFood() {

        if (foodInstances.size() < 50) {
            float y = 50 + (int) (Math.random() * 768 - 100);
            float x = 51 + (int) (Math.random() * 1024 - 101);
            foodInstances.add(new foodInstance(this.game, x, y, 4, 4));
        }

    }

    public void hatchEgg() {
        for (int i = 0; i < eggInstances.size(); i++) {
            if (game.getSeconds() - eggInstances.get(i).getTimeLayed() >= 15) {
                //Calculate the new type
                //Calculate the new x
                //calculate the new y
                String newType;
                double newX = eggInstances.get(i).getX();
                double newY = eggInstances.get(i).getY();
                if (eggInstances.get(i).getEggType() == "Herbivore") {
                    if(Math.random() < 0.01) {
                        newType = "Carnivore";
                    } else {
                        newType = "Herbivore";
                    }
                } else {
                    newType = "Carnivore";
                }
                while (Math.random() < 0.33) {
                    newX++;
                    newY++;
                }

                //Calculate the new height
                //calculate the new width
                double newHeight  = eggInstances.get(i).getHeight();
                double newWidth = eggInstances.get(i).getWidth();
                while (Math.random() < 0.33) {
                    newHeight++;
                    newWidth++;
                }
                //Calculate the new max speed
                double newMaxSpeed = eggInstances.get(i).getMaxSpeed();
                while (Math.random() < 0.33) {
                    newMaxSpeed += 0.25;
                }

                //Calculate the new max health
                double newHealth = eggInstances.get(i).getHealth();
                while (Math.random() < 0.33) {
                    newHealth += 0.25;
                }

                //calculate the new eggTime
                double newEggTime = eggInstances.get(i).getEggTime();
                while (Math.random() < 0.33) {
                    newEggTime -= 0.15;
                }

                double newfoodAmount = 2;
                //Calculate the generation
                int newGeneration = eggInstances.get(i).getGeneration() + 1;

                //destroy the egg
                eggInstances.remove(i);
                //HATCH THE EGG
                creatureInstances.add(new CreatureInstance(game, newType, newX, newY, newHeight, newWidth,
                        newMaxSpeed, newHealth, newEggTime, newfoodAmount, newGeneration));

            }
        }
    }

    @Override
    public void tick() {
        hatchEgg();
        for (int i = 0; i < foodInstances.size(); i++) {
            foodInstances.get(i).tick();
        }
        for (int i = 0; i < creatureInstances.size(); i++) {
            creatureInstances.get(i).tick();
        }

    }

    @Override
    public void render(Graphics g) {
        for (int i = 0; i < creatureInstances.size(); i++) {
            creatureInstances.get(i).render(g);
        }
        for (int i = 0; i < foodInstances.size(); i++) {
            foodInstances.get(i).render(g);
        }
        for (int i = 0; i < eggInstances.size(); i++) {
            eggInstances.get(i).render(g);
        }


    }
}
