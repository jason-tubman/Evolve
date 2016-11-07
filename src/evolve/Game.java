package evolve;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;

/**
 * Created by Jason on 27/10/16.
 */
public class Game  implements Runnable {
    private ProgramPanel panel;
    private float framesPerSecondCount = 0;
    private int seconds = 0;
    private BufferStrategy bs;
    private Graphics g;

    private int width, height;

    private String title;

    private Thread thread;

    private boolean running = false;
    private World world = new World(this, 1920, 1080);

    //States
    private GameState gameState;
    private State menuState;


    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    private void init(){
        this.panel = new ProgramPanel(title, this.width, this.height);
        Assets.init();

        gameState = new GameState(this, world);
        menuState = new MenuState(this);
        State.setState(gameState);

    }

    private void render() {
        bs = panel.getCanvas().getBufferStrategy();
        if (bs == null) {
            panel.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);
        Graphics2D g2d = (Graphics2D)g;
        g2d.scale(this.getPanel().getCamera().getZoom(), this.getPanel().getCamera().getZoom());
        g2d.translate(this.getPanel().getCamera().getXoffset(), this.getPanel().getCamera().getYOffset());
        if (State.getState() != null) {
            State.getState().render(g2d);
        }

        bs.show();
        g.dispose();
        g2d.dispose();

    }

    private void tick() {

        if (State.getState() != null) {
            State.getState().tick();
        }

    }


    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long timer = 0;
        int ticks = 0;
        long lastTime = System.nanoTime();

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            if (timer >= 1000000000) {
                seconds++;
                framesPerSecondCount = ticks;
                ticks = 0;
                timer = 0;

            }
        }

        stop();
    }

    public synchronized void start(){
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public double getFPS() {
        return framesPerSecondCount;
    }

    public World getWorld() {
        return world;
    }

    public ProgramPanel getPanel() {
        return this.panel;
    }

    public int getSeconds() {

        return this.seconds;
    }

    public GameState getGameState() {
        return this.gameState;
    }
}
