package group.greenbyte.lunchplanner.user;

import group.greenbyte.lunchplanner.exceptions.DatabaseException;
import group.greenbyte.lunchplanner.exceptions.HttpRequestException;
import group.greenbyte.lunchplanner.user.database.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.security.*;

import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.regex.Pattern;

import static group.greenbyte.lunchplanner.user.SecurityHelper.validatePassword;

@Service
public class UserLogic {

    private static final Pattern REGEX_MAIL = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");

    // This variable will be set over the setter Method by java spring
    private UserDao userDao;

    /**
     * Create a user
     *
     * @param userName his username
     * @param password the unhashed password
     * @param mail mail-adress
     *
     * @throws HttpRequestException when an parameter is not valid or user already exists or an DatabaseError happens
     */
    void createUser(String userName, String password, String mail) throws HttpRequestException {
        if(userName == null || userName.length() == 0)
            throw new HttpRequestException(HttpStatus.BAD_REQUEST.value(), "user name is empty");

        if(password == null || password.length() == 0)
            throw new HttpRequestException(HttpStatus.BAD_REQUEST.value(), "password is empty");

        if(mail == null || mail.length() == 0)
            throw new HttpRequestException(HttpStatus.BAD_REQUEST.value(), "mail is empty");

        if(!REGEX_MAIL.matcher(mail).matches())
            throw new HttpRequestException(HttpStatus.BAD_REQUEST.value(), "mail is not valid");

        try {
            userDao.createUser(userName, SecurityHelper.hashPassword(password), mail);
        } catch (DatabaseException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new HttpRequestException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    public User getUser(String userName) throws HttpRequestException {
        try {
            return userDao.getUser(userName);
        } catch (DatabaseException e) {
            throw new HttpRequestException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    /**
     * Send an invitation to a user (async)
     *
     * @param userName who intivtes
     * @param toInviteUserName who is invited
     */
    public void sendInvitation(String userName, String toInviteUserName) {
        //ToDO send notfication to user
    }


    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
