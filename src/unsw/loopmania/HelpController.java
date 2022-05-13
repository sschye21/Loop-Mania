package unsw.loopmania;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HelpController {

    private MenuSwitcher mainSwitcher;

    @FXML
    private Button back;

    @FXML
    void handleBackMain() throws IOException {
        mainSwitcher.switchMenu();
    }

    public void setHelpMainSwitcher(MenuSwitcher mainSwitcher) {
        this.mainSwitcher = mainSwitcher;
    }

}
