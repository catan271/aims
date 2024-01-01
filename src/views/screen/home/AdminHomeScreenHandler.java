package views.screen.home;

import controller.AdminHomeController;
import entity.media.Book;
import entity.media.Media;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class AdminHomeScreenHandler extends BaseScreenHandler implements Initializable {

    public static Logger LOGGER = Utils.getLogger(LoginScreenHandler.class.getName());

    @FXML
    private Button homeBtn;

    @FXML
    private Button bookBtn;

    @FXML
    private Button cdBtn;

    @FXML
    private Button dvdBtn;

    @FXML
    private AnchorPane homeForm;

    @FXML
    private AnchorPane bookForm;

    @FXML
    private AnchorPane cdForm;

    @FXML
    private AnchorPane dvdForm;

    // Media Table

    @FXML
    private TableView<Media> mediaTableView;

    @FXML
    private TableColumn<Media, Integer> mediaIDCol;

    @FXML
    private TableColumn<Media, Integer> mediaValueCol;

    @FXML
    private TableColumn<Media, Integer> mediaPriceCol;

    @FXML
    private TableColumn<Media, Integer> mediaQuantityCol;

    @FXML
    private TableColumn<Media, String> mediaTypeCol;

    @FXML
    private TableColumn<Media, String> mediaCategoryCol;

    @FXML
    private TableColumn<Media, String> mediaTitleCol;

    // Book Table

    @FXML
    private TableView<Book> bookTableView;

    @FXML
    private TableColumn<Book, Integer> bookIDCol;

    @FXML
    private TableColumn<Book, Integer> bookValueCol;

    @FXML
    private TableColumn<Book, Integer> bookPriceCol;

    @FXML
    private TableColumn<Book, Integer> bookQuantityCol;

    @FXML
    private TableColumn<Book, Integer> bookNumPagesCol;

    @FXML
    private TableColumn<Book, String> bookCategoryCol;

    @FXML
    private TableColumn<Book, String> bookTitleCol;

    @FXML
    private TableColumn<Book, String> bookAuthorCol;

    @FXML
    private TableColumn<Book, String> bookCoverTypeCol;

    @FXML
    private TableColumn<Book, String> bookPublisherCol;

    @FXML
    private TableColumn<Book, String> bookPublishDateCol;

    @FXML
    private TableColumn<Book, String> bookLanguageCol;



    public AdminHomeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public AdminHomeController getBController() { return (AdminHomeController) super.getBController(); }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setBController(new AdminHomeController());
        try {
            showAllMedia();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigate(ActionEvent e) throws SQLException {
        if(e.getSource() == homeBtn) {
            LOGGER.info("Navigate to home");
            homeForm.setVisible(true);
            bookForm.setVisible(false);
            cdForm.setVisible(false);
            dvdForm.setVisible(false);
            showAllMedia();
        } else if(e.getSource() == bookBtn) {
            homeForm.setVisible(false);
            bookForm.setVisible(true);
            cdForm.setVisible(false);
            dvdForm.setVisible(false);
            showAllBook();
        } else if(e.getSource() == cdBtn) {
            homeForm.setVisible(false);
            bookForm.setVisible(false);
            cdForm.setVisible(true);
            dvdForm.setVisible(false);
        } else if(e.getSource() == dvdBtn) {
            homeForm.setVisible(false);
            bookForm.setVisible(false);
            cdForm.setVisible(false);
            dvdForm.setVisible(true);
        }
    }

    public void showAllMedia() throws SQLException {
        List<Media> listMedia = getBController().getAllMedia();
        LOGGER.info("Number of media items: " + listMedia.size());
        mediaIDCol.setCellValueFactory(new PropertyValueFactory<Media, Integer>("id"));
        mediaValueCol.setCellValueFactory(new PropertyValueFactory<Media, Integer>("value"));
        mediaPriceCol.setCellValueFactory(new PropertyValueFactory<Media, Integer>("price"));
        mediaQuantityCol.setCellValueFactory(new PropertyValueFactory<Media, Integer>("quantity"));
        mediaTypeCol.setCellValueFactory(new PropertyValueFactory<Media, String>("type"));
        mediaCategoryCol.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        mediaTitleCol.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));

        mediaTableView.getItems().setAll(listMedia);
    }

    public void showAllBook() throws SQLException {
        List<Book> listBook = getBController().getAllBook();
        LOGGER.info("Number of media items: " + listBook.size());
        bookIDCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        bookValueCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("value"));
        bookPriceCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("price"));
        bookQuantityCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("quantity"));
        bookNumPagesCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("numOfPages"));
        bookTitleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        bookCategoryCol.setCellValueFactory(new PropertyValueFactory<Book, String>("bookCategory"));
        bookAuthorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        bookCoverTypeCol.setCellValueFactory(new PropertyValueFactory<Book, String>("coverType"));
        bookPublisherCol.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
        bookPublishDateCol.setCellValueFactory(new PropertyValueFactory<Book, String>("publishDate"));
        bookLanguageCol.setCellValueFactory(new PropertyValueFactory<Book, String>("language"));

        bookTableView.getItems().setAll(listBook);
    }

    @FXML
    void logout() throws IOException, InterruptedException, SQLException {
        this.homeScreenHandler.show();
    }
}
