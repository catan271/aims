package views.screen.manage.media.form;

import controller.MediaController;
import entity.media.Book;
import entity.media.Media;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Utils;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookFormScreenHandler extends MediaFormScreenHandler implements Initializable {

    @FXML
    private TextField authorField;
    @FXML
    private TextField coverTypeField;
    @FXML
    private TextField publisherField;
    @FXML
    private TextField publishDateField;
    @FXML
    private TextField numOfPagesField;
    @FXML
    private TextField languageField;
    @FXML
    private TextField bookCategoryField;

    public BookFormScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }

    @Override
    protected void save() throws SQLException {
        Book book = getBookValues();
        getBController().saveMedia(book);
    }

    protected Book getBookValues() {
        Media media = getMediaValues();

        String author = authorField.getText();
        String coverType = coverTypeField.getText();
        String publisher = publisherField.getText();
        Date publishDate = Date.valueOf(publishDateField.getText());
        int numOfPages = Integer.valueOf(numOfPagesField.getText());
        String language = languageField.getText();
        String bookCategory = bookCategoryField.getText();

        return new Book(media.getId(), media.getTitle(), media.getCategory(), media.getPrice(), media.getQuantity(), "book",
                author, coverType, publisher, publishDate, numOfPages, language, bookCategory);
    }

    public void setDefaultBookValues() throws SQLException {
        Book book = this.getBController().getBookById(id);

        if (book != null) {
            super.setDefaultValues(book.getTitle(), book.getCategory(), book.getPrice(), book.getValue(), book.getQuantity());

            authorField.setText(book.getAuthor());
            coverTypeField.setText(book.getCoverType());
            publisherField.setText(book.getPublisher());
            publishDateField.setText(book.getPublishDate().toString());
            numOfPagesField.setText("" + book.getNumOfPages());
            languageField.setText(book.getLanguage());
            bookCategoryField.setText(book.getBookCategory());
        }
    }
}
