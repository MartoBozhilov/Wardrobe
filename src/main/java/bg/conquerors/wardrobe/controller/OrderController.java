package bg.conquerors.wardrobe.controller;

import bg.conquerors.wardrobe.model.enums.SizeEnum;
import bg.conquerors.wardrobe.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/add-to-cart/{productNumber}/{size}/{quantity}")
    public String addProductToCart(Model model,
                                   @PathVariable("productNumber") String productNumber,
                                   @PathVariable("size") SizeEnum size,
                                   @PathVariable("quantity") Integer quantity) {

        orderService.addProductToCart(productNumber, size, quantity);

        return "redirect:/shop/product-detail/{productNumber}";
    }

    @GetMapping("/cart/remove-from-cart/{id}")
    public String removeProductFromCart(@PathVariable("id") Long id) {

        orderService.removeProductFromCart(id);
        return "redirect:/shopping-cart";
    }

    @GetMapping("/shopping-cart")
    public String shopingCart(Model model) {

        model.addAttribute("cartView", orderService.getCart());

        return "shoping-cart";
    }

}
