package evolve;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;


/**
 * Created by Jason on 18/11/2016.
 */
public class Sidebar extends Entity{
    private int height;
    private int width;
    private int x;
    private int y;
    private Game game;
    private int clicked = 0;
    private JComboBox box;

    public Sidebar(Game game, double x, double y, double height,
                   double width) {
        super(game, x, y, height, width);
        this.x = (int) x;
        this.y = (int) y;
        this.height = (int) height;
        this.width = (int) width;
        this.game = game;
        createTopStatsList();
    }

    @Override
    public void tick() {
        String boxValue = game.getPanel().getBoxValue();
        drawTopStats(boxValue);
    }

    public void drawTopStats(String boxValue) {
            switch(boxValue){
                case "Speed" :
                {
                    Collections.sort(game.getGameState().getCreatures(), new Comparator<CreatureInstance>() {
                        public int compare(CreatureInstance c1, CreatureInstance c2) {
                            return Double.compare(c1.getMaxSpeed(), c2.getMaxSpeed());
                        }
                    });
                    break;
                }
                case "Generation" :
                {
                    Collections.sort(game.getGameState().getCreatures(), new Comparator<CreatureInstance>() {
                        public int compare(CreatureInstance c1, CreatureInstance c2) {
                            return Integer.compare(c1.getGeneration(), c2.getGeneration());
                        }
                    });
                    break;
                }
                case "Size" :
                {
                    Collections.sort(game.getGameState().getCreatures(), new Comparator<CreatureInstance>() {
                        public int compare(CreatureInstance c1, CreatureInstance c2) {
                            return Double.compare(c1.getHeight(), c2.getHeight());
                        }
                    });
                    break;
                }
                case "Digestion Time" :
                {
                    Collections.sort(game.getGameState().getCreatures(), new Comparator<CreatureInstance>() {
                        public int compare(CreatureInstance c1, CreatureInstance c2) {
                            return Double.compare(c1.getDigestionTime(), c2.getDigestionTime());
                        }
                    });
                    break;
                }
                case "lifeTime" :
                {
                    Collections.sort(game.getGameState().getCreatures(), new Comparator<CreatureInstance>() {
                        public int compare(CreatureInstance c1, CreatureInstance c2) {
                            return Double.compare(c1.getLifeTime(), c2.getLifeTime());
                        }
                    });
                    break;
                }
                case "Egg Time" :
                {
                    Collections.sort(game.getGameState().getCreatures(), new Comparator<CreatureInstance>() {
                        public int compare(CreatureInstance c1, CreatureInstance c2) {
                            return Double.compare(c1.getEggTime(), c2.getEggTime());
                        }
                    });
                    break;
                }
                default:
            }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(48, 19, 11));
        g.fillRect(x, y, width, height);

        g.setColor(new Color(153, 137, 113));
        g.fillRect(x+5, y+5, width-10, height-10);

        g.setColor(new Color(48, 19, 11));
        g.drawRect(x+10, y+10, width-20, height/2 - 15);
        g.setColor(new Color(150, 143, 135));
        g.fillRect(x+12, y+12, width-22, height/2 - 17);

        g.setColor(new Color(48, 19, 11));
        g.drawRect(x+10, height/2 +75, width-20, height/2 - 15);
        g.setColor(new Color(150, 143, 135));
        g.fillRect(x+12, height/2 +77, width-22, height/2 - 17);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString("TOP CREATURES ", x+17, y+35);

        int creaturesAdded = 0;
        try {
            for (int i = game.getGameState().getCreatures().size() - 1; i > game.getGameState().getCreatures().size() - 1 - 21; i--) {
                CreatureInstance creature = game.getGameState().getCreatures().get(i);

                double size;

                size = 40 - (100*(Math.pow(1.3, -creature.getWidth())));

                creature.renderSide(g, 35 + 80 * (creaturesAdded % 3) - (int)size/3, 205 + 55 * (creaturesAdded / 3) - (int)size,
                        (int) size, (int) size);
                creaturesAdded++;
            }
        } catch (IndexOutOfBoundsException e) {

        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString("CREATURE STATS  ", x+17, y+520);
        drawBottomStats(g);

    }
    public void createTopStatsList() {
        Vector stats = new Vector();
        for(int i = 1; i < 22; i++){
            stats.addElement(Integer.toString(i));
        }
        this.box = new JComboBox(stats);
        game.getPanel().getFrame().getContentPane().add(box);
        game.getPanel().getFrame().getContentPane().add(game.getPanel().getBox());
        game.getPanel().getFrame().getContentPane().add(game.getPanel().getCanvas());
        box.setVisible(true);
        box.setBounds(15, 600, 220, 30);
        game.getPanel().getFrame().pack();

    }

