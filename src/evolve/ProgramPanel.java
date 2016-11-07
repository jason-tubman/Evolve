package evolve;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


/**
 * Created by Jason on 27/10/16.
 */
public class ProgramPanel {

    private JFrame frame;
    private Canvas canvas;

    //Camera
    private Camera camera;

    private String title = "EVOLVE";
    private int width, height;
    private int totalNotch = 0;

    public ProgramPanel(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        camera = new Camera(0, 0);
        createPanel();
    }

    private void createPanel() {
        frame = new JFrame(title);
        frame.setTitle(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setBackground(new Color(1, 7, 23));
        canvas.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();
                if ((totalNotch > 39 && notches == -1) || (totalNotch < 1 && notches == 1)) {

                } else {
                    totalNotch -= notches;
                }
                camera.setZoomOffset(totalNotch);
            }
        });
        canvas.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
               if (e.getKeyCode() == KeyEvent.VK_RIGHT && e.getKeyCode() == KeyEvent.VK_UP) {
                    camera.moveCamera(-10, 10);
                }
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT && e.getKeyCode() == KeyEvent.VK_DOWN) {
                    camera.moveCamera(-10, -10);
                }
                else if (e.getKeyCode() == KeyEvent.VK_LEFT && e.getKeyCode() == KeyEvent.VK_UP) {
                    camera.moveCamera(10, 10);
                }
                else if (e.getKeyCode() == KeyEvent.VK_LEFT && e.getKeyCode() == KeyEvent.VK_DOWN) {
                    camera.moveCamera(10, -10);
                }
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    camera.moveCamera(-10, 0);
                }
                else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    camera.moveCamera(10, 0);
                }
                else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    camera.moveCamera(0, 10);
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    camera.moveCamera(0, -10);
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        frame.add(canvas);
        frame.pack();

    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }

    public Camera getCamera() {
        return camera;
    }

}
