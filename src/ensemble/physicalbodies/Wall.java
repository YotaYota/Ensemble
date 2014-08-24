package ensemble.physicalbodies;

import java.awt.*;

/**
 * Created by Johannes Jeppsson on 2014-08-24.
 */
public class Wall extends PhysicalBody {

    // Horizontal = 0, Vertical = 1
    public int ORIENTATION;

    public Wall(int x, int y, int width, int height, int orientation){
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
    }

    @Override
    public boolean hasCollidedWith(MovingBody otherBody) {
        return true;
    }

    @Override
    public void increment() {}
}