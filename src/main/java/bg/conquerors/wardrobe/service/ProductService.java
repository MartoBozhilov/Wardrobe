package bg.conquerors.wardrobe.service;

import bg.conquerors.wardrobe.model.dto.ViewProductDTO;
import bg.conquerors.wardrobe.model.dto.ViewProductsDTO;

import java.util.List;

public interface ProductService {

    List<ViewProductsDTO> getViewOfProducts();
    ViewProductDTO getProductByProductNumber(String productNumber);

}
