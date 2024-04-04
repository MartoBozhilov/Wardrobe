package bg.conquerors.wardrobe.service.registration;

import bg.conquerors.wardrobe.model.entity.User;
import bg.conquerors.wardrobe.service.UserService;
import bg.conquerors.wardrobe.service.events.RegistrationCompleteEvent;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;
    private ApplicationEventPublisher publisher;
    @PostMapping
    public String register(RegistrationRequest registrationRequest, final HttpServletRequest request){

        User user = userService.registerUser(registrationRequest);

        publisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl(request)));

        return user.getUsername() + "has been registered successfully ! Please, check your email to complete your registration !";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
}

