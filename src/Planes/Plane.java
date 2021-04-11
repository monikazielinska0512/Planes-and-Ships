package Planes;

import Airports.*;
import MainMap.Entity;
import MainMap.MapController;
import javafx.scene.image.Image;
import java.util.*;
import java.util.concurrent.Phaser;

/**
 * Class represents as "Mother" of all planes on the map
 */
public abstract class Plane extends Entity {
    /**
     * The Route register.
     */
    protected List<Airport> routeRegister;
    private final int staff;
    private int actualPetrol;
    private final int maxPetrol;
    private final int ID;
    /**
     * The Type.
     */
    protected String type;
    /**
     * The Next landing.
     */
    protected Airport nextLanding;

    /**
     * Instantiates a new Plane.
     *
     * @param image the image
     * @param ph    the ph
     */
    public Plane(Image image, Phaser ph) {
        super(image, ph);
        Random rand = new Random();
        this.staff = rand.nextInt(20);
        this.maxPetrol = 1000;
        this.actualPetrol = this.maxPetrol;
        this.ID = rand.nextInt(10000);
        this.routeChange = false;
    }

    /**
     * Sets route register.
     *
     * @param startAirport the start airport
     * @param endAirport   the end airport
     */
    public void setRouteRegister(Airport startAirport, Airport endAirport) {
        pathUnweighted path = new pathUnweighted(Integer.parseInt(startAirport.getName()), Integer.parseInt(endAirport.getName()));
        LinkedList<Integer> pathie = path.getPath();
        List<Airport> finalRoute = new ArrayList<Airport>();
        try {
            if (pathie != null) {
                for (Integer p : pathie) {
                    for (Airport airport : AirportsRegister.getAirports()) {
                        if (p == Integer.parseInt(airport.getName())) {
                            finalRoute.add(airport);
                        }
                    }
                }
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        this.routeRegister = finalRoute;
    }

    /**
     * Move.
     *
     * @param airport the airport
     */
    protected void move(Airport airport) {
        double speed = 0.7;
        int waiting = 0;

        while (true) {
            if (this.routeChange == false) {
                if (fault == false) {
                        if (Math.round(this.getX()) == airport.getX() & Math.round(this.getY()) == airport.getY()) {
                            this.tank();
                            if (this.type.equals(airport.getType())) {
                                waiting += 1;
                                modifyCoords(0, 0);
                                if (waiting == 100) {
                                    waiting = 0;
                                    break;
                                }
                            } else {
                                modifyCoords(0, 0);
                                break;
                            }
                        } else {
                            double direction = this.setDirection(airport.getX(), airport.getY());
                            double x1 = this.getX() + (speed * Math.cos(direction));
                            double y1 = this.getY() + (speed * Math.sin(direction));
                            this.setActualPetrol();
                            this.setX(x1);
                            this.setY(y1);
                            this.getPh().arriveAndAwaitAdvance();
                        }
                    }
                    else {

                        modifyCoords(0,0);
                        break;
                    }
                } else {
                    modifyCoords(0, 0);
                    break;
                }
            this.getPh().arriveAndAwaitAdvance();
            }
        }

    /**
     * Sets direction.
     *
     * @param x the x
     * @param y the y
     * @return the direction
     */
    protected double setDirection(double x, double y) {
        double deltaX = x - this.getX();
        double deltaY = y - this.getY();
        return Math.atan2(deltaY, deltaX);
    }

    /**
     * Gets route register.
     *
     * @return the route register
     */
    public List<Airport> getRouteRegister() {
        return routeRegister;
    }

    /**
     * Go.
     *
     * @param routeRegister the route register
     */
    public void go(List<Airport> routeRegister){
        for (int i = 0; i < routeRegister.size(); i++) {
            if (this.fault == false) {
                if (this.routeChange == false) {
                    this.nextLanding = routeRegister.get(i);
                    this.move(routeRegister.get(i));
                }
                else{
                    this.move(findTheNearest());
                    break;
                }
            } else {
                break;
            }
        }
        setFault(false);
        this.move(findTheNearest());
    }

    @Override
    public void run() {
        go(routeRegister);
        if (routeChange == true) {
            Random rand = new Random();
            int index = rand.nextInt(AirportsRegister.getAirports().size());
            Airport airport = AirportsRegister.getAirports().get(index);
            setRouteChange(false);
            setRouteRegister(findTheNearest(), airport);
            go(routeRegister);
        }
        this.getPh().arriveAndDeregister();
        MapController.getEntities().remove(this);
    }

    private Airport findTheNearest() {
        Airport theNearest = null;
        double minn = 1000;
        double x2 = this.getX();
        double y2 = this.getY();
        for (Airport airport : AirportsRegister.getAirports()) {
            double x1 = airport.getX();
            double y1 = airport.getY();
            double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
            if (distance < minn) {
                minn = distance;
                theNearest = airport;
            }
        }
        return theNearest;
    }


    /**
     * Sets actual petrol.
     */
    protected void setActualPetrol() {
        this.actualPetrol -= 1;
    }

    /**
     * Tank.
     */
    protected void tank() {
        this.actualPetrol = this.maxPetrol;
    }

    /**
     * Gets staff.
     *
     * @return the staff
     */
    public int getStaff() {
        return staff;
    }

    /**
     * Gets actual petrol.
     *
     * @return the actual petrol
     */
    public int getActualPetrol() {
        return actualPetrol;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getID() {
        return ID;
    }
}

