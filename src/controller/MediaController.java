package controller;

import entity.media.Book;
import entity.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class MediaController extends BaseController {
    /**
     * This method gets all Media in DB and return back to home to display
     * @return
     * @throws SQLException
     */
    public ObservableList<Media> getAllMedia() throws SQLException {
        return FXCollections.observableArrayList(new Media().getAllMedia());
    }

    public Media getMediaById(int id) throws SQLException {
        return new Media().getMediaById(id);
    }

    public Media getBookById(int id) throws SQLException {
        return new Book().getMediaById(id);
    }
}
