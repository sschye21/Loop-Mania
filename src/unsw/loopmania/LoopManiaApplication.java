package unsw.loopmania;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * the main application run main method from this class
 */
public class LoopManiaApplication extends Application {
    // TODO = possibly add other menus?

    /**
     * the controller for the game. Stored as a field so can terminate it when click
     * exit button
     */
    private LoopManiaWorldController mainController;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // set title on top of window bar
        primaryStage.setTitle("Loop Mania");

        // prevent human player resizing game window (since otherwise would see white
        // space)
        // alternatively, you could allow rescaling of the game (you'd have to program
        // resizing of the JavaFX nodes)
        primaryStage.setResizable(false);

        SettingsMenuController newMusic = new SettingsMenuController();

        // load the main game
        LoopManiaWorldControllerLoader loopManiaLoader = new LoopManiaWorldControllerLoader(
                "world_with_twists_and_turns.json");
        mainController = loopManiaLoader.loadController();
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("LoopManiaView.fxml"));
        gameLoader.setController(mainController);
        Parent gameRoot = gameLoader.load();

        // load the main menu
        MainMenuController mainMenuController = new MainMenuController();
        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("MainMenuView.fxml"));
        menuLoader.setController(mainMenuController);
        Parent mainMenuRoot = menuLoader.load();

        // load the game pause
        GamePausedController gamePausedController = new GamePausedController();
        FXMLLoader gamePausedLoader = new FXMLLoader(getClass().getResource("GamePausedView.fxml"));
        gamePausedLoader.setController(gamePausedController);
        Parent gamePausedroot = gamePausedLoader.load();

        // load the mode menu
        ModeMenuScreen modeMenuScreen = new ModeMenuScreen(primaryStage);
        mainMenuController.setModeMenuScreen(modeMenuScreen);

        // load the shop menu
        ShopController shopController = new ShopController(mainController, modeMenuScreen.getController());
        FXMLLoader shopLoader = new FXMLLoader(getClass().getResource("ShopView.fxml"));
        shopLoader.setController(shopController);
        Parent shopMenuRoot = shopLoader.load();

        // load the help screen - main menu
        HelpScreen helpScreen = new HelpScreen(primaryStage);
        mainMenuController.setHelpScreen(helpScreen);

        // load the help screen - pause menu
        HelpScreenPause helpScreenPause = new HelpScreenPause(primaryStage);
        gamePausedController.setHelpScreen(helpScreenPause);

        // load the settings menu - main menu
        SettingsScreen settingsScreen = new SettingsScreen(primaryStage);
        mainMenuController.setSettingsScreen(settingsScreen);

        // load the settings menu - pause menu
        SettingsScreenPause settingsScreenPause = new SettingsScreenPause(primaryStage);
        gamePausedController.setSettingsScreen(settingsScreenPause);

        // load the lost screen
        LostScreen lostScreen = new LostScreen(primaryStage);
        mainController.setLostScreen(lostScreen);

        // load the win screen
        WinScreen winScreen = new WinScreen(primaryStage);
        mainController.setWinScreen(winScreen);

        // load the shop screen
        ShopScreen shopScreen = new ShopScreen(primaryStage, mainController, modeMenuScreen.getController());
        mainController.setShopScreen(shopScreen);

        // load the goals menu
        GoalsMenuScreen goalsMenuScreen = new GoalsMenuScreen(primaryStage);
        gamePausedController.setGoalsMenuScreen(goalsMenuScreen);

        // set the scene for main menu
        Scene scene = new Scene(mainMenuRoot);

        // MENU SWITCHERS

        // Switch from shop menu to game
        shopScreen.getController().setResumeSwitcher(() -> {
            switchToRoot(scene, gameRoot, primaryStage);
            mainController.startTimer();
        });

        // Switch from help menu to gamePause
        helpScreenPause.getController().setHelpMainSwitcher(() -> {
            switchToRoot(scene, gamePausedroot, primaryStage);
        });

        // Switch from settings menu to gamePause
        settingsScreenPause.getController().setSettingsMainSwitcher(() -> {
            switchToRoot(scene, gamePausedroot, primaryStage);
        });

        // Switch from settings menu to main menu
        settingsScreen.getController().setSettingsMainSwitcher(() -> {
            switchToRoot(scene, mainMenuRoot, primaryStage);
        });

        // Switch from gamePause menu to main menu
        gamePausedController.setMainSwitcher(() -> {
            switchToRoot(scene, mainMenuRoot, primaryStage);
        });

        // Switch from help menu to main menu
        helpScreen.getController().setHelpMainSwitcher(() -> {
            switchToRoot(scene, mainMenuRoot, primaryStage);
        });

        // Switch from lost menu to main menu - navigation
        lostScreen.getController().setLostMainSwitcher(() -> {
            switchToRoot(scene, mainMenuRoot, primaryStage);
        });

        // Switch from win menu to main menu - navigation
        winScreen.getController().setWinMainSwitcher(() -> {
            switchToRoot(scene, mainMenuRoot, primaryStage);
        });

        // Switch from gamePause menu to game
        gamePausedController.setResumeSwitcher(() -> {
            switchToRoot(scene, gameRoot, primaryStage);
            mainController.startTimer();
        });

        // Switch from game to gamePause menu
        mainController.setMainMenuSwitcher(() -> {
            switchToRoot(scene, gamePausedroot, primaryStage);
        });

        // Switch from modeMenu to game
        modeMenuScreen.getController().setGameSwitcher(() -> {
            switchToRoot(scene, gameRoot, primaryStage);
            mainController.startTimer();
        });

        // Switch from GoalsMenu to gameStop menu
        goalsMenuScreen.getController().setGoalsMenuMainSwitcher(() -> {
            switchToRoot(scene, gamePausedroot, primaryStage);
        });

        // deploy the main onto the stage
        gameRoot.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        // wrap up activities when exit program
        mainController.terminate();
    }

    /**
     * switch to a different Root
     */
    private void switchToRoot(Scene scene, Parent root, Stage stage) {
        scene.setRoot(root);
        root.requestFocus();
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
