package Ships;

import javafx.scene.image.Image;
import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * The type Army carrier.
 */
public class ArmyCarrier extends Ship {
    private String armamentType;
    private final int ID;

    /**
     * Instantiates a new Army carrier.
     *
     * @param ph the ph
     */
    public ArmyCarrier(Phaser ph) {
        super(new Image("MainMap/Pictures/green.png"), ph);
        Random rand = new Random();
        setArmamentType();
        this.ID = rand.nextInt(20000);
        this.setX(32);
        this.setY(32);
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public String showInfo() {
        String str = String.format("Typ: Statek wojskowy\n" +
                "Pozycja aktualna X: %f, Y: %f\n" +
                "Moje ID: %s\n" +
                "Typ uzbrojenia: %s",this.getX(), this.getY(), this.ID, this.armamentType);
        return str;
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
     * Gets armament type.
     *
     * @return the armament type
     */
    public String getArmamentType() {
        return armamentType; }



}

