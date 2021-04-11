package Planes;

import Airports.Airport;
import Airports.AirportsRegister;
import javafx.scene.image.Image;
import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * Creating objects which can only goes to the Civil Airports, they change Passenger and move on air routes.
 */
public class PassengerPlane extends Plane {
    private int maxCapacity;
    private int actualCapacity;
    /**
     * The Next landing.
     */
    protected Airport nextLanding;

    /**
     * Instantiates a new Passenger plane.
     *
     * @param ph the ph
     */
    public PassengerPlane(Phaser ph) {
        super(new Image("MainMap/Pictures/unnamed.png"), ph);
        this.type = "Civil";
        Random rand = new Random();
        int index  = rand.nextInt(AirportsRegister.getCivilAirports().size());
        int index2 = rand.nextInt(AirportsRegister.getCivilAirports().size());;
        Airport startAirport = AirportsRegister.getCivilAirports().get(index);
        Airport endAirport = AirportsRegister.getCivilAirports().get(index2);
        setRouteRegister(startAirport, endAirport);
        setCapacities();
        setX(this.routeRegister.get(0).getX());
        setY(this.routeRegister.get(0).getY());
        this.nextLanding = this.routeRegister.get(0);
    }

    private void setCapacities(){
        Random rand = new Random();
        int[] capacities = {100, 200, 300, 400, 500};
        int index1 = rand.nextInt(capacities.length);
        this.maxCapacity = capacities[index1] + this.getStaff();
        this.actualCapacity = rand.nextInt(maxCapacity) ;
    }

    @Override
    protected void move(Airport airport) {
        double speed = 0.7;
        int waiting = 0;

        while (true) {
            if (this.routeChange == false) {
                if (fault == false) {
                    if (Math.round(this.getX()) == airport.getX() & Math.round(this.getY()) == airport.getY()) {
                        this.tank();
                        this.changePassengers();
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

    @Override
    public void run() {
        super.run();
    }

    @Override
    public String showInfo() {
        String str = String.format("Typ: Samolot pasażerski\n" +
                "Pozycja aktualna X: %f, Y: %f\n" +
                "Następne ladowanie lotnisko nr %s\n"+
                "Moje ID: %s\n" +
                "Aktualna pojemność statku: %d\n" +
                "Maksymalna pojemność statku: %d\n" +
                "Aktualna ilość paliwa %d", this.getX(), this.getY(), this.nextLanding.getName(), this.actualCapacity, this.maxCapacity, this.getID(), this.getActualPetrol());
        return str;
    }

    /**
     * Change passengers.
     */
    public void changePassengers(){
        Random rand = new Random();
        this.actualCapacity = rand.nextInt(this.maxCapacity);
    }
}