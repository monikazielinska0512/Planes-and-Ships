package Planes;

import Airports.Airport;
import Airports.AirportsRegister;
import Airports.pathUnweighted;
import MainMap.MapController;
import Ships.ArmyCarrier;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * The type Army plane.
 */
public class ArmyPlane extends Plane {
    private String armamentType;
    private Airport start;

    /**
     * Instantiates a new Army plane.
     *
     * @param ph the ph
     */
    public ArmyPlane(Phaser ph) {
        super(new Image("MainMap/Pictures/blue.jpg"), ph);
        this.type = "Army";
        new AirportsRegister();
        Random rand = new Random();
        int index  = rand.nextInt(AirportsRegister.getArmyAirports().size());
        int index2 = rand.nextInt(AirportsRegister.getArmyAirports().size());
        Airport startAirport = AirportsRegister.getArmyAirports().get(index);
        this.start = startAirport;
        Airport endAirport = AirportsRegister.getArmyAirports().get(index2);
        setRouteRegister(startAirport, endAirport);
        setArmamentType();
        setStart();
        this.nextLanding = this.routeRegister.get(0);
        }

    @Override
    public void run() {
        findTheNearest();
        super.run();
    }

    /**
     * Gets armament type.
     *
     * @return the armament type
     */
    public String getArmamentType() {
        return armamentType;
    }

    /**
     * Sets armament type.
     */
    public void setArmamentType() {
        Random rand = new Random();
        String[] types = {"Bomber", "Spacecraft"};
        int index1 = rand.nextInt(types.length);
        this.armamentType = types[index1];
    }

    /**
     * Sets start.
     */
    public void setStart() {
        for (ArmyCarrier carrier : MapController.getArmyShips()) {
            if (carrier.getArmamentType().equals(this.getArmamentType())) {
                this.setX(carrier.getX());
                this.setY(carrier.getY());
                break;
            }
        }
    }

    @Override
    public String showInfo() {
        String str = String.format("Typ: Samolot wojskowy\n" +
                "Następne ladowanie lotnisko nr %s\n"+
                "Pozycja aktualna X: %f, Y: %f\n" +
                "Moje ID: %s\n" +
                "Typ uzbrojenia: %s"+
                "Aktualna ilość paliwa %d", this.nextLanding.getName(), this.getX(), this.getY(), this.getID(), this.armamentType, this.getActualPetrol());
        return str;
    }

    /**
     * Sets route register 1.
     *
     * @param startAirport the start airport
     * @param endAirport   the end airport
     * @return the route register 1
     */
    public List<Airport> setRouteRegister1(Airport startAirport, Airport endAirport) {
        pathUnweighted path = new pathUnweighted(Integer.parseInt(startAirport.getName()), Integer.parseInt(endAirport.getName()));
        LinkedList<Integer> pathie = path.getPath();
        List<Airport> finalRoute = new ArrayList<Airport>();
        for (Integer p : pathie) {
            for (Airport airport : AirportsRegister.getAirports()) {
                if (p == Integer.parseInt(airport.getName())) {
                    finalRoute.add(airport);
                }
            }
        }
//        this.routeRegister = finalRoute;
        return finalRoute;
    }


    private void findTheNearest(){
        Airport theNearest = null;
        double minn = 1000;
        double x2 = this.getX();
        double y2 = this.getY();
        for (Airport airport : AirportsRegister.getAirports()) {
            double x1 = airport.getX();
            double y1 = airport.getY();
            double distance = Math.sqrt((x2 - airport.getX()) * (x2 - x1) + (y2 - y1) * (y2 - y1));
            if (distance < minn) {
                minn = distance;
                theNearest = airport;
            }
        }
        assert theNearest != null;
        List<Airport> begging = setRouteRegister1(theNearest, start);
        for (int i = 0; i < begging.size(); i++) {
            this.move(begging.get(i));
        }
    }
}