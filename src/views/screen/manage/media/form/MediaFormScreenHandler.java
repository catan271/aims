package views.screen.manage.media.form;

import controller.MediaController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.manage.ManageScreenHandler;
import views.screen.manage.media.MediaManageScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MediaFormScreenHandler extends ManageScreenHandler implements Initializable {
    @FXML
    protected Label formTitle;

    @FXML
    protected TextField titleField;

    @FXML
    protected TextField categoryField;

    @FXML
    protected TextField valueField;

    @FXML
    protected TextField priceField;

    @FXML
    protected TextField quantityField;

    @FXML
    protected Button uploadButton;

    @FXML
    protected Button saveButton;

    @FXML
    protected Button cancelButton;

    protected int id;

    public void setId(int id) {
        this.id = id;
    }

    public MediaFormScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public MediaController getBController() {
        return (MediaController) super.getBController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        cancelButton.setOnAction(e -> {
            try {
                backScreen();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    protected ArrayList<String> getMediaValues() {
        ArrayList<String> valuesList = new ArrayList<String>();
        String title = titleField.getText();
        String category = categoryField.getText();
        String value = valueField.getText();
        String price = priceField.getText();
        String quantity = quantityField.getText();

        valuesList.add(title);
        valuesList.add(category);
        valuesList.add(price);
        valuesList.add(value);
        valuesList.add(quantity);

        return valuesList;
    }

    protected void backScreen() throws IOException {
        MediaManageScreenHandler mediaScreen = new MediaManageScreenHandler(this.stage, Configs.MEDIA_MANAGE_SCREEN_PATH);
        mediaScreen.setBController(new MediaController());
        mediaScreen.show();
    }

    protected void setDefaultValues(String title, String category, int price, int value, int quantity) {
        titleField.setText(title);
        categoryField.setText(category);
        priceField.setText(String.valueOf(price));
        valueField.setText(String.valueOf(value));
        quantityField.setText(String.valueOf(quantity));
    }

    public void setFormTitle(String title) {
        this.formTitle.setText(title);
    }
}
