package bg.conquerors.wardrobe.service;

import bg.conquerors.wardrobe.model.dto.AddDiscountDTO;
import bg.conquerors.wardrobe.model.dto.AddOrderDTO;
import bg.conquerors.wardrobe.model.dto.AddProductDTO;
import bg.conquerors.wardrobe.model.dto.AddUserDTO;
import bg.conquerors.wardrobe.model.entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AdminService {
    //region <Product>
    void addProduct(AddProductDTO addProductDTO);

    void editProduct(String productNumber, AddProductDTO addProductDTO);

    void deleteProduct(String productNumber);

    AddProductDTO getProductByProductNumber(String id);
    //endregion

    //region <Discount>
    void addDiscount(AddDiscountDTO addDiscountDTO);

    void editDiscount(Long id, AddDiscountDTO addDiscountDTO);

    void deleteDiscount(Long id);

    AddDiscountDTO getDiscountById(Long id);
    //endregion

    //region <Order>
    //void addOrder(AddOrderDTO addOrderDTO);
    void editOrder(Long id, AddOrderDTO addOrderDTO);

    void deleteOrder(Long id);

    void deleteOrderProduct(Long id);

    void addOrderProduct(Long orderId, Long productId, Integer quantity);

    AddOrderDTO getOrderById(Long id);

    void changeStatus(Long id);
    //endregion

    //region <User>
    void editUser(Long id, AddUserDTO addUserDTO);

    void deleteUser(Long id);

    AddUserDTO getUserById(Long id);
    //endregion
}
