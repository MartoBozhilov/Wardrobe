package bg.conquerors.wardrobe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String Test() {

        return "index";
    }

    @GetMapping("/")
    public String Redirect(){
        return "redirect:/home";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}