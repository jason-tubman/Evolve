package evolve;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Jason on 27/10/16.
 */
public class ProgramPanel {

    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;

    public ProgramPanel(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        createPanel();
    }

    private void createPanel() {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));

        frame.add(canvas);
        frame.pack();

    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }



}
