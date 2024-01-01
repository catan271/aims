package controller;

import common.exception.FailLoginException;
import entity.db.AIMSDB;
import entity.user.User;
import utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.logging.Logger;

public class AuthenticationController extends BaseController {

    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

    public void login(String email, String password) throws Exception {
//        LOGGER.info(email);
//        LOGGER.info(Utils.md5(password));
        try {
            User user = this.authenticate(email, Utils.md5(password));
            // Log user details
            if (Objects.isNull(user)) throw new FailLoginException();
//            SessionInformation.mainUser = user;
//            SessionInformation.expiredTime = LocalDateTime.now().plusHours(24);
        } catch (SQLException ex) {
            throw new FailLoginException();
        }
    }

    public User authenticate(String email, String encryptedPassword) throws SQLException {
        String sql = "SELECT * FROM User " +
                "WHERE email = '" + email + "' AND encrypted_password = '" + encryptedPassword + "'";
        LOGGER.info(sql);
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if(res.next()) {
            LOGGER.info("User Name: " + res.getString("name"));
            return new User(
                    res.getInt("id"),
                    res.getString("name"),
                    res.getString("email"),
                    res.getString("address"),
                    res.getString("phone")
            );
        } else {
            throw new SQLException();
        }
    }

//    public void logout() {
//        SessionInformation.mainUser = null;
//        SessionInformation.expiredTime = null;
//    }

}
