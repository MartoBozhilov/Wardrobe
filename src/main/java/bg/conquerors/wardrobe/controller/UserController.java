package bg.conquerors.wardrobe.controller;

import bg.conquerors.wardrobe.model.dto.UserRegistrationDTO;
import bg.conquerors.wardrobe.model.entity.User;
import bg.conquerors.wardrobe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }
   /* @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("register")
    public String register(Model model) {
        log.info("Hello register");

        //model.addAttribute("userRegistrationDTO", new UserRegistrationDTO());
        return "register";
    }
   @PostMapping("/register")
    public String register(UserRegistrationDTO userRegistrationDTO) {

        userService.registerAndLogin(userRegistrationDTO);
        return "redirect:/login";
    }*/

}
