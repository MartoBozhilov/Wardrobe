package bg.conquerors.wardrobe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

}
