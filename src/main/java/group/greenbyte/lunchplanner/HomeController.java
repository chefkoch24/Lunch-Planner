package group.greenbyte.lunchplanner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    @CrossOrigin()
    public String index() {
        return "index.html";
    }

}
