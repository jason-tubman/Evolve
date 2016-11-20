package evolve;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;


/**
 * Created by Jason on 27/10/16.
 */
public class ProgramPanel {
    private int clicked = 0;
    private JFrame frame;
    private Canvas canvas;
    private JComboBox box;
    //Camera
    private Camera camera;
    private JButton playButton;
    private String title = "EVOLVE";
    private int width, height;
    private int totalNotch = 0;

    public ProgramPanel(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        camera = new Camera(0, 0);

        Vector stats = new Vector();

        stats.addElement("Speed");
        stats.addElement("Generation");
        stats.addElement("Size");
        stats.addElement("Digestion Time");
        stats.addElement("lifeTime");
        stats.addElement("Egg Time");
        this.box = new JComboBox(stats);

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
        canvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                clicked = 1;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

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

        frame.getContentPane().add(this.box);

        playButton = new JButton("EVOLVE");
        playButton.setBounds(880, 550, 150, 75);
        playButton.setVisible(true);
        frame.getContentPane().add(playButton);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButton.setVisible(false);
            }
        });



        frame.add(canvas);

        this.box.setVisible(false);
        this.box.setBounds(15, 125, 220, 30);

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

    public int returnClicked() {
        return clicked;
    }
    public void resetClicked() {
        clicked = 0;
    }
    public String getBoxValue() {
        return this.box.getSelectedItem().toString();
    }
    public JComboBox getBox() {
        return this.box;
    }
    public JButton getPlay(){
        return playButton;
    }

}
