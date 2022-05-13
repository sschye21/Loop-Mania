package unsw.loopmania;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShopScreen {

    private Stage stage;
    private String title;
    private ShopController controller;
    private Scene scene;

    public ShopScreen(Stage stage, LoopManiaWorldController worldController, ModeMenuController modeController)
            throws IOException {
        this.stage = stage;
        title = "Shop Menu";

        controller = new ShopController(worldController, modeController);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShopView.fxml"));
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

    public ShopController getController() {
        return controller;
    }
}
