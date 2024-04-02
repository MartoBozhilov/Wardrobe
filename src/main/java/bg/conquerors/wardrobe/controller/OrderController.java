package bg.conquerors.wardrobe.controller;

import bg.conquerors.wardrobe.model.entity.Order;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.BindParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    private  List<Order> orders = new ArrayList<>();

    @GetMapping("/orders")
    public String Orders(Model model){
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/orders/test")
    public String OrdersTest(Model model){
        for (int i = 1 ; i < 21  ; i++){
            orders.add(new Order(i));
        }

        model.addAttribute("orders", orders);
        return "redirect:/orders";
    }

    @PostMapping("/post")
    public String post(@Valid Order order, BindingResult result, Model model){
        orders.add(new Order(orders.size()));
        model.addAttribute("orders", orders);
        return "redirect:/orders";
    }
}
