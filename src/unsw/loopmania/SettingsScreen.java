package unsw.loopmania;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SettingsScreen {
    private Stage stage;
    private String title;
    private SettingsMenuController controller;
    private Scene scene;

    public SettingsScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Settings";

        controller = new SettingsMenuController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SettingsMenuView.fxml"));
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
    
    public SettingsMenuController getController() {
        return controller;
    }
}

