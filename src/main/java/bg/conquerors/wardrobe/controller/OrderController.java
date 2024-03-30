package bg.conquerors.wardrobe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @GetMapping("/orders")
    public String Orders(Model model){
        List<String> ids = new ArrayList<String>();
        for (int i = 0 ; i < 11  ; i++){
            ids.add(String.valueOf(i));
        }

        model.addAttribute("test", ids);
        return "orders";
    }
}
