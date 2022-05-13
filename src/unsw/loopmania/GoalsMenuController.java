package unsw.loopmania;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class GoalsMenuController {
    
    @FXML
    private Button returnGoalsMenu;

    private MenuSwitcher mainSwitcher;

    @FXML
    public void handleGoalsMenu() throws IOException {
        mainSwitcher.switchMenu();
    }

    public void setGoalsMenuMainSwitcher(MenuSwitcher mainSwitcher) {
        this.mainSwitcher = mainSwitcher;
    }
}