    public void updateClicked() {
        try {
            int size = game.getGameState().getCreatures().size();
            clicked = size - this.box.getSelectedIndex() - 1;
        }
        catch (Exception e) {
        }
    }

    public void drawBottomStats(Graphics g) {
        updateClicked();
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 17));
        g.drawString("Name:  ", x+17, y+585);

        g.drawString(game.getGameState().getCreatures().get(clicked).getName(), x+80, y + 585);
        g.drawString("Size:  ", x+17, y+625);
        g.drawString(Double.toString(game.getGameState().getCreatures().get(clicked).getWidth()), x+65, y + 625);
        g.drawString("Age:  ", x+17, y+670);
        g.drawString(Double.toString(game.getGameState().getCreatures().get(clicked).getAge()), x+60, y + 670);
        g.drawString("Speed:  ", x+17, y+715);
        g.drawString(Double.toString(game.getGameState().getCreatures().get(clicked).getMaxSpeed()), x+80, y + 715);
        g.drawString("Food Amount:  ", x+17, y+760);
        g.drawString(Double.toString(game.getGameState().getCreatures().get(clicked).getFoodAmount()), x+140, y + 760);
        g.drawString("Time To Fertilise:  ", x+17, y+805);
        g.drawString(Double.toString(game.getGameState().getCreatures().get(clicked).getEggTime()), x+160, y + 805);
        g.drawString("Life Expectancy:  ", x+17, y+850);
        g.drawString(Double.toString(game.getGameState().getCreatures().get(clicked).getLifeTime()), x+160, y + 850);
        g.drawString("Digestion Time:  ", x+17, y+895);
        g.drawString(Double.toString(game.getGameState().getCreatures().get(clicked).getDigestionTime()), x+150, y + 895);
        g.drawString("Direction:  ", x+17, y+940);
        if (game.getGameState().getCreatures().get(clicked).getYDir().intValue() == 1) {
            //DOWN
            if (game.getGameState().getCreatures().get(clicked).getXDir().intValue() == 1) {
                g.drawString("SOUTH EAST", x+100, y+940);
            } else if (game.getGameState().getCreatures().get(clicked).getXDir().intValue() == 0) {
                g.drawString("SOUTH", x+100, y+940);
            } else if (game.getGameState().getCreatures().get(clicked).getXDir().intValue() == -1) {
                g.drawString("SOUTH WEST", x+100, y+940);
            }
        } else if (game.getGameState().getCreatures().get(clicked).getYDir().intValue() == 0) {
            //NOT DOWN
            if (game.getGameState().getCreatures().get(clicked).getXDir().intValue() == 1) {
                g.drawString("EAST", x+100, y+940);
            } else if (game.getGameState().getCreatures().get(clicked).getXDir().intValue() == 0) {
                g.drawString("STATIONARY", x+100, y+940);
            } else if (game.getGameState().getCreatures().get(clicked).getXDir().intValue() == -1) {
                g.drawString("WEST", x+100, y+940);
            }
        } else if (game.getGameState().getCreatures().get(clicked).getYDir().intValue() == -1) {
            //UP
            if (game.getGameState().getCreatures().get(clicked).getXDir().intValue() == 1) {
                g.drawString("NORTH EAST", x+100, y+940);
            } else if (game.getGameState().getCreatures().get(clicked).getXDir().intValue() == 0) {
                g.drawString("NORTH", x+100, y+940);
            } else if (game.getGameState().getCreatures().get(clicked).getXDir().intValue() == -1) {
                g.drawString("NORTH WEST", x+100, y+940);
            }
        }


    }

}
