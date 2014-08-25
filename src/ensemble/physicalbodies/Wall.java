package ensemble.physicalbodies;

import java.awt.*;

/**
 * Created by Johannes Jeppsson on 2014-08-24.
 */
public class Wall extends PhysicalBody {

    // Horizontal = 0, Vertical = 1
    public int ORIENTATION;
    public int collisionCounter = 0;

    public Wall(int x, int y, int width, int height, int orientation) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rect = new Rectangle(x, y, width, height);
        ORIENTATION = orientation;
    }

    @Override
    public Rectangle getBounds() {
        return rect;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLUE);
        g.drawString("Collisions: " + collisionCounter, x + width / 2, y + height / 2);
    }

    @Override
    public boolean hasCollidedWith(MovingBody otherBody) {
        collisionCounter += 1;
        return true;
    }

    @Override
    public void increment() {
    }
}