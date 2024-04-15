package bg.conquerors.wardrobe.controller;

import bg.conquerors.wardrobe.model.dto.AddProductDTO;
import bg.conquerors.wardrobe.model.enums.SizeEnum;
import bg.conquerors.wardrobe.service.AdminService;
import bg.conquerors.wardrobe.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    private final ProductService productService;
    private final AdminService adminService;
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    public HomeController(AdminService adminService, ProductService productService) {
        this.adminService = adminService;
        this.productService = productService;
    }

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

    @GetMapping("/product-detail/{id}")
    public String productDetailWithId(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", adminService.getProductById(id));
        model.addAttribute("itemId", id);
        model.addAttribute("sizeOptions", SizeEnum.values());
        return "product-detail";
    }

    @GetMapping("/product-detail")
    public String productDetail() {

        return "product-detail";
    }

    @GetMapping("/shoping-cart")
    public String shopingCart() {

        return "shoping-cart";
    }

}
