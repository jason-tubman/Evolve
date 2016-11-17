package evolve;


import java.awt.*;

/**
 * Created by Jason on 18/11/2016.
 */
public class Sidebar extends Entity{
    private int height;
    private int width;
    private int x;
    private int y;

    public Sidebar(Game game, double x, double y, double height,
                   double width) {
        super(game, x, y, height, width);
        this.x = (int) x;
        this.y = (int) y;
        this.height = (int) height;
        this.width = (int) width;
    }

    @Override
    public void tick() {

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




    }
}
