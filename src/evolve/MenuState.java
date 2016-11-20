package evolve;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jason on 27/10/16.
 */
public class MenuState extends State {


    public MenuState(Game game) {
        super(game);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 100));
        g.drawString("EVOLVE ", 750, 400);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("A simple evolution Simulator", 610, 465);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("An Experiment in Java by Jason Tubman", 850, 500);

    }
}
