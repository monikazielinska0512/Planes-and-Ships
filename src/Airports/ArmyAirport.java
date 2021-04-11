package Airports;


/**
 * Objects of Army Airport Class, can take to yourself ony planes, which are army planes
 */
public class ArmyAirport extends Airport {
    /**
     * Instantiates a new Army airport.
     *
     * @param x    the x
     * @param y    the y
     * @param name the name
     */
    public ArmyAirport( double x, double y, String name) {
        super( "Army", x, y,name );
    }
}

