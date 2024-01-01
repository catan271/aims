package views.screen.home;
import controller.AdminHomeController;
import controller.AuthenticationController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.cart.CartScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


public class LoginScreenHandler extends BaseScreenHandler{

    public static Logger LOGGER = Utils.getLogger(LoginScreenHandler.class.getName());

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    public LoginScreenHandler(Stage stage, String screenPath) throws IOException{
        super(stage, screenPath);
    }

    public AuthenticationController getBController() {
        return (AuthenticationController) super.getBController();
    }

    @FXML
    void login() throws IOException, InterruptedException, SQLException {
        try {
            getBController().login(email.getText(), password.getText());

            PopupScreen.success("Login Successfully!");
            AdminHomeScreenHandler adminScreen = new AdminHomeScreenHandler(this.stage, Configs.ADMIN_SCREEN_PATH);
            adminScreen.setScreenTitle("Admin");
            adminScreen.setBController(new AdminHomeController());
            adminScreen.setHomeScreenHandler(homeScreenHandler);
            adminScreen.show();

        } catch (Exception ex) {
            PopupScreen.error(ex.getMessage());
        }
    }

    @FXML
    void backToHomeScreen(MouseEvent event) throws IOException, InterruptedException, SQLException {
        this.homeScreenHandler.show();
    }
}
