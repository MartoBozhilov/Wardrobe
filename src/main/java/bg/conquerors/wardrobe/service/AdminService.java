package bg.conquerors.wardrobe.service;

import bg.conquerors.wardrobe.model.dto.*;
import bg.conquerors.wardrobe.model.entity.Order;
import bg.conquerors.wardrobe.model.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Date;

import javax.swing.text.View;
import java.util.List;

public interface AdminService {
    //region <Product>
    void addProduct(AddProductDTO addProductDTO);

    void editProduct(String productNumber, AddProductDTO addProductDTO);

    void deleteProduct(String productNumber);

    AddProductDTO getProductByProductNumber(String id);

    List<Product> getAllProducts();
    //endregion

    //region <Discount>
    void addDiscount(AddDiscountDTO addDiscountDTO);

    void editDiscount(Long id, AddDiscountDTO addDiscountDTO);

    void deleteDiscount(Long id);

    AddDiscountDTO getDiscountById(Long id);

    void  setDiscountToProduct(SetDiscountToProductDTO setDiscountToProductDTO);
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

    StatisticDTO getStatistics(Date startDate, Date endDate);

    List<ViewProductsDTO> findAllByDiscount();

    List<ViewAllDiscountsDTO> getAllDiscounts();

}
