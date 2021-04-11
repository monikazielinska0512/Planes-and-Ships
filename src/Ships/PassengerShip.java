package Ships;

import javafx.scene.image.Image;
import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * The type Passenger ship.
 */
public class PassengerShip extends Ship {
    private final int maxCapacity;
    private final int actualCapacity;
    private String company;
    private final int ID;

    /**
     * Instantiates a new Passenger ship.
     *
     * @param ph the ph
     */
    public PassengerShip(Phaser ph) {
        super(new Image("MainMap/Pictures/yellow.png"), ph);
        Random rand = new Random();
        setCompany();
        int[] capacities = {1000, 2000, 3000, 4000, 5000};
        int index = rand.nextInt(capacities.length);
        this.ID = rand.nextInt(20000);
        this.maxCapacity = capacities[index];
        this.actualCapacity = rand.nextInt(maxCapacity);
        this.setX(32);
        this.setY(32);
    }

    private void setCompany(){
        Random rand = new Random();
        String[] companies = {"Firma A", "Firma B", "Firma C"};
        int index = rand.nextInt(companies.length);
        this.company = companies[index];
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getID() {
        return ID;
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public String showInfo() {
        String str = String.format("Typ: Statek pasażerski\n" +
                "Pozycja aktualna X: %f, Y: %f\n" +
                "Moje ID: %s\n" +
                "Firma: %s\n" +
                "Maksymalna pojemność: %d\n" +
                "Aktualna pojemność: %d\n", this.getX(), this.getY(), this.getID(), this.company, this.maxCapacity, this.actualCapacity);
        return str;
    }
}