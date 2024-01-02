package views.screen.manage;

import controller.MediaController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.manage.media.MediaManageScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageScreenHandler extends BaseScreenHandler implements Initializable {
    @FXML
    private Button mediaManage;

    private HomeScreenHandler home;

    public ManageScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mediaManage.setOnAction(e -> {
            openMediaManage();
        });
    }

    protected void openMediaManage() {
        MediaManageScreenHandler mediaManageScreen;
        try {
            mediaManageScreen = new MediaManageScreenHandler(this.stage, Configs.MEDIA_MANAGE_SCREEN_PATH);
            mediaManageScreen.setHomeScreenHandler(this.home);
            mediaManageScreen.setBController(new MediaController());
            mediaManageScreen.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
