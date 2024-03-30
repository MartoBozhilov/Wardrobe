package bg.conquerors.wardrobe.service;

import bg.conquerors.wardrobe.model.dto.UserRegistrationDTO;

public interface UserService {

    void registerAndLogin(UserRegistrationDTO userRegistrationDTO);

}
