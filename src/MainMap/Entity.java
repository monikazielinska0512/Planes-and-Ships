package MainMap;

import javafx.scene.image.Image;
import java.util.concurrent.Phaser;


/**
 * It's probably main class of all programme. Implements interface Runnable, what caused that all vehicles on map are threads
 */
public abstract class Entity implements Runnable {
    private final Image sprite;
    private double x;
    private double y;
    private final Phaser ph;
    /**
     * The Fault.
     */
    protected boolean fault = false;
    /**
     * The Route change.
     */
    protected boolean routeChange = false;

    /**
     * Instantiates a new Entity.
     *
     * @param image the image
     * @param ph    the ph
     */
    public Entity(Image image, Phaser ph) {
        this.sprite = image;
        this.ph = ph;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Modify coords.
     *
     * @param x the x
     * @param y the y
     */
    public void modifyCoords(double x, double y) {
        this.x += x;
        this.y += y;
    }

    /**
     * Sets route change.
     *
     * @param routeChange the route change
     */
    public void setRouteChange(boolean routeChange) {
        this.routeChange = routeChange;
    }

    /**
     * Sets fault.
     *
     * @param fault the fault
     */
    public void setFault(boolean fault) {
        this.fault = fault;
    }

    /**
     * Gets sprite.
     *
     * @return the sprite
     */
    public Image getSprite() {
        return this.sprite;
    }

    /**
     * Gets ph.
     *
     * @return the ph
     */
    public Phaser getPh() {
        return ph;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Show info string.
     *
     * @return the string
     */
    public String showInfo() {
        return "Entity";
    }
}

