package experiment;

import ensemble.physicalbodies.Gas;
import ensemble.physicalbodies.Wall;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Johannes Jeppsson on 2014-08-24.
 */
public class Experiment extends Canvas implements Runnable {

    public static int HEIGHT = 800;
    public static int WIDTH = 600;
    private boolean running = false;
    private Thread thread;

    Handler handler;

    private Gas gas;
    private ArrayList<Wall> walls;

    public static void main(String args[]) {
        new Window(HEIGHT, WIDTH, "Canonical Ensemble", new Experiment());
    }

    private void init() {

        HEIGHT = getHeight();
        WIDTH = getWidth();

        handler = new Handler();

        gas = new Gas(100, WIDTH, HEIGHT);
        walls = new ArrayList<Wall>();

        // Creates a box
        Wall wallTop = new Wall(0, 0, WIDTH, 1, 0);
        Wall wallLeft = new Wall(0, 0, 1, HEIGHT, 1);
        Wall wallRight = new Wall(WIDTH-1, 0, 1, HEIGHT, 1);
        Wall wallBottom = new Wall(0, HEIGHT-1, WIDTH, 1, 0);
        walls.add(wallTop);
        walls.add(wallLeft);
        walls.add(wallRight);
        walls.add(wallBottom);

    }

    public synchronized void start() {

        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        init();
        this.requestFocus();

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        // Game loop
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                increment();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + ", Ticks: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(new Color(45, 25, 45));
        g.fillRect(0, 0, getWidth(), getHeight());

        //
        gas.render(g);
        for (Wall wall : walls) {
            wall.render(g);
        }
        //

        g.dispose();
        bs.show();

    }

    private void increment() {
        handler.increment();
    }
}
