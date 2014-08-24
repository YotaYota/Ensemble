package ensemble.physicalbodies;

import java.awt.*;

/**
 * Created by Johannes Jeppsson on 2014-08-24.
 */
public class Particle extends PhysicalBody implements MovingBody {

    int width = 1;
    int height = 1;
    int x, y;
    int dx, dy;

    public Particle(int x, int y){
        this.x = x;
        this.y = y;
        rect  = new Rectangle(x, y, width, height);
    }

    public Particle(int x, int y, int dx, int dy){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        rect  = new Rectangle(x, y, width, height);
    }

    public void increment(){
        x += dx;
        y += dy;
    }

    public Rectangle getBounds() {
        return new Rectangle( x, y, width, height);
    }

    public void render(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(x,y, width, height);
    }

    // TODO : make sure this if statement works
    @Override
    public boolean hasCollidedWith(MovingBody otherBody) {
        if (otherBody.getClass() ==  Wall.class ) {
            Wall wall = (Wall)otherBody;
            switch (wall.ORIENTATION) {
                case 0: dy *= (-1);
                        break;
                case 1: dx *= (-1);
                        break;
            }
            return true;
        }
        return false;
    }
}