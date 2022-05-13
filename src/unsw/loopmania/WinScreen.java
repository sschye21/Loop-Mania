package unsw.loopmania;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WinScreen {

    private Stage stage;
    private String title;
    private WinController controller;
    private Scene scene;

    public WinScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Mode Menu";

        controller = new WinController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WinView.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public WinController getController() {
        return controller;
    }
}


