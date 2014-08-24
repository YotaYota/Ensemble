package ensemble.physicalbodies;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Johannes Jeppsson on 2014-08-24.
 */
public class Gas extends PhysicalBody {

    public LinkedList<Particle> particles;

    public Gas(int N, int boundsX, int boundsY) {
        particles = new LinkedList<Particle>();
        populateGas(N, boundsX, boundsY);
    }

    // TODO : reuse code
    public void populateGas(int N, int boundsX, int boundsY) {
        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            Particle particle;
            switch (rand.nextInt(5)) {
                case 0:
                    particle = new Particle(rand.nextInt(boundsX - 2) + 1, rand.nextInt(boundsY - 2) - 1, -1, 0);
                    particles.add(particle);
                    break;
                case 1:
                    particle = new Particle(rand.nextInt(boundsX - 2) + 1, rand.nextInt(boundsY - 2) - 1, 0, -1);
                    particles.add(particle);
                    break;
                case 2:
                    particle = new Particle(rand.nextInt(boundsX - 2) + 1, rand.nextInt(boundsY - 2) - 1, 1, 0);
                    particles.add(particle);
                    break;
                case 3:
                    particle = new Particle(rand.nextInt(boundsX - 2) + 1, rand.nextInt(boundsY - 2) - 1, 0, 1);
                    particles.add(particle);
                    break;
                case 4:
                    particle = new Particle(rand.nextInt(boundsX - 2) + 1, rand.nextInt(boundsY - 2) - 1, -1, -1);
                    particles.add(particle);
                    break;
                case 5:
                    particle = new Particle(rand.nextInt(boundsX - 2) + 1, rand.nextInt(boundsY - 2) - 1, 1, 1);
                    particles.add(particle);
                    break;
            }
        }
    }

    public void increment() {
        for (Particle particle : particles) {
            particle.increment();
        }
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    public void render(Graphics g) {
        for (Particle particle : particles) {
            particle.render(g);
        }
    }

    @Override
    public boolean hasCollidedWith(MovingBody otherBody) {
        return false;
    }
}
