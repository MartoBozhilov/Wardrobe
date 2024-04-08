package bg.conquerors.wardrobe.service;

import bg.conquerors.wardrobe.model.dto.AddProductDTO;

public interface AdminService {

    void addProduct(AddProductDTO addProductDTO);
    void editProduct(Long id,AddProductDTO addProductDTO);
    void deleteProduct(Long id);
    AddProductDTO getProductById(Long id);
}
