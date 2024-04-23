package bg.conquerors.wardrobe.service;

import bg.conquerors.wardrobe.model.dto.AddProductDTO;
import bg.conquerors.wardrobe.model.dto.UserRegistrationDTO;
import bg.conquerors.wardrobe.model.dto.ViewUserOrdersDTO;
import bg.conquerors.wardrobe.model.entity.User;

import java.util.List;

public interface UserService {

    void register(UserRegistrationDTO userRegistrationDTO);

    User findCurrentUser();

    void editUserInformation(Long id, UserRegistrationDTO userRegistrationDTO);

    List<ViewUserOrdersDTO> getUserOrders(User currentUser);
}
