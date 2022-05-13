package unsw.loopmania;

import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsMenuController implements Initializable {

    @FXML
    private Text SettingLabel;

    @FXML
    private Button musicOn;

    @FXML
    private Button musicOff;

    @FXML
    private Button returnMenu;

    private MenuSwitcher mainSwitcher;
   
    private MediaPlayer mp;
    private Media me;

    @FXML
    public void handleReturnMenu() throws IOException {
        mainSwitcher.switchMenu();
    }

    public void setSettingsMainSwitcher(MenuSwitcher mainSwitcher) {
        this.mainSwitcher = mainSwitcher;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String path = "src/music/gameMusic.mp3";
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
    }

    public void handleMusicOn(ActionEvent event) throws IOException {
        mp.play();
        mp.setRate(1);
    }

    public void handleMusicOff(ActionEvent event) throws IOException {
        mp.pause();
    }

}

