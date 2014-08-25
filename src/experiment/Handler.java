package experiment;

import ensemble.physicalbodies.Gas;
import ensemble.physicalbodies.Particle;
import ensemble.physicalbodies.PhysicalBody;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Johannes Jeppsson on 2014-08-24.
 */
public class Handler {
    public LinkedList<PhysicalBody> objects = new LinkedList<PhysicalBody>();

    public void increment() {
        //checkCollisions();
        for (PhysicalBody object : objects) {
            object.increment();
        }
    }

    public void render(Graphics g) {
        for (PhysicalBody object : objects) {
            object.render(g);
        }
    }

    public void addObject(PhysicalBody body) {
        objects.add(body);
    }

    // TODO: gas is also a container -> find out a good way to get the bounds of each particle in a gas.
    public void checkCollisions() {
        for (PhysicalBody object1 : objects) {
            for (PhysicalBody object2 : objects) {
                if (object1.equals(object2)) continue;
                if (object1.getBounds().intersects(object2.getBounds())) {
                    object1.hasCollidedWith(object2);
                    object2.hasCollidedWith(object1);
                }
            }
        }
    }
}
