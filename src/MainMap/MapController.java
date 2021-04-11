package MainMap;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.concurrent.Phaser;
import Planes.ArmyPlane;
import Planes.PassengerPlane;
import Planes.Plane;
import Ships.ArmyCarrier;
import Ships.PassengerShip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;

/**
 * The type Map controller.
 */
public class MapController implements Initializable {

    /**
     * The Map.
     */
    @FXML
    public Canvas map;
    private static final Vector<Entity> entities = new Vector<>();
    private static final Vector<Plane> planes = new Vector<>();
    private static final Vector<ArmyCarrier> armyShips = new Vector<>();
    private static final Phaser ph = new Phaser();

    /**
     * Instantiates a new Map controller.
     */
    public MapController() {}

    private void addEntity(Entity entity) {
        entities.add(entity);
        Thread th = new Thread(() -> {
            MapController.getPh().register();
            entity.run();
        });
        th.setDaemon(true);
        th.start();
    }

    /**
     * Create passenger plane.
     *
     * @param actionEvent the action event
     */
    public void createPassengerPlane(ActionEvent actionEvent) {
        PassengerPlane passengerPlane = new PassengerPlane(ph);
        if (passengerPlane.getRouteRegister() != null){
            addEntity(passengerPlane);
            planes.add(passengerPlane);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Stworzono nowy pojazd!");
            alert.setHeaderText(null);
            alert.initOwner(Main.getPrimaryStage());
            alert.setContentText(passengerPlane.showInfo());
            alert.showAndWait();
            alert.close();
        }
    }

    /**
     * Create army carrier.
     *
     * @param actionEvent the action event
     */
    public void createArmyCarrier(ActionEvent actionEvent) {
        ArmyCarrier armyCarrier = new ArmyCarrier(ph);
        addEntity(armyCarrier);
        armyShips.add(armyCarrier);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Stworzono nowy pojazd!");
        alert.setHeaderText(null);
        alert.initOwner(Main.getPrimaryStage());
        alert.setContentText(armyCarrier.showInfo());
        alert.showAndWait();
        alert.close();

    }

    /**
     * Create army plane.
     *
     * @param actionEvent the action event
     */
    public void createArmyPlane(ActionEvent actionEvent) {
        ArmyPlane armyPlane = new ArmyPlane(ph);
        if (armyPlane.getRouteRegister() != null){
            addEntity(armyPlane);
            planes.add(armyPlane);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Stworzono nowy pojazd!");
            alert.setHeaderText(null);
            alert.initOwner(Main.getPrimaryStage());
            alert.setContentText(armyPlane.showInfo());
            alert.showAndWait();
            alert.close();
        }
    }

    /**
     * Create passenger ship.
     *
     * @param actionEvent the action event
     */
    public void createPassengerShip(ActionEvent actionEvent) {
        PassengerShip passengerShip = new PassengerShip(ph);
        addEntity(passengerShip);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Stworzono nowy pojazd!");
        alert.setHeaderText(null);
        alert.initOwner(Main.getPrimaryStage());
        alert.setContentText(passengerShip.showInfo());
        alert.showAndWait();
        alert.close();
    }


    /**
     * Make map clickable.
     */
    public void clickable(){
        map.setOnMouseClicked(event -> {
            double x1 = Math.round(event.getX()), y1 = Math.round(event.getY());
            Entity theNearest = findTheNearest(x1, y1);
            OptionController.setEntity(theNearest);
            try {
                OptionController.showInformationPanel();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    /**
     * Refresh map.
     */
    public void refreshMap() {
        GraphicsContext gc = this.map.getGraphicsContext2D();
        gc.clearRect(0.0D, 0.0D, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    /**
     * Draw entity on map.
     *
     * @param entity the entity
     */
    public void drawMap(Entity entity) {
        GraphicsContext gc = this.map.getGraphicsContext2D();
        gc.drawImage((entity.getSprite()), entity.getX() % (this.map.getWidth() - entity.getSprite().getWidth()), entity.getY() % (this.map.getHeight() - entity.getSprite().getHeight()));
    }

    /**
     * Gets ph.
     *
     * @return the ph
     */
    public static Phaser getPh() {
        return ph;
    }

    /**
     * Gets all entities.
     *
     * @return the entities
     */
    public static Vector<Entity> getEntities() {
        return entities;
    }

    /**
     * Gets all planes.
     *
     * @return the planes
     */
    public static Vector<Plane> getPlanes() {
        return planes;
    }

    /**
     * Gets all army ships.
     *
     * @return the army ships
     */
    public static Vector<ArmyCarrier> getArmyShips() {
        return armyShips;
    }
    private Entity findTheNearest(double x1, double y1){
        Entity theNearest = null;
        double minn = 1000;
        for (Entity entity : MapController.getEntities()) {
            double x2 = entity.getX();
            double y2 = entity.getY();
            double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
            if (distance < minn) {
                minn = distance;
                theNearest = entity;
            }
        }
        return theNearest;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}

