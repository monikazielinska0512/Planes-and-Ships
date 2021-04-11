package MainMap;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import Airports.AirportsRegister;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This class is responsible for start window and main thread of application.
 */
public class Main extends Application {
    private static Stage primaryStage;

    /**
     * Instantiates a new Main.
     */
    public Main() {}
    public void init() throws Exception {
        super.init();
    }

    public void start(Stage primaryStage) throws IOException {
        Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle("Świat samolotów i statków");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/MainMap/sample.fxml"));
        BorderPane mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
        MapController controller = loader.getController();
        MapController.getPh().register();

        new Thread(() -> {
            new AirportsRegister();
            while (true) {
                try {
                    controller.clickable();
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                            controller.refreshMap();
                            try {
                                for (Entity entity : MapController.getEntities()) {
                                    controller.drawMap(entity);
                                }
                            }
                            catch (ConcurrentModificationException e){
                                e.printStackTrace();
                            }
                    MapController.getPh().arriveAndAwaitAdvance();
                });
            }
        }).start();
    }

    /**
     * Gets primary stage.
     *
     * @return the primary stage
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main (String[]args){
        launch(args);
    }
}



