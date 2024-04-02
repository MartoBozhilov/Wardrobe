package bg.conquerors.wardrobe.controller;

import bg.conquerors.wardrobe.model.entity.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @GetMapping("/orders")
    public String Orders(Model model){
        List<Order> orders = new ArrayList<>();
        for (int i = 1 ; i < 11  ; i++){
            orders.add(new Order(i));
        }

        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/orders/test")
    public String OrdersTest(Model model){
        List<Order> orders = new ArrayList<>();
        for (int i = 11 ; i < 21  ; i++){
            orders.add(new Order(i));
        }

        model.addAttribute("orders", orders);
        return "orders";
    }
}
