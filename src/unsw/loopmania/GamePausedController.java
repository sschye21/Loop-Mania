package unsw.loopmania;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.io.IOException;

public class GamePausedController {

    private MenuSwitcher mainSwitcher;
    private MenuSwitcher gameResumeSwitcher;

    private HelpScreenPause helpScreenPause;
    
    private SettingsScreenPause settingsScreenPause;
    private GoalsMenuScreen goalsMenuScreen;

    @FXML
    private Button resumeGameButton;

    @FXML
    private Button saveGameButton;

    @FXML
    private Button menuButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button goalsMenuButton;

    @FXML
    void handleHelp(ActionEvent event) {
        helpScreenPause.start();
    }

    public void setHelpScreen(HelpScreenPause helpScreenPause) {
        this.helpScreenPause = helpScreenPause;
    }

    public void setMainSwitcher(MenuSwitcher mainSwitcher) {
        this.mainSwitcher = mainSwitcher;
    }

    @FXML
    public void handleMenu() throws IOException {
        mainSwitcher.switchMenu();
    }

    public void setResumeSwitcher(MenuSwitcher gameResumeSwitcher){
        this.gameResumeSwitcher = gameResumeSwitcher;
    }

    @FXML
    void handleResumeGame() throws IOException {
        gameResumeSwitcher.switchMenu();
    }

    @FXML
    void handleSaveGame(ActionEvent event) {

    }

    @FXML
    void handleSettings(ActionEvent event) {
        settingsScreenPause.start();
    }

    public void setSettingsScreen(SettingsScreenPause settingsScreenPause ){
        this.settingsScreenPause = settingsScreenPause;
    }

    public void setGoalsMenuScreen(GoalsMenuScreen goalsMenuScreen) {
        this.goalsMenuScreen = goalsMenuScreen;
    }

    @FXML
    void handleGoalsMenu(ActionEvent event) {
        goalsMenuScreen.start();
    }

}
