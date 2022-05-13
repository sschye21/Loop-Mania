package unsw.loopmania;

import javafx.scene.control.Button;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;

public class MainMenuController {

    private ModeMenuScreen modeMenuScreen;
    private SettingsScreen settingsScreen;
    private HelpScreen helpScreen;

    @FXML
    private Button startGameButton;

    @FXML
    private Button loadGameButton;

    @FXML
    private Button settings;

    @FXML
    private Button help;

    @FXML
    private Button quit;

    @FXML
    public void handleHelp(ActionEvent event) {
        helpScreen.start();
    }

    public void setHelpScreen(HelpScreen helpScreen) {
        this.helpScreen = helpScreen;
    }

    @FXML
    public void handleLoadGame(ActionEvent event) {

    }

    @FXML
    public void handleQuit(ActionEvent event) {
        Platform.exit();
    }

    public void setSettingsScreen(SettingsScreen settingsScreen) {
        this.settingsScreen = settingsScreen;
    }

    @FXML
    public void handleSettings(ActionEvent event) {
        settingsScreen.start();
    }

    @FXML
    public void handleStart(ActionEvent event) {
        modeMenuScreen.start();
    }

    public void setModeMenuScreen(ModeMenuScreen modeMenuScreen) {
        this.modeMenuScreen = modeMenuScreen;
    }
}