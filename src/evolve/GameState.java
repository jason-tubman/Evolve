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
    private World world;
    public GameState(Game game, World world) {
        super(game);
        createCreature();
        this.world = world;
        addButton();
    }

    public void addButton() {
        JButton addButton = new JButton("ADD");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                createCreature();
            }
        });
        addButton.setBounds(0, 0, 200, 50);
        this.game.getPanel().getFrame().getContentPane().add(addButton);
        Canvas canvas = this.game.getPanel().getCanvas();
        this.game.getPanel().getFrame().add(canvas);
        this.game.getPanel().getFrame().pack();
    }

    public void createCreature() {
        float y =  50 + (int)(Math.random() * 768-100);
        float x =  51 + (int)(Math.random() * 1024-101);
        creatureInstances.add(new CreatureInstance(this.game, x, y, 50, 50, 3, 10, 5, 0));
    }

    public ArrayList<CreatureInstance> getCreatures() {
        return creatureInstances;
    }

    @Override
    public void tick() {

        for (int i = 0; i < creatureInstances.size(); i++) {
            creatureInstances.get(i).tick();
        }

    }

    @Override
    public void render(Graphics g) {
        for (int i = 0; i < creatureInstances.size(); i++) {
            creatureInstances.get(i).render(g);
        }


    }
}
