package unsw.loopmania;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelpScreen {

    private Stage stage;
    private String title;
    private HelpController controller;
    private Scene scene;

    public HelpScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Help";

        controller = new HelpController();
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

    public HelpController getController() {
        return controller;
    }
}

