package bg.conquerors.wardrobe.service.impl;

import bg.conquerors.wardrobe.model.dto.UserRegistrationDTO;
import bg.conquerors.wardrobe.model.entity.User;
import bg.conquerors.wardrobe.repository.UserRepository;
import bg.conquerors.wardrobe.repository.VerificationTokenRepository;
import bg.conquerors.wardrobe.service.UserService;
import bg.conquerors.wardrobe.service.exception.UserAlreadyExistsExceprion;
import bg.conquerors.wardrobe.service.registration.RegistrationRequest;
import bg.conquerors.wardrobe.service.registration.tokens.VerificationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository tokenRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(RegistrationRequest request) {
        Optional<User> user = this.findByUsername(request.username());
        if (user.isPresent()){
            throw new UserAlreadyExistsExceprion("User with username "+request.username() + " already exists !");
        }

        var newUser = new User();
        newUser.setFirstName(request.firstName());
        newUser.setLastName(request.lastName());
        newUser.setEmail(request.email());
        newUser.setUsername(request.username());
        newUser.setPhoneNumber(request.phoneNumber());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setPoints(request.points());
        //newUser.setRoles();
        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

  /*  @Override
    public void registerAndLogin(UserRegistrationDTO userRegistrationDTO) {

    }
*/
    @Override
    public void saveUserVerificationToken(User user, String verificationToken) {
        var vt = new VerificationToken(user,verificationToken);
        tokenRepository.save(vt);
    }

}
