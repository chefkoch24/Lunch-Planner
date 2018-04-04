package group.greenbyte.lunchplanner.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(name = "/", method = RequestMethod.GET)
    public String test() {
        return "Hello World";
    }

}
