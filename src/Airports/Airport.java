package Airports;

import java.util.Random;

/**
 * Class Airport is absract class, which represents some points where all vehicles go there.
 */
public abstract class Airport {
    private final String type;
    private final String name;
    private final double x;
    private final double y;
    private final int maxCapacity;
    private int actualCapacity;

    /**
     * Instantiates a new Airport.
     *
     * @param type the type
     * @param x    the x
     * @param y    the y
     * @param name the name
     */
    public Airport(String type, double x, double y, String name) {
        Random rand = new Random();
        this.type = type;
        this.x = x;
        this.y = y;
        this.maxCapacity = rand.nextInt(5)+1;
        this.actualCapacity = 0;
        this.name = name;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

}
