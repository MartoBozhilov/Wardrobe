package bg.conquerors.wardrobe.controller;

import bg.conquerors.wardrobe.model.enums.SizeEnum;
import bg.conquerors.wardrobe.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public String addProductToCart(Model model,
                                   @PathVariable("productNumber") String productNumber,
                                   @PathVariable("size") SizeEnum size) {

        return "redirect:/shop/product/{productNumber}";
    }

}
