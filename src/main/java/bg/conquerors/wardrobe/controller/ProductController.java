package bg.conquerors.wardrobe.controller;

import bg.conquerors.wardrobe.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/shop")
    public String getViewProducts(Model model) {
        model.addAttribute("products", productService.getViewOfProducts());

        return "shop";
    }

}
