package MainMap;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Option controller.
 */
public class OptionController implements Initializable {

    private static Entity entity;
    private static final Stage stage = new Stage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Show information panel.
     */
    public static void  showInformationPanel(){
        FXMLLoader loader = new FXMLLoader(OptionController.class.getResource("/MainMap/Option.fxml"));
        try {
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            stage.initOwner(Main.getPrimaryStage());
            stage.initModality(Modality.APPLICATION_MODAL);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        stage.setTitle("Panel Informacyjny");
        stage.showAndWait();
    }

    /**
     * Remove.
     */
    public void remove() {
        MapController.getEntities().remove(entity);
        stage.close();
    }

    /**
     * Change route.
     */
    public void changeRoute() {
        entity.setRouteChange(true);
        stage.close();
    }

    /**
     * Show info.
     */
    public void showInfo(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacje o pojeździe");
        alert.setHeaderText(null);
        alert.setContentText(entity.showInfo());
        alert.showAndWait();
        alert.close();
        stage.close();
    }

    /**
     * Fault.
     */
    public void fault() {
        entity.setFault(true);
        stage.close();
    }

    /**
     * Sets entity.
     *
     * @param entity1 the entity 1
     */
    public static void setEntity(Entity entity1) {
        entity = entity1;
    }

}
