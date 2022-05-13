package unsw.loopmania;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class WinController {

    private MenuSwitcher mainSwitcher;

    //@FXML
    //private Button exit;

    @FXML
    void handleExit(ActionEvent event) {
        mainSwitcher.switchMenu();
    }

    public void setWinMainSwitcher(MenuSwitcher mainSwitcher) {
        this.mainSwitcher = mainSwitcher;
    }

}

