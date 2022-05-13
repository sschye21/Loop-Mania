package unsw.loopmania;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 * controller for the main menu.
 */
public class ModeMenuController {
    /**
     * facilitates switching to main game
     */
    private MenuSwitcher gameSwitcher;
    private String gameMode;

    public void setGameSwitcher(MenuSwitcher gameSwitcher) {
        this.gameSwitcher = gameSwitcher;
    }

    /**
     * facilitates switching to main game upon button click
     * 
     * @throws IOException
     */
    @FXML
    private void handleStandard() throws IOException {
        gameMode = "Standard";
        gameSwitcher.switchMenu();
    }

    /**
     * facilitates switching to main game upon button click
     * 
     * @throws IOException
     */
    @FXML
    private void handleBerserker() throws IOException {
        gameMode = "Berserker";
        gameSwitcher.switchMenu();
    }

    /**
     * facilitates switching to main game upon button click
     * 
     * @throws IOException
     */
    @FXML
    private void handleSurvive() throws IOException {
        gameMode = "Survive";
        gameSwitcher.switchMenu();
    }

    /**
     * facilitates switching to main game upon button click
     * 
     * @throws IOException
     */
    @FXML
    private void handleConfusion() throws IOException {
        gameMode = "Confusion";
        gameSwitcher.switchMenu();
    }

    public String getGameMode() {
        return gameMode;
    }
}
