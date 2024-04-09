package bg.conquerors.wardrobe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String Test10() {

        return "about";
    }

    @GetMapping("/about")
    public String Test() {

        return "about";
    }
    @GetMapping("/blog")
    public String Test1() {

        return "blog";
    }
    @GetMapping("/blog-detail")
    public String Test2() {

        return "index";
    }

    @GetMapping("/contact")
    public String Test4() {

        return "contact";
    }
    @GetMapping("/index")
    public String Test5() {

        return "index";
    }

    @GetMapping("/product")
    public String Test6() {

        return "product";
    }
    @GetMapping("/product-detail")
    public String Test7() {

        return "product-detail";
    }
    @GetMapping("/shoping-cart")
    public String Test8() {

        return "shoping-cart";
    }

}
