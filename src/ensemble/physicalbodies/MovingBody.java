package ensemble.physicalbodies;

import java.awt.*;

/**
 * Created by Johannes Jeppsson on 2014-08-24.
 */
public interface MovingBody {

    int dx = 0;
    int dy = 0;

    public void increment();
}
