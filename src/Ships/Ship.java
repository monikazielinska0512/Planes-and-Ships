package Ships;

import MainMap.Entity;
import javafx.scene.image.Image;
import java.util.ConcurrentModificationException;
import java.util.concurrent.Phaser;

/**
 * Abstract class which is "mother" of all ships on the map. There's implementation of move method and run method
 */
public abstract class Ship extends Entity{

    /**
     * Instantiates a new Ship.
     *
     * @param image the image
     * @param ph    the ph
     */
    public Ship(Image image, Phaser ph) {
        super(image, ph);
    }

    /**
     * Move.
     *
     * @param x the x
     * @param y the y
     */
    protected void move(double x, double y) {
        double speed = 1;
        while (true) {
            double direction = this.setDirection(x, y);
            double x1 = this.getX() + (speed * Math.cos(direction));
            double y1 = this.getY() + (speed * Math.sin(direction));
            this.setX(x1);
            this.setY(y1);
            if (this.getX() == x & this.getY() == y) {
                setX(x);
                setY(y);
                break;
            }
            this.getPh().arriveAndAwaitAdvance();
        }
    }

    private double setDirection(double x, double y){
        double deltaX = x - this.getX();
        double deltaY = y - this.getY();
        return Math.atan2(deltaY,deltaX);
    }

    @Override
    public void run(){
        try {
            while(true) {
                this.move(32, 492);
                this.move(667, 492);
                this.move(667, 40);
                this.move(40, 40);
            }
        }
        catch (ConcurrentModificationException e) {
            e.printStackTrace();
        }
    }
}
