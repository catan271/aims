package entity.media;

import entity.db.AIMSDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Book extends Media {

    String author;
    String coverType;
    String publisher;
    Date publishDate;
    int numOfPages;
    String language;
    String bookCategory;

    public Book() throws SQLException {

    }

    public Book(int id, String title, String category, int price, int quantity, String type, String author,
                String coverType, String publisher, Date publishDate, int numOfPages, String language,
                String bookCategory) {
        super(id, title, category, price, quantity, type);
        this.author = author;
        this.coverType = coverType;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.numOfPages = numOfPages;
        this.language = language;
        this.bookCategory = bookCategory;
    }

    // getter and setter
    public int getId() {
        return this.id;
    }

    public String getAuthor() {
        return this.author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getCoverType() {
        return this.coverType;
    }

    public Book setCoverType(String coverType) {
        this.coverType = coverType;
        return this;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public Book setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }


    public Date getPublishDate() {
        return this.publishDate;
    }

    public Book setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public int getNumOfPages() {
        return this.numOfPages;
    }

    public Book setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
        return this;
    }

    public String getLanguage() {
        return this.language;
    }

    public Book setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getBookCategory() {
        return this.bookCategory;
    }

    public Book setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
        return this;
    }

    @Override
    public Book getMediaById(int id) throws SQLException {
        String sql = "SELECT * FROM " +
                "Media " +
                "LEFT JOIN Book " +
                "ON Media.id = Book.id " +
                "where Media.id = " + id + ";";
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if (res.next()) {

            // from Media table
            String title = res.getString("title");
            String type = res.getString("type");
            int price = res.getInt("price");
            String category = res.getString("category");
            int quantity = res.getInt("quantity");

            // from Book table
            String author = res.getString("author");
            String coverType = res.getString("coverType");
            String publisher = res.getString("publisher");
            Date publishDate = Date.valueOf(res.getString("publishDate"));
            int numOfPages = res.getInt("numOfPages");
            String language = res.getString("language");
            String bookCategory = res.getString("bookCategory");

            return new Book(id, title, category, price, quantity, type,
                    author, coverType, publisher, publishDate, numOfPages, language, bookCategory);

        }
        return null;
    }

    @Override
    public List<Media> getAllMedia() throws SQLException {
        List<Media> books = new ArrayList<Media>();

        String sql = "SELECT * FROM " +
                "Media " +
                "LEFT JOIN Book " +
                "ON Media.id = Book.id "
                + "ORDER BY Media.id DESC "
                + ";";
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        while (res.next()) {

            // from Media table
            String title = res.getString("title");
            String type = res.getString("type");
            int price = res.getInt("price");
            String category = res.getString("category");
            int quantity = res.getInt("quantity");

            // from Book table
            String author = res.getString("author");
            String coverType = res.getString("coverType");
            String publisher = res.getString("publisher");
            Date publishDate = res.getDate("publishDate");
            int numOfPages = res.getInt("numOfPages");
            String language = res.getString("language");
            String bookCategory = res.getString("bookCategory");

            books.add(new Book(id, title, category, price, quantity, type,
                    author, coverType, publisher, publishDate, numOfPages, language, bookCategory));

        }
        return books;
    }

    public void create() throws SQLException {
        super.create();
        String sql = "INSERT INTO Book (id, author, coverType, publisher, \"publishDate\", \"numOfPages\", language, \"bookCategory\") "
                + "VALUES (" + id + ",\'" + author + "\',\'" + coverType + "\',\'" + publisher + "\',\'" + publishDate + "\'," + numOfPages + ",\'" + language + "\',\'" + bookCategory + "\') "
                + ";";
        Statement stm = AIMSDB.getConnection().createStatement();
        stm.executeUpdate(sql);
    }

    public void save() throws SQLException {
        if (id == 0) {
            create();
        } else {
            super.save();
            String sql = "UPDATE Book SET "
                    + "author = \'" + author + "\',"
                    + "\"coverType\" = \'" + coverType + "\',"
                    + "publisher = \'" + publisher + "\',"
                    + "\"publishDate\" = \'" + publishDate + "\',"
                    + "\"numOfPages\" = \'" + numOfPages + "\',"
                    + "language = \'" + language + "\',"
                    + "\"bookCategory\" = \'" + bookCategory + "\' "
                    + "WHERE id = " + this.id + ";";
            Statement stm = AIMSDB.getConnection().createStatement();
            stm.executeUpdate(sql);
        }
    }

    @Override
    public String toString() {
        return "{" +
                super.toString() +
                " author='" + author + "'" +
                ", coverType='" + coverType + "'" +
                ", publisher='" + publisher + "'" +
                ", publishDate='" + publishDate + "'" +
                ", numOfPages='" + numOfPages + "'" +
                ", language='" + language + "'" +
                ", bookCategory='" + bookCategory + "'" +
                "}";
    }
}
