package bg.conquerors.wardrobe.controller;

import bg.conquerors.wardrobe.model.enums.SizeEnum;
import bg.conquerors.wardrobe.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/add-to-cart/{productNumber}/{size}")
    public String addProductToCart(Model model,
                                   @PathVariable("productNumber") String productNumber,
                                   @PathVariable("size") SizeEnum size) {

        orderService.addProductToCart(productNumber, size);

        return "redirect:/shop/product/{productNumber}";
    }

    @GetMapping("/cart/remove-from-cart/{id}")
    public String removeProductFromCart(@PathVariable("id") Long id) {

        orderService.removeProductFromCart(id);
        return "redirect:/shopping-cart";
    }

}
