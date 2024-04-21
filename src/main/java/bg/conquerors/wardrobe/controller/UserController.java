package bg.conquerors.wardrobe.controller;

import bg.conquerors.wardrobe.model.dto.UserRegistrationDTO;
import bg.conquerors.wardrobe.model.entity.User;
import bg.conquerors.wardrobe.repository.UserRepository;
import bg.conquerors.wardrobe.service.OrderService;
import bg.conquerors.wardrobe.service.UserService;
import bg.conquerors.wardrobe.service.impl.OrderServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
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

    @GetMapping("/users/account-update")
    public String account_update() {
        return "account-update";
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
