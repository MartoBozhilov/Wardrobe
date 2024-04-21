package bg.conquerors.wardrobe.service;

import bg.conquerors.wardrobe.model.dto.AddProductDTO;
import bg.conquerors.wardrobe.model.dto.UserRegistrationDTO;
import bg.conquerors.wardrobe.model.entity.User;

public interface UserService {

    void register(UserRegistrationDTO userRegistrationDTO);

    User findCurrentUser();

    void editUserInformation(Long id, UserRegistrationDTO userRegistrationDTO);
}
