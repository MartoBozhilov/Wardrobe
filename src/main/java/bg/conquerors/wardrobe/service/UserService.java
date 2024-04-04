package bg.conquerors.wardrobe.service;

import bg.conquerors.wardrobe.model.dto.UserRegistrationDTO;
import bg.conquerors.wardrobe.model.entity.User;
import bg.conquerors.wardrobe.service.registration.RegistrationRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getUsers();

    User registerUser(RegistrationRequest request);

    Optional<User> findByUsername(String username);
    void registerAndLogin(UserRegistrationDTO userRegistrationDTO);

    void saveUserVerificationToken(User user, String verificationToken);
}
