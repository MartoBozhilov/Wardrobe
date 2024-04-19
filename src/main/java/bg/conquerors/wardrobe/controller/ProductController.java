package bg.conquerors.wardrobe.controller;

import bg.conquerors.wardrobe.model.enums.SizeEnum;
import bg.conquerors.wardrobe.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/shop/product-detail/{productNumber}")
    public String viewProduct(Model model, @PathVariable String productNumber) {

        model.addAttribute("product", productService.getProductByProductNumber(productNumber));
        model.addAttribute("sizes", SizeEnum.values());

        return "product-detail";
    }
}
