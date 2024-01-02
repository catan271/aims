package entity.media;

import entity.db.AIMSDB;
import utils.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * The general media class, for another media it can be done by inheriting this class
 */
public class Media {

    protected static boolean isSupportedPlaceRushOrder = new Random().nextBoolean();
    private static Logger LOGGER = Utils.getLogger(Media.class.getName());
    protected Statement stm;
    protected int id;
    protected String title;
    protected String category;
    protected int value; // the real price of product (eg: 450)
    protected int price; // the price which will be displayed on browser (eg: 500)
    protected int quantity;
    protected String type;
    protected String imageURL;

    public Media() throws SQLException {
        stm = AIMSDB.getConnection().createStatement();
    }

    public Media(int id, String title, String category, int price, int quantity, String type) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.type = type;

        this.value = 0;
        this.imageURL = "";

        //stm = AIMSDB.getConnection().createStatement();
    }

    public static boolean getIsSupportedPlaceRushOrder() {
        return Media.isSupportedPlaceRushOrder;
    }

    public int getQuantity() {
        return quantity;
    }

    public Media setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }


    public Media getMediaById(int id) throws SQLException {
        String sql = "SELECT * FROM Media "
                + "WHERE id = " + id + ";";
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if (res.next()) {

            return new Media()
                    .setId(res.getInt("id"))
                    .setTitle(res.getString("title"))
                    .setQuantity(res.getInt("quantity"))
                    .setCategory(res.getString("category"))
                    .setMediaURL(res.getString("imageUrl"))
                    .setPrice(res.getInt("price"))
                    .setType(res.getString("type"));
        }
        return null;
    }

    public List<Media> getAllMedia() throws SQLException {
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery("select * from Media ORDER BY id DESC;");
        ArrayList medium = new ArrayList<>();
        while (res.next()) {
            Media media = new Media()
                    .setId(res.getInt("id"))
                    .setTitle(res.getString("title"))
                    .setQuantity(res.getInt("quantity"))
                    .setCategory(res.getString("category"))
                    .setMediaURL(res.getString("imageUrl"))
                    .setPrice(res.getInt("price"))
                    .setType(res.getString("type"));
            medium.add(media);
        }
        return medium;
    }

    public void updateMediaFieldById(String tbname, int id, String field, String value) throws SQLException {
        Statement stm = AIMSDB.getConnection().createStatement();
        value = "\"" + value + "\"";
        stm.executeUpdate(" update " + tbname + " set" + " "
                + field + "=" + value + " "
                + "where id=" + id + ";");
    }

    public void create() throws SQLException {
        String sql = "INSERT INTO Media (title, value, price, quantity, category, \"imageUrl\", type) "
                + "VALUES (\'" + title + "\'," + value + "," + price + "," + quantity + ",\'" + category + "\',\'" + imageURL + "\',\'" + type + "\') "
                + ";";
        PreparedStatement stm = AIMSDB.getConnection().prepareStatement(sql, new String[] { "id" });
        if (stm.executeUpdate() == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }
        ResultSet res = stm.getGeneratedKeys();
        if (res.next()) {
            id = res.getInt(1);
        }
    }

    public void save() throws SQLException {
        if (id == 0) {
            create();
        } else {
            String sql = "UPDATE Media SET "
                    + "title = \'" + title + "\',"
                    + "value = " + value + ","
                    + "price = " + price + ","
                    + "quantity = " + quantity + ","
                    + "category = \'" + category + "\',"
                    + "\"imageUrl\" = \'" + imageURL + "\',"
                    + "type = \'" + type + "\' "
                    + "WHERE id = " + this.id + ";";
            Statement stm = AIMSDB.getConnection().createStatement();
            stm.executeUpdate(sql);
        }
    }

    // getter and setter
    public int getId() {
        return this.id;
    }

    private Media setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public Media setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCategory() {
        return this.category;
    }

    public Media setCategory(String category) {
        this.category = category;
        return this;
    }

    public int getPrice() {
        return this.price;
    }

    public Media setPrice(int price) {
        this.price = price;
        return this;
    }

    public String getImageURL() {
        return this.imageURL;
    }

    public Media setMediaURL(String url) {
        this.imageURL = url;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public Media setType(String type) {
        this.type = type;
        return this;
    }

    public int getValue() {
        return this.value;
    }

    public Media setValue(int value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + id + "'" +
                ", title='" + title + "'" +
                ", category='" + category + "'" +
                ", price='" + price + "'" +
                ", quantity='" + quantity + "'" +
                ", type='" + type + "'" +
                ", imageURL='" + imageURL + "'" +
                "}";
    }
}