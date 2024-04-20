package bg.conquerors.wardrobe.service;

import bg.conquerors.wardrobe.model.dto.AddDiscountDTO;
import bg.conquerors.wardrobe.model.dto.AddOrderDTO;
import bg.conquerors.wardrobe.model.dto.AddProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AdminService {

    void addProduct(AddProductDTO addProductDTO);
    void editProduct(String productNumber,AddProductDTO addProductDTO);
    void deleteProduct(String productNumber);
    AddProductDTO getProductByProductNumber(String id);


    void addDiscount(AddDiscountDTO addDiscountDTO);
    void editDiscount(Long id, AddDiscountDTO addDiscountDTO);
    void deleteDiscount(Long id);
    AddDiscountDTO getDiscountById(Long id);

    //void addOrder(AddOrderDTO addOrderDTO);
    void editOrder(Long id, AddOrderDTO addOrderDTO);
    void deleteOrder(Long id);
    AddOrderDTO getOrderById(Long id);
}
