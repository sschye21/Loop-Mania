package unsw.loopmania;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HelpControllerPause {

    private MenuSwitcher gameSwitcher;

    @FXML
    private Button back;

    @FXML
    void handleBackMain() throws IOException {
        gameSwitcher.switchMenu();
    }

    public void setHelpMainSwitcher(MenuSwitcher gameSwitcher) {
        this.gameSwitcher = gameSwitcher;
    }

}
