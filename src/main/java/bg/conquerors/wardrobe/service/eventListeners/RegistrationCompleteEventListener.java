package bg.conquerors.wardrobe.service.eventListeners;

import bg.conquerors.wardrobe.model.entity.User;
import bg.conquerors.wardrobe.service.UserService;
import bg.conquerors.wardrobe.service.events.RegistrationCompleteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
private final UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        User user = event.getUser();
        String verificationToken = UUID.randomUUID().toString();
        userService.saveUserVerificationToken(user,verificationToken);
        String url = event.getApplicationUrl()+"/register/verify?token="+verificationToken;
        log.info("Click the link to verify you registration : {}",url);
    }
}
