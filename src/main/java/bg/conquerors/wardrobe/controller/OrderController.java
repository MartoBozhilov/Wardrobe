package bg.conquerors.wardrobe.controller;

import bg.conquerors.wardrobe.model.dto.FinishOrderDTO;
import bg.conquerors.wardrobe.model.enums.SizeEnum;
import bg.conquerors.wardrobe.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

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

    @PostMapping("/cart/remove-from-cart/{id}")
    public String removeProductFromCart(@PathVariable("id") Long id) {

        orderService.removeProductFromCart(id);
        return "redirect:/shopping-cart";
    }

    @GetMapping("/shopping-cart")
    public String shopingCart(Model model) {

        var cartView = orderService.getCart();
        model.addAttribute("cartView", cartView);

        BigDecimal totalPrice = cartView.getCartItems().stream()
                .map(cartItemDTO -> cartItemDTO.getPrice().multiply(new BigDecimal(cartItemDTO.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("totalPrice", totalPrice);

        return "shoping-cart";
    }

    @PostMapping("/cart/proceed-to-checkout")
    public String saveOrder(FinishOrderDTO finishOrderDTO,
                            @RequestParam("id") Long cartItemId) throws Exception {

        finishOrderDTO.setId(cartItemId);
        orderService.saveOrder(finishOrderDTO);

        return "redirect:/index";
    }

}
