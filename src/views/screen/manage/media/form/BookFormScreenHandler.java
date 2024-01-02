package views.screen.manage.media.form;

import controller.MediaController;
import entity.media.Book;
import entity.media.Media;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import utils.Utils;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookFormScreenHandler extends MediaFormScreenHandler implements Initializable {
    public BookFormScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.saveButton.setOnAction(e -> {
            System.out.println(this.id);
        });
    }

    public void setDefaultBookValues() throws SQLException {
        Media book = this.getBController().getMediaById(this.id);

        if (book != null) {
            setDefaultValues(book.getTitle(), book.getCategory(), book.getPrice(), book.getValue(), book.getQuantity());
        }
    }
}
