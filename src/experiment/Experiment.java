package experiment;

import ensemble.physicalbodies.Gas;
import ensemble.physicalbodies.Particle;
import ensemble.physicalbodies.Wall;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 * Created by Johannes Jeppsson on 2014-08-24.
 *
 * Particles are not interacting with each other, they can only bounce against the walls.
 */
public class Experiment extends Canvas implements Runnable {

    public static int HEIGHT = 800;
    public static int WIDTH = 600;
    Handler handler;
    private boolean running = false;
    private Thread thread;
    private Gas gas;
    private ArrayList<Wall> walls;

    public static void main(String args[]) {
       new Window(HEIGHT, WIDTH, "Canonical Ensemble", new Experiment());
    }

    // TODO : make clearer distinction between gas/walls and handler.
    private void init() {

        HEIGHT = getHeight();
        WIDTH = getWidth();

        handler = new Handler();

        gas = new Gas(10000, WIDTH, HEIGHT);
        walls = new ArrayList<Wall>();

        // TODO: move to handler
        // Creates a box
        Wall wallTop = new Wall(0, 0, WIDTH, 1, 0);
        Wall wallLeft = new Wall(0, 0, 1, HEIGHT, 1);
        Wall wallRight = new Wall(WIDTH - 1, 0, 1, HEIGHT, 1);
        Wall wallBottom = new Wall(0, HEIGHT - 1, WIDTH, 1, 0);
        walls.add(wallTop);
        walls.add(wallLeft);
        walls.add(wallRight);
        walls.add(wallBottom);

        handler.addObject(gas);
        handler.addObject(wallTop);
        handler.addObject(wallLeft);
        handler.addObject(wallRight);
        handler.addObject(wallBottom);

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
                checkCollisions();
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
        handler.render(g);
        //

        g.dispose();
        bs.show();

    }

    private void increment() {
        handler.increment();
    }

    // TODO : this code should be more general and go into the handler class.
    private void checkCollisions() {
        for (Particle p : gas.particles) {
            for (Wall w : walls) {
                if (p.getBounds().intersects(w.getBounds())) {
                    p.hasCollidedWith(w);
                    w.hasCollidedWith(p);
                }
            }
        }
    }
}
