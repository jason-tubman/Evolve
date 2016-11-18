package evolve;

import java.awt.*;

import java.util.*;

/**
 * Created by Jason on 27/10/16.
 */
public class GameState extends State{

    private ArrayList<CreatureInstance> creatureInstances = new ArrayList<>();
    private ArrayList<foodInstance> foodInstances = new ArrayList<>();
    private ArrayList<eggInstance> eggInstances = new ArrayList<>();
    private World world;
    private int secondsPassed = 0;
    private int ticksPassed = 0;
    private Sidebar sidebar = new Sidebar(this.game, 0, 70, game.getPanel().getCanvas().getHeight() - 90, 250);


    public GameState(Game game, World world) {
        super(game);
        createCreature();
        this.world = world;
    }

    public void createCreature() {
        for (int i = 0; i < 200; i++) {
            generateFirstCreatures();
        }
        for (int i = 0; i < 600; i++) {
            float y = 63 + (int) (Math.random() * (this.game.getPanel().getCanvas().getHeight() - 50));
            float x = 260 + (int) (Math.random() * (this.game.getPanel().getCanvas().getWidth() - 20));
            foodInstances.add(new foodInstance(this.game, x, y, 2, 2));
        }
    }

    public void generateFirstCreatures() {

        String newType;

        double newY = 88 + (int) (Math.random() * (this.game.getPanel().getCanvas().getHeight() - 180));
        double newX = 300 + (int) (Math.random() * (this.game.getPanel().getCanvas().getWidth() - 20));
        if (Math.random() < 0.005) {
            newType = "Carnivore";
        } else {
            newType = "Herbivore";
        }
        double newHeight  = 5 + Math.random() * 7;
        double newWidth = newHeight;
        double foodTime = 20;
        double newMaxSpeed = 1;
        while (Math.random() < 0.33) {
            newMaxSpeed += 0.02;
        }
        //Calculate the new max health
        double newHealth = 60;
        while (Math.random() < 0.33) {
            newHealth += 1;
        }

        //calculate the new eggTime
        double newEggTime = 32;
        while (Math.random() < 0.33) {
            newEggTime -= 1;
        }

        double newfoodAmount = 5;
        //Calculate the generation
        int newGeneration = 1;
        creatureInstances.add(new CreatureInstance(game, newType, newX, newY, newHeight, newWidth,
                newMaxSpeed, newHealth, newEggTime, newfoodAmount, newGeneration, foodTime, 15));


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


    public foodInstance foodAtLocation(double x, double y, double h, double w) {

        for (int i = 0; i < foodInstances.size(); i++) {
            double Width = foodInstances.get(i).getWidth();
            double foodHeight = foodInstances.get(i).getHeight();
            double foodX = foodInstances.get(i).getX();
            double foodY = foodInstances.get(i).getY();
            if (x < foodX + Width &&
                    x + w > foodX &&
                    y < foodY + foodHeight &&
                    h + y > foodY) {
                // collision detected!
                return foodInstances.get(i);
            }
        }
        return null;
    }

    public CreatureInstance creatureAtLocation(double x, double y, double h, double w) {
        for (int i = 0; i < creatureInstances.size(); i++) {
            double creatureWidth = creatureInstances.get(i).getWidth();
            double creatureHeight = creatureInstances.get(i).getHeight();
            double creatureX = creatureInstances.get(i).getX();
            double creatureY = creatureInstances.get(i).getY();
            int bye;
            if (x < creatureX + creatureWidth &&
                    x + w > creatureX &&
                    y < creatureY + creatureHeight &&
                    h + y > creatureY) {
                // collision detected!
                return creatureInstances.get(i);
            }
        }
            return null;
        }


    public void addNewFood() {
        if (foodInstances.size() < 600 && secondsPassed >=0.5) {
            float y = 50 + (int) (Math.random() * (this.game.getPanel().getCanvas().getHeight() - 100));
            float x = 301 + (int) (Math.random() *  (this.game.getPanel().getCanvas().getWidth() - 50));
            foodInstances.add(new foodInstance(this.game, x, y, 2, 2));
            secondsPassed = 0;
        }

    }

    public void hatchEgg() {
        for (int i = 0; i < eggInstances.size(); i++) {
            if (game.getSeconds() - eggInstances.get(i).getTimeLayed() >= 25) {
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
                //calc new foodTime
                double newFoodTime = eggInstances.get(i).getDigestionTime();
                while (Math.random() < 0.33) {
                    newFoodTime += 0.15;
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

                int newAnotherEgg = 15;

                while (Math.random() < 0.22) {
                    newAnotherEgg -= 1;
                }
                //destroy the egg
                eggInstances.remove(i);
                //HATCH THE EGG
                creatureInstances.add(new CreatureInstance(game, newType, newX, newY, newHeight, newWidth,
                        newMaxSpeed, newHealth, newEggTime, newfoodAmount, newGeneration, newFoodTime, newAnotherEgg));

            }
        }
    }

    @Override
    public void tick() {
        sidebar.tick();
        ticksPassed++;
        if (ticksPassed >= 3) {
            secondsPassed = 1;
            ticksPassed = 0;
        }
        hatchEgg();
        addNewFood();
        for (int i = 0; i < foodInstances.size(); i++) {
            foodInstances.get(i).tick();

        }
        for (int i = 0; i < creatureInstances.size(); i++) {
            creatureInstances.get(i).tick();

        }

    }

    @Override
    public void render(Graphics g) {
        sidebar.render(g);
        g.setColor(new Color(48, 19, 11));
        g.fillRect(0, 0, this.game.getPanel().getCanvas().getWidth(), 50);
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
