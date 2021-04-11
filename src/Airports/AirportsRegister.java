package Airports;

import java.util.Vector;

/**
 * The type Airports register. Represents all airports on map.
 */
public class AirportsRegister {
    private static final Vector<Airport> airports = new Vector<>();
    private static final Vector<CivilAirport> civilAirports = new Vector<>();
    private static final Vector<ArmyAirport> armyAirports = new Vector<>();

    /**
     * Instantiates a new Airports register.
     */
    public AirportsRegister() {
        //Civil Airports
        CivilAirport airport0 = new CivilAirport(287.0, 152.0, "0");
        ArmyAirport airport1 = new ArmyAirport(235.0, 240.0, "1");
        CivilAirport airport2 = new CivilAirport(340.0, 253.0, "2");
        CivilAirport airport3 = new CivilAirport(277, 332.0, "3");
        ArmyAirport airport4 = new ArmyAirport(365.0, 346.0,"4");
        CivilAirport airport5 = new CivilAirport(509.0, 346.0,"5");
        CivilAirport airport6 = new CivilAirport(429.0, 281.0,"6");
        ArmyAirport airport7 = new ArmyAirport(504.0, 237.0,"7");
        CivilAirport airport8 = new CivilAirport(443.0, 184.0,"8");
        ArmyAirport airport9 = new ArmyAirport(371.0, 192.0,"9");


        airports.add(airport0);
        airports.add(airport1);
        airports.add(airport2);
        airports.add(airport3);
        airports.add(airport4);
        airports.add(airport5);
        airports.add(airport6);
        airports.add(airport7);
        airports.add(airport8);
        airports.add(airport9);

        for (Airport airport : airports) {
            if (airport.getType().equals("Civil")) {
                civilAirports.add((CivilAirport) airport);
            } else {
                armyAirports.add((ArmyAirport) airport);
            }
        }
    }

    /**
     * Gets airports.
     *
     * @return the airports
     */
    public static Vector<Airport> getAirports() {
        return airports;
    }

    /**
     * Gets civil airports.
     *
     * @return the civil airports
     */
    public static Vector<CivilAirport> getCivilAirports() {
        return civilAirports;
    }

    /**
     * Gets army airports.
     *
     * @return the army airports
     */
    public static Vector<ArmyAirport> getArmyAirports() {
        return armyAirports;
    }
}

