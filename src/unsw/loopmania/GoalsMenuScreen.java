package unsw.loopmania;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GoalsMenuScreen {
    
    private Stage stage;
    private String title;
    private GoalsMenuController controller;
    private Scene scene;

    public GoalsMenuScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Goals Menu";

        controller = new GoalsMenuController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GoalsMenuView.fxml"));
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
    
    public GoalsMenuController getController() {
        return controller;
    }
}

