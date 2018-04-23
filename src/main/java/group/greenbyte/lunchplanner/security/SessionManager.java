package group.greenbyte.lunchplanner.security;

import group.greenbyte.lunchplanner.user.database.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SessionManager {

    public static String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getUserName();
    }

}
