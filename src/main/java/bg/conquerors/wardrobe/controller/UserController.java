package bg.conquerors.wardrobe.controller;

import bg.conquerors.wardrobe.model.dto.AddProductDTO;
import bg.conquerors.wardrobe.model.dto.UserRegistrationDTO;
import bg.conquerors.wardrobe.model.dto.ViewUserOrdersDTO;
import bg.conquerors.wardrobe.model.entity.Order;
import bg.conquerors.wardrobe.model.entity.User;
import bg.conquerors.wardrobe.repository.UserRepository;
import bg.conquerors.wardrobe.service.OrderService;
import bg.conquerors.wardrobe.service.UserService;
import bg.conquerors.wardrobe.service.impl.OrderServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Binding;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String login() {
        return "login";
    }

    @GetMapping("/users/account")
    public String account(Model model) {
        User currentUser = userService.findCurrentUser();
        model.addAttribute("user",currentUser);
        return "account";
    }

    @GetMapping("/users/account-update/{userId}")
    public String editUser(@PathVariable("userId") Long userId, Model model) {

        model.addAttribute("userId", userId);
        User currentUser = userService.findCurrentUser();
        model.addAttribute("user",currentUser);


        return "/account-update";
    }

    @PostMapping("/users/account-update/{userId}")
    public String editUser(@PathVariable("userId") Long userId, UserRegistrationDTO userRegistrationDTO) {

        userService.editUserInformation(userId,userRegistrationDTO);
        return "redirect:/users/account";
    }

    @GetMapping("/users/account-order")
    public String ordersUser(Model model) {

        List<ViewUserOrdersDTO> viewUserOrdersDTOS = userService.getUserOrders(userService.findCurrentUser());

        model.addAttribute("orders",viewUserOrdersDTOS);

        return "account-orders";
    }

    @PostMapping("/users/account-order")
    public String ordersUser() {
        return "redirect:/users/account";
    }


    @GetMapping("/users/register")
    public String register(Model model) {
        model.addAttribute("userRegistrationDTO", new UserRegistrationDTO());
        return "register";
    }

   @PostMapping("/users/register")
    public String register(UserRegistrationDTO userRegistrationDTO) {

        userService.register(userRegistrationDTO);

        return "redirect:/users/login";
    }

}
