package group.greenbyte.lunchplanner.user;

import group.greenbyte.lunchplanner.AppConfig;
import group.greenbyte.lunchplanner.exceptions.HttpRequestException;
import group.greenbyte.lunchplanner.user.database.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static group.greenbyte.lunchplanner.Utils.createString;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@ActiveProfiles("application-test.properties")
public class UserLogicTest {

    @Autowired
    private UserLogic userLogic;

    // ------------- CREATE USER ------------------

    @Test
    public void test1CreateUserValidParam() throws Exception{
        String userName = createString(50);
        String mail = "nknakldsnf@jkldadsf.klen";
        String password = createString(80);

        userLogic.createUser(userName, password, mail);
    }

    @Test(expected = HttpRequestException.class)
    public void test2CreateUserEmptyUsername() throws Exception{
        String userName = "";
        String mail = createString(50);
        String password = createString(80);

        userLogic.createUser(userName, password, mail);
    }

    @Test(expected = HttpRequestException.class)
    public void test3CreateUserTooLongUserName() throws Exception{
        String userName = createString(51);
        String mail = createString(50);
        String password = createString(80);

        userLogic.createUser(userName, password, mail);
    }

    @Test(expected = HttpRequestException.class)
    public void test4CreateUserEmptyPassword() throws Exception{
        String userName = createString(50);
        String mail = createString(50);
        String password = "";

        userLogic.createUser(userName, password, mail);
    }

    @Test(expected = HttpRequestException.class)
    public void test5CreateUserTooLongPassword() throws Exception{
        String userName = createString(50);
        String mail = createString(50);
        String password = createString(81);

        userLogic.createUser(userName, password, mail);
    }

    @Test(expected = HttpRequestException.class)
    public void test6CreateUserMailEmpty() throws Exception{
        String userName = createString(50);
        String mail = "";
        String password = createString(80);

        userLogic.createUser(userName, password, mail);
    }

    @Test(expected = HttpRequestException.class)
    public void test7CreateUserTooLongEmail() throws Exception{
        String userName = createString(50);
        String mail = createString(51);
        String password = createString(80);

        userLogic.createUser(userName, password, mail);
    }

    @Test(expected = HttpRequestException.class)
    public void test1CreateUserInvalidMail() throws Exception{
        String userName = createString(50);
        String mail = "ungueltige-mail.de";
        String password = createString(80);

        userLogic.createUser(userName, password, mail);
    }

    // ------------- USER EXISTS ------------------

    @Test
    public void test1UserExistsValidParam() throws Exception{
        String userName1 = createString(User.MAX_USERNAME_LENGTH);
        String mail1 = "ungueltige@mail.de";
        String password1 = createString(User.MAX_PASSWORD_LENGTH);

        String userName2 = createString(User.MAX_USERNAME_LENGTH);
        String mail2 = "ungueltige-mail.de";
        String password2 = createString(User.MAX_PASSWORD_LENGTH);

        boolean isAlreadyThere = userLogic.userExist(userName2,password2,mail2);
    }

    @Test(expected = HttpRequestException.class)
    public void test2RegisterUserEmptyUsername() throws Exception{
        String userName = "";
        String mail = createString(50);
        String password = createString(80);

        userLogic.userExist(userName, password, mail);
    }

    @Test(expected = HttpRequestException.class)
    public void test3RegisterUserTooLongUserName() throws Exception{
        String userName = createString(51);
        String mail = createString(50);
        String password = createString(80);

        userLogic.userExist(userName, password, mail);
    }

    @Test(expected = HttpRequestException.class)
    public void test4RegisterUserEmptyPassword() throws Exception{
        String userName = createString(50);
        String mail = createString(50);
        String password = "";

        userLogic.userExist(userName, password, mail);
    }

    @Test(expected = HttpRequestException.class)
    public void test5RegisterUserTooLongPassword() throws Exception{
        String userName = createString(50);
        String mail = createString(50);
        String password = createString(81);

        userLogic.userExist(userName, password, mail);
    }

    @Test(expected = HttpRequestException.class)
    public void test6RegisterUserMailEmpty() throws Exception{
        String userName = createString(50);
        String mail = "";
        String password = createString(80);

        userLogic.userExist(userName, password, mail);
    }

    @Test(expected = HttpRequestException.class)
    public void test7RegisterUserTooLongEmail() throws Exception{
        String userName = createString(50);
        String mail = createString(51);
        String password = createString(80);

        userLogic.userExist(userName, password, mail);
    }

    @Test(expected = HttpRequestException.class)
    public void test1RegisterUserInvalidMail() throws Exception{
        String userName = createString(50);
        String mail = "ungueltige-mail.de";
        String password = createString(80);

        userLogic.userExist(userName, password, mail);
    }

}