package bg.conquerors.wardrobe.service;

import bg.conquerors.wardrobe.model.dto.CartViewDTO;
import bg.conquerors.wardrobe.model.entity.User;
import bg.conquerors.wardrobe.model.enums.SizeEnum;

public interface OrderService {

    void createNewOrder(User user);

    void addProductToCart(String productNumber, SizeEnum size);

    void removeProductFromCart(Long id);

    CartViewDTO getCart();

}
