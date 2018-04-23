package group.greenbyte.lunchplanner.user;

import group.greenbyte.lunchplanner.exceptions.HttpRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserLogic userLogic;

    /**
     * Create user with username, password and mail
     *
     * @param user is a json object with all attributes from UserJson
     * @return error message or nothing
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String createUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String email,
                             HttpServletResponse response) {

        try {
            userLogic.createUser(username, password, email);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (HttpRequestException e) {
            response.setStatus(e.getStatusCode());
            return e.getErrorMessage();
        }

        return "";
    }

//    /**
//     *
//     * @param userToInvite
//     * @param eventId
//     */
//    @RequestMapping(value = "/invitations", method = RequestMethod.GET,
//                    produces = MediaType.APPLICATION_JSON_VALUE)
//    public String getInvitation(@PathVariable String userToInvite, @PathVariable int eventId, HttpServletResponse response) {
//        response.setStatus(HttpServletResponse.SC_OK);
//        return "";
//    }

    /**
     * login with username oder mail
     *
     * @param user
     * @param response
     */
    @RequestMapping(value = "/user/loginUser", method = RequestMethod.POST)
    public void loginUser(@RequestBody UserJson user, HttpServletResponse response) {
        try {
            userLogic.loginUser(user.getUserName(), user.getPassword());
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } catch (HttpRequestException e) {
            response.setStatus(e.getStatusCode());
        }
    }

    @Autowired
    public void setUserLogic(UserLogic userLogic) {
        this.userLogic = userLogic;
    }

}
