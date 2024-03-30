package bg.conquerors.wardrobe.controller;

import bg.conquerors.wardrobe.model.dto.UserRegistrationDTO;
import bg.conquerors.wardrobe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(UserRegistrationDTO userRegistrationDTO) {

        userService.registerAndLogin(userRegistrationDTO);

        return "redirect:/login";
    }

}
