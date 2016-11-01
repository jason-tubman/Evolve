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
        for (int i = 0; i < 200; i++) {
            generateFirstCreatures();
        }
        for (int i = 0; i < 300; i++) {
            float y = 55 + (int) (Math.random() * 768);
            float x = 0 + (int) (Math.random() * 1024);
            foodInstances.add(new foodInstance(this.game, x, y, 2, 2));
        }
    }

    public void generateFirstCreatures() {

        String newType;

        double newY = 55 + (int) (Math.random() * 768);
        double newX = 0 + (int) (Math.random() * 1024);
        if (Math.random() < 0.05) {
            newType = "Carnivore";
        } else {
            newType = "Herbivore";
        }
        double newHeight  = 5 + Math.random() * 7;
        double newWidth = newHeight;
        double newMaxSpeed = 1;
        while (Math.random() < 0.33) {
            newMaxSpeed += 0.02;
        }
        //Calculate the new max health
        double newHealth = 20;
        while (Math.random() < 0.33) {
            newHealth += 1;
        }

        //calculate the new eggTime
        double newEggTime = 9;
        while (Math.random() < 0.33) {
            newEggTime -= 1;
        }

        double newfoodAmount = 2;
        //Calculate the generation
        int newGeneration = 1;
        creatureInstances.add(new CreatureInstance(game, newType, newX, newY, newHeight, newWidth,
                newMaxSpeed, newHealth, newEggTime, newfoodAmount, newGeneration));


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

        if (foodInstances.size() < 300) {
            float y = 50 + (int) (Math.random() * 768 - 100);
            float x = 51 + (int) (Math.random() * 1024 - 101);
            foodInstances.add(new foodInstance(this.game, x, y, 2, 2));
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
        g.setColor(new Color(108, 77, 93));
        g.fillRect(0, 0, 1024, 50);
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("EVOLVE ", 10, 38);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString("A simple evolution Simulator", 140, 35);
        g.drawString("Food: ", 450, 35);
        g.drawString(Integer.toString(getFoods().size()), 500, 35);
        g.drawString("Herbivores: ", 550, 35);
        int herbivores = 0;
        int carnivores = 0;
        for (int i = 0; i < getCreatures().size(); i++) {
            if (getCreatures().get(i).getType() == "Herbivore") {
                herbivores++;
            } else {
                carnivores++;
            }

        }
        g.drawString(Integer.toString(herbivores), 645, 35);
        g.drawString("Carnivores: ", 690, 35);
        g.drawString(Integer.toString(carnivores), 790, 35);
        g.drawString("Eggs: ", 825, 35);
        g.drawString(Integer.toString(getEggs().size()), 885, 35);

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
