package unsw.loopmania;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelpScreenPause {

    private Stage stage;
    private String title;
    private HelpControllerPause controller;
    private Scene scene;

    public HelpScreenPause(Stage stage) throws IOException {
        this.stage = stage;
        title = "Help";

        controller = new HelpControllerPause();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HelpView.fxml"));
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

    public HelpControllerPause getController() {
        return controller;
    }
}

