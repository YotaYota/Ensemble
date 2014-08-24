package ensemble.physicalbodies;

import java.awt.*;

/**
 * Created by Johannes Jeppsson on 2014-08-24.
 */
public abstract class PhysicalBody implements MovingBody {

    // TODO: A bit nested... should clear out what is the highest instance that should be included in the experiment
    // and what methods they should include
    int width;
    int height;
    int x;
    int y;
    Rectangle rect;

    public abstract Rectangle getBounds();

    public abstract void render(Graphics g);

    public abstract boolean hasCollidedWith(MovingBody otherBody);
}