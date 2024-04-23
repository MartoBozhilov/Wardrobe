package bg.conquerors.wardrobe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {

        return "index";
    }

    @GetMapping("/faq")
    public String faqPage() {

        return "faq";
    }

    @GetMapping("/buy-policy")
    public String buyPolicy() {

        return "buy-policy";
    }

    @GetMapping("/contact")
    public String contactPage() {

        return "contact";
    }

    @GetMapping("/index")
    public String homeIndex() {

        return "index";
    }

    @GetMapping("/order-successful")
    public String orderSuccessful() {

        return "/order-successful";
    }

    @PostMapping("/error")
    public String error() {

        return "/error";
    }

}
