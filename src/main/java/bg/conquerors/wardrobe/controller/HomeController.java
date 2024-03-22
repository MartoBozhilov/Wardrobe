package bg.conquerors.wardrobe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/test")
    public String Test() {
        return "index";
    }

}
