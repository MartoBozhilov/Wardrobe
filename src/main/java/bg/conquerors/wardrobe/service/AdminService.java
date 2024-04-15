package bg.conquerors.wardrobe.service;

import bg.conquerors.wardrobe.model.dto.AddDiscountDTO;
import bg.conquerors.wardrobe.model.dto.AddProductDTO;

public interface AdminService {

    void addProduct(AddProductDTO addProductDTO);
    void editProduct(Long id,AddProductDTO addProductDTO);
    void deleteProduct(Long id);
    AddProductDTO getProductById(Long id);


    void addDiscount(AddDiscountDTO addDiscountDTO);
    void editDiscount(Long id, AddDiscountDTO addDiscountDTO);
    void deleteDiscount(Long id);
    AddDiscountDTO getDiscountById(Long id);
}
