package bg.conquerors.wardrobe.service;

import bg.conquerors.wardrobe.model.dto.CartViewDTO;
import bg.conquerors.wardrobe.model.dto.FinishOrderDTO;
import bg.conquerors.wardrobe.model.entity.Order;
import bg.conquerors.wardrobe.model.entity.User;
import bg.conquerors.wardrobe.model.enums.SizeEnum;

import java.util.List;

public interface OrderService {

    void createNewOrder(User user);

    void addProductToCart(String productNumber, SizeEnum size, Integer quantity);

    void removeProductFromCart(Long id);

    CartViewDTO getCart();

    void saveOrder(FinishOrderDTO finishOrderDTO) throws Exception;

    List<Order> getAllOrders();
}
