package controller;

import entity.media.Book;
import entity.media.CD;
import entity.media.DVD;
import entity.media.Media;

import java.sql.SQLException;
import java.util.List;

public class AdminHomeController extends  BaseController {
    public List getAllMedia() throws SQLException {
        return new Media().getAllMedia();
    }

    public List getAllBook() throws SQLException {
        return new Book().getAllMedia();
    }

    public List getAllCD() throws SQLException {
        return new CD().getAllMedia();
    }

    public List getAllDVD() throws SQLException {
        return new DVD().getAllMedia();
    }
}
